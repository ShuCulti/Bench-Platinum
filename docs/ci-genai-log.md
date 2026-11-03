# GenAI log: CI/CD

This log covers the CI/CD (Continuous Integration / Continuous Delivery) work I had Claude build for Platinum Bench. It's evidence for LO3, which asks for GenAI used to automate engineering work *and* a critical view on what it produced. My agreement with myself: Claude builds plumbing I review, and I write the code I'm meant to be learning (domain logic, APIs, frontend) by hand.

Each entry has what I asked, what came out, and my own review. The review part is mine.

## 2026-11-03: pull-based deployment to EduCloud

### What I asked

Continuous delivery to my EduCloud VM, which the teachers asked for as the deploy target. Constraint: GitHub's runners can't reach the VM, because EduCloud is behind the Fontys VPN with I-account login and 2FA, so a normal "CI SSHes into the server" pipeline doesn't work.

### What Claude proposed

Flip the direction. CI publishes an image, the server pulls it. Claude compared three options (push to my home server over Tailscale, pull-based on EduCloud, or both) and recommended pull-based on EduCloud because it deploys where the assessment happens and needs no inbound access to anything.

### What it generated

| File | What it does |
|---|---|
| `.github/workflows/ci.yml` | `docker-build` replaced by `docker-image`. Pull requests only build the image. A push to `main` also logs in to GHCR with the built-in `GITHUB_TOKEN` and pushes it, tagged `latest` and with the commit SHA. |
| `compose.prod.yml` | Server version of the stack. Uses the GHCR image instead of `build:`, doesn't publish 5432, and has `BACKEND_TAG` for rollbacks. |
| `deploy/update.sh` | Pulls, runs `up -d`, then retries `/actuator/health` for up to a minute. Exits non-zero if the backend never reports UP. |
| `deploy/platinum-bench-update.service` / `.timer` | Runs the script every 5 minutes and 2 minutes after boot. |
| `deploy/README.md` | Setup, checking on it, rollback. |
| `build.gradle` | Added `spring-boot-starter-actuator`, for the health endpoint. |

### Choices Claude made and its reasons

- **systemd timer instead of Watchtower.** Watchtower needs the Docker socket mounted into a container, which is root-equivalent access, and it's a black box. The timer is a short script I can read, and its output lands in the journal.
- **Health check on every run, not only after a deploy.** That turns the timer into basic monitoring: if the backend dies between deploys, the unit fails within 5 minutes and shows up in `systemctl --failed`.
- **Commit SHA tag next to `latest`.** `latest` alone can't be rolled back to a known version.
- **Pinned project name `bench-platinum`** so the prod stack reuses the existing database volume instead of starting empty.
- **Response checked with a string match instead of `curl | grep -q`.** With `set -o pipefail`, `grep -q` exiting early makes `curl` fail on a broken pipe, and the check would report DOWN while the app is UP.

### What Claude said it couldn't verify

- It couldn't build the backend from the session (no Maven access from there), so the actuator dependency is only checked once CI runs.
- The GHCR package is private by default and has to be made public by hand before the VM can pull.
- Timer, script and health check only get tested for real on the VM.

### My review

[NOTE: Fill this in after running it. Things worth checking yourself, not taking Claude's word for:]

- [NOTE: Did the first push to main actually publish the package? Screenshot the package page.]
- [NOTE: Does /actuator/health expose anything it shouldn't? Open it in the browser and look at what it returns.]
- [NOTE: Kill the backend with `docker compose -f compose.prod.yml stop backend` and check whether the timer reports the failure within 5 minutes. Then `start` it again.]
- [NOTE: Is the deploy actually "continuous"? Push a small visible change and time how long until it's live on the VM.]
- [NOTE: Anything you changed or would do differently, and why. For example: the script runs as `ubuntu`, which is in the docker group, so effectively root. Is a dedicated deploy user worth it here?]

## 2026-11-03 (evening): the first real deploy didn't happen

### What went wrong

I pushed a small backend change (`ca80b89`) to test CD. CI went green and pushed the image, and the timer kept logging "health check passed" every 5 minutes. But comparing image IDs on the VM showed the backend container was still running the *old* image. `:latest` on the VM pointed at the new one (`63b6...`), the container ran `26e5...`, and the journal never once said "deployed new backend image".

### Why

The generated script assumed `docker compose up -d` recreates a container when its tag points to a newer image after a `pull`. On this VM it didn't: every run said `Container bench-platinum-backend-1 Running`. And the health check didn't catch it, because the *old* backend was healthy. It checked "is something up", not "is the new version up".

### What Claude changed

`deploy/update.sh` now compares the running container's image ID with the ID its tag points to after the pull, and if they differ runs `docker compose up -d --force-recreate --no-deps backend`. That recreates only the backend, without touching the database.

### My review

[NOTE: This is the most useful entry for the critical-view part of LO3. The generated pipeline looked green at every step and still didn't deploy. Write down how you found it (comparing image IDs instead of trusting the log) and what that says about checking generated work.]
[NOTE: Still open: the health check can't tell old and new versions apart. Is that worth fixing, e.g. by having the app report its commit SHA?]
