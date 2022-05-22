---
title: ♻️ GitHub Actions workflows
sidebar_position: 3
---

## Project workflows

Following are the GitHub Actions workflows that we have created for our project along with it's trigger condition:

| Workflow | Event Trigger | Path Trigger | Description |
| -------- | ------- | ----------- | ----------- |
| `check-commit` | `PR -> main` | `all` | Will check commit message if it complies with [conventional commit specifications][commit]. |
| `codeql` | `push (main)`, `PR -> main`, `Every Sat 2 PM` | `all` | Will run codeql on the latest commit. |
| `deploy-site` | `PR -> main` | `website/`, `.github/` | Will only test the site. |
| `deploy-site` | `push (main)` | `website/`, `.github/` | Will only deploy the site to GitHub pages. |
| `test-java` | `push (main)`, `PR -> main` | `core-java/`, `.github/` | Will run check code styles, run tests and sonarcloud code analysis. |

[commit]: https://conventionalcommits.org/
