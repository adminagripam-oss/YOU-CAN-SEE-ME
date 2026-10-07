---
name: ota_deployment
description: Updates project version, creates a POSIX-compliant ZIP for Capacitor OTA, and creates a GitHub Pull Request.
---

# OTA Deployment Workflow

Whenever you are asked to generate an OTA update, follow these strict steps:

1. **Bump Version**:
   - Update `version` in `package.json`.
   - Update `version` and the `url` string in `version.json` (e.g., `dist-2.1.5.zip`).

2. **Build Project**:
   - Always build the project using Windows `cmd.exe`: `cmd.exe /c "npm run build"`. Do not use PowerShell for this if execution policies are restricted.

3. **Generate POSIX ZIP**:
   - Run the custom Node script to generate a zip file that Capacitor can extract correctly: `node create_posix_zip.js <NEW_VERSION>`.
   - Never use standard Windows zip utilities (they break Capacitor extraction on Android).

4. **Git Workflow**:
   - Checkout a new feature branch (e.g., `git checkout -b feat/ota-<VERSION>`).
   - Stage `package.json`, `version.json`, and any source code changes (`git add package.json version.json src/ ...`).
   - Commit the changes (`git commit -m "chore: OTA release <VERSION>"`).
   - Push to the repository (`git push -u origin feat/ota-<VERSION>`).
   - Provide the GitHub link to the user to open a Pull Request.
