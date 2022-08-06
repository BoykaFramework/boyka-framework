---
title: 🔀 Branching Strategy
sidebar_position: 2
---

![Branching strategy](/img/docs/contributing/branching-strategy.png)

## 👨‍🏭 Contributor steps

- All contributors should create a branch named as `issue-<issue-number>`
- Once the contributor is done with the work, they should create the PR to merge their branch to `develop` branch

## 🧑‍💼 Maintainer steps

- Maintainers will review the PR raised by contributors and approve / suggest changes in it
- Once everything is fine, maintainers will squash merge the PR to `develop` branch
- For PR raised in `staging` branch, when all the issue tickets planned for the current milestone are closed, the `develop` branch will be merged to `staging` branch without squashing the commits
- For PR raised in `main` branch, when the testing on release candidate created on `staging` is done, the `staging` branch will be merged to `main` branch without squashing the commits

## 🤖 GitHub Actions steps

- As soon as the PR is merged to `develop` branch, the new PR will get automatically created from `develop` branch to `staging` branch
- Once the PR is merged to `staging` branch, the new PR will get automatically created from `staging` branch to `main` branch
- Also a release candidate will be created from `staging` branch and will be released to Maven Central
- Once the PR is merged to `main` branch, the final release will be created from `main` branch and published to Maven Central
- After release to Maven Central, GitHub release will also get created along with Release discussion thread
- Finally a Tweet will be posted on Twitter informing about the Boyka framework release
