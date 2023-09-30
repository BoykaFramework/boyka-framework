---
title: 🔀 Branching Strategy
sidebar_position: 2
---

![Branching strategy](/img/docs/contributing/branching-strategy.png)

## 👨‍🏭 Contributor steps

- All contributors should create a branch named as `issue-<issue-number>`
- Once the contributor is done with the work, they should create the PR to merge their branch to `main` branch

## 🧑‍💼 Maintainer steps

- Maintainers will review the PR raised by contributors and approve / suggest changes in it
- Once everything is fine, maintainers will squash merge the PR to `main` branch
- Maintainers will decide when to release a new version
- Whenever a new version is planned to be released, maintainers will trigger the `release` workflow and mark the release as a generally available release or a `beta` release

## 🔢 Versioning process

- We follow [SemVer](https://semver.org/) versioning strategy
- We follow [Convention Commit](https://www.conventionalcommits.org/en/v1.0.0/) Commit message formatting
- Currently, we are still in early stage of the framework where it's still not considered stable, hence our version is in `0.x.x` format
- Version for a new release is automatically identified based on the commit messages
  - If there are bug fixes, `patch` version will be released
  - If there are new features being added, `minor` version will be released
  - If there are any `BREAKING CHANGES` or we come out of MVP stage, then `major` version will be released
- If it is a `beta` release,
  - Framework JAR will get published to Maven central for the new version
  - A new pre-release will be created on GitHub project release page
  - A new discussion will be created for the same release in `Discussion` tab
- If it is a final release,
  - Framework JAR will get published on Maven central for the new version
  - A new latest release will be created in GitHub project release page
  - A new discussion will be created for the same release in `Discussion` tab
  - Finally a Tweet will be posted on Twitter handle [@BoykaFramework][twitter], informing about the Boyka framework release

[twitter]: https://dub.sh/boyka-twitter
