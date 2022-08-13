---
title: ♻️ GitHub Actions workflows
sidebar_position: 4
---

## Project workflows

Following are the GitHub Actions workflows that we have created for our project along with it's trigger condition:

| Workflow | Event Trigger | Path Trigger | Description |
| -------- | ------- | ----------- | ----------- |
| `check-commit` | `PR -> main` | `all` | Will check commit message if it complies with [conventional commit specifications][commit]. |
| `codeql` | `push (main)`, `PR -> main`, `Every Sat 2 PM` | `all` | Will run CodeQL on the latest commit for both `java` and `typescript`. |
| `create-main-pr` | `push (staging)` | `all` | Will create a PR from `staging` branch to `main` branch. |
| `create-staging-pr` | `push (develop)` | `all` | Will create a PR from `develop` branch to `staging` branch. |
| `deploy-site` | `PR -> main` | `website/`, `.github/` | Will only test the site. |
| `deploy-site` | `push (main)` | `website/`, `.github/` | Will only deploy the site to GitHub pages. |
| `pr-labeler` | `PR -> (open / close)` | `all` | Will label the PR with the appropriate labels. |
| `pre-release` | `PR -> staging -> merged` | `all` | Will deploy release candidate for the framework to Maven central and create a tag in GitHub. |
| `release` | `PR -> main -> merged` | `all` | Will deploy the framework to Maven central, create a tag and release on GitHub. |
| `test-core` | `push (develop, staging, main)`, `PR -> (develop, staging, main)` | `core-java/`, `.github/` | Will run check code styles, run tests and SonarCloud code analysis. Code analysis will only run on `push(main)` event trigger |
| `tweet-release` | `release -> (published)` | `all` | Will tweet about the release on Twitter handle [@WasiqBhamla][twitter]. |

[commit]: https://conventionalcommits.org/
[twitter]: https://twitter.com/WasiqBhamla
