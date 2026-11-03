#!/usr/bin/env bash
# Pull-based deploy for the EduCloud VM, run every 5 minutes by
# platinum-bench-update.timer. Pulls the newest images, recreates only the
# containers whose image changed, then checks the backend's health endpoint.
# A failed health check makes the systemd unit fail, so it shows up in
# `systemctl --failed` and the journal.
set -euo pipefail

cd "$(dirname "$0")/.."

compose() { docker compose -f compose.prod.yml "$@"; }

running_image() {
  local id
  id=$(compose ps -q backend)
  if [ -n "$id" ]; then
    docker inspect --format '{{.Image}}' "$id"
  else
    echo none
  fi
}

# Image ID that the backend's tag (e.g. :latest) points to on this machine.
newest_image() {
  local id ref
  id=$(compose ps -q backend)
  [ -n "$id" ] || { echo none; return; }
  ref=$(docker inspect --format '{{.Config.Image}}' "$id")
  docker image inspect --format '{{.Id}}' "$ref"
}

before=$(running_image)
compose pull --quiet
compose up -d

# `up -d` doesn't always notice that a tag now points to a newer image, and
# then leaves the old container running. So compare the IDs ourselves and
# force a recreate of just the backend when they differ.
if [ "$(running_image)" != "$(newest_image)" ]; then
  compose up -d --force-recreate --no-deps backend
fi

after=$(running_image)

if [ "$before" != "$after" ]; then
  echo "deployed new backend image ${after:7:12} (was ${before:7:12})"
fi

# Spring needs a few seconds after a restart, so retry for up to a minute.
for _ in $(seq 1 30); do
  body=$(curl -fsS http://localhost:8080/actuator/health 2>/dev/null || true)
  if [[ $body == *'"status":"UP"'* ]]; then
    echo "health check passed"
    exit 0
  fi
  sleep 2
done

echo "health check FAILED, see: docker compose -f compose.prod.yml logs backend" >&2
exit 1
