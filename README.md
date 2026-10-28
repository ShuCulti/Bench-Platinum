# Bench-Platinum
Ora

Platinum Bench is an open-source leaderboard for local LLM inference. You run a small CLI on your own machine, it benchmarks models through llama.cpp, and your results end up on the site next to everyone else's. So instead of guessing which GPU, quant or setting runs a model fastest, you can just look it up.

It's my individual project for the Full Stack Development semester at Fontys ICT, so it's still early and changes a lot.

## Stack

- Backend: Java 25, Spring Boot 4, Spring Data JPA, Gradle
- Database: PostgreSQL 17
- Frontend: React 19 + Vite
- Docker + Docker Compose, GitHub Actions for CI, Qodana for static analysis

## Running it

You need Docker with Compose. Create a `.env` in the repo root:

```
DB_PASSWORD= ...
```

Then:

```bash
docker compose up --build
```

Postgres starts first, the backend waits until it's healthy and then runs on port 8080. `docker compose down` stops everything and keeps your data. Add `-v` if you want a clean database.

## Working on the backend

Rebuilding the image every time gets slow, so I run only the database in Docker and the backend through Gradle:

```bash
docker compose up -d db
cd platinum-bench-backend
./gradlew bootRun
```

This needs JDK 25 and a second `.env` with the same `DB_PASSWORD`, placed in `platinum-bench-backend/` next to `build.gradle`. Spring looks for it relative to where you start the app, so anywhere else and it won't find it.

## Frontend

```bash
cd platinum-bench-frontend
npm install
npm run dev
```

It runs on `http://localhost:5173`, which is the origin the backend allows.

## Layout

```
compose.yml                  database + backend
platinum-bench-backend/      Spring Boot API (controller, logic, domain, persistence)
platinum-bench-frontend/     React app
.github/workflows/           CI and Qodana
```
