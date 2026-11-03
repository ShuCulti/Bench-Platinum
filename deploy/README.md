# Deploying to EduCloud

The VM can't be reached from GitHub (it sits behind the Fontys VPN), so deployment is pull-based. CI builds the backend image on every push to `main` and publishes it to GHCR (GitHub Container Registry). A systemd timer on the VM checks GHCR every 5 minutes, pulls a new image if there is one, restarts the backend and checks `/actuator/health`.

```
push to main ──▶ CI: tests + Sonar ──▶ image pushed to ghcr.io/shuculti/platinum-bench-backend
                                                        ▲
EduCloud VM: timer every 5 min ──▶ update.sh ──pull─────┘ ──▶ compose up -d ──▶ health check
```

## One-time setup

**1. Make the package public.** GHCR packages start out private, and the VM pulls without logging in. After the first successful push to `main`: GitHub profile → Packages → `platinum-bench-backend` → Package settings → Change visibility → Public. The repo is public already, so this exposes nothing new.

**2. Switch the VM to the prod stack.** In `~/Bench-Platinum` on the VM:

```bash
git pull
docker compose -f compose.prod.yml pull
docker compose -f compose.prod.yml up -d
docker compose -f compose.prod.yml ps
```

Both stacks use the project name `bench-platinum`, so this replaces the containers from `compose.yml` and keeps the same database volume. `ps` should show no `5432` for `db` anymore.

**3. Install the timer.**

```bash
sudo cp deploy/platinum-bench-update.service deploy/platinum-bench-update.timer /etc/systemd/system/
sudo systemctl daemon-reload
sudo systemctl enable --now platinum-bench-update.timer
```

## Checking on it

```bash
systemctl list-timers platinum-bench-update.timer   # when it last ran and runs next
journalctl -u platinum-bench-update -n 20           # what each run did
systemctl --failed                                  # shows the unit if a health check failed
sudo systemctl start platinum-bench-update          # run it right now instead of waiting
```

## Rolling back

Every image is also tagged with its commit SHA. To go back to an older build, add it to `.env` on the VM:

```
BACKEND_TAG=<full commit sha>
```

and run `sudo systemctl start platinum-bench-update`. Delete the line again to go back to following `latest`.

## What the timer doesn't do

It only updates images. If `compose.prod.yml` itself changes, run `git pull` and `docker compose -f compose.prod.yml up -d` on the VM yourself.
