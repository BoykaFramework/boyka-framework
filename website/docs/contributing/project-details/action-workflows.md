---
title: ♻️ GitHub Actions workflows
sidebar_position: 3
---

## Project workflows

Following are the GitHub Actions workflows that we have created for our project along with it's trigger condition:

| Workflow | Event Trigger | Path Trigger | Description |
| -------- | ------- | ----------- | ----------- |
| `check-commit` | `PR -> main` | `all` | Will check commit message if it complies with [conventional commit specifications][commit]. |
| `codeql` | `push (main)`, `PR -> main`, `Every Sat 2 PM` | `all` | Will run CodeQL on the latest commit for both `java` and `typescript`. |
| `deploy-site` | `PR -> main` | `website/`, `.github/` | Will only test the site. |
| `deploy-site` | `push (main)` | `website/`, `.github/` | Will only deploy the site to GitHub pages. |
| `pr-labeler` | `PR -> (open / close)` | `all` | Will label the PR with the appropriate labels. |
| `publish` | `Manual trigger` | `all` | Will deploy framework to Maven central, create release and discussion on GitHub and Tweet about the release from [@WasiqBhamla Twitter][twitter] handle. |
| `test-java` | `push (main)`, `PR -> main` | `core-java/`, `.github/` | Will run check code styles, run tests and SonarCloud code analysis. Code analysis will only run on `push(main)` event trigger |

[commit]: https://conventionalcommits.org/
[twitter]: https://twitter.com/WasiqBhamla
