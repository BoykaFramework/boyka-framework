---
title: 🔀 Branching Strategy
sidebar_position: 2
---

![Branching strategy](/img/docs/contributing/branching-strategy.png)

## 👨‍🏭 Contributor steps

- All contributors should create a branch named as `issue-<issue-number>`
- Once the contributor is done with the work, they should create the PR to merge their branch to `main` branch

## 🧑‍💼 Core Team steps

- Core Team members will review the PR raised by contributors and approve / suggest changes in it
- Once everything is fine, Core Team will squash merge the PR to `main` branch
- Core Team will decide when to release a new version
- Whenever a new version is planned to be released, Core Team will trigger the `release` workflow and mark the release as a generally available release or a `beta` release

## 🔢 Versioning process

- We follow [SemVer](https://semver.org/) versioning strategy
- We follow [Convention Commit](https://www.conventionalcommits.org/en/v1.0.0/) Commit message formatting
- Version for a new release is selected when triggering the release workflow. It can be any of the following versioning strategy:
  - `patch`: If there are bug fixes
  - `minor`: If there are new features being added
  - `major`: If there are any `BREAKING CHANGES` or changes which are not backward compatible
- When a release is marked as `pre-release`, a `beta` version will be released. Following are the release steps:
  - Framework JAR will get published to Maven central for the new version
  - A new pre-release will be created on GitHub project release page
  - A new discussion will be created for the same release in `Discussion` tab
- When a release is not marked as `pre-release`, a `stable` version will get released. Following are the release steps:
  - Framework JAR will get published on Maven central for the new version
  - A new latest release will be created in GitHub project release page
  - A new discussion will be created for the same release in `Discussion` tab
  - Finally a Tweet will be posted on Twitter handle [@BoykaFramework][twitter], informing about the Boyka framework release

[twitter]: https://dub.sh/boyka-twitter
