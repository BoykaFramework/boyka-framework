---
title: ♻️ GitHub Actions workflows
sidebar_position: 4
---

## Project workflows

:::tip
Check out all our project workflows [on GitHub](https://github.com/BoykaFramework/boyka-framework/actions)
:::

Following are the GitHub Actions workflows that we have created for our project along with it's trigger condition:

| Workflow | Event Trigger | Path Trigger | Description |
| -------- | ------- | ----------- | ----------- |
| `bs-app-upload` | `Every month on 25th day`, `manual` | `all` | Will upload the Android and iOS apps on the BrowserStack cloud |
| `check-commit` | `PR -> main` | `all` | Will check commit message if it complies with [conventional commit specifications][commit]. |
| `codeql` | `push (main)`, `PR -> main` | `all` | Will run CodeQL on the latest commit for both `java` and `typescript`. |
| `deploy-site` | `PR -> main` | `website/`, `.github/` | Will only test the site. |
| `deploy-site` | `push (main)` | `website/`, `.github/` | Will only deploy the site to GitHub pages. |
| `lt-app-upload` | `Every 3 months`, `manual` | `all` | Will upload the Android and iOS apps on the LambdaTest cloud |
| `pr-labeler` | `PR -> (open / close)` | `all` | Will label the PR with the appropriate labels. |
| `release` | `manual` | `all` | Will deploy the framework to Maven central, create a tag and release on GitHub based on input params. |
| `test-core` | `push (main)`, `PR -> (main)` | `core-java/`, `.github/` | Will run check code styles, run tests and SonarCloud code analysis. Code analysis will only run on `push(main)` event trigger |
| `tweet-release` | `release -> (published)` | `all` | Will tweet about the release on Twitter handle [@BoykaFramework][twitter]. |

[commit]: https://conventionalcommits.org/
[twitter]: https://dub.sh/boyka-twitter
