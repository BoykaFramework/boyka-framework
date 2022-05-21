---
title: 🛡️ Branch Protection
sidebar_position: 2
---

## Branches

We have only one branch in our repository: `main`.

Whenever any contributor works on the project, we want to ensure that they are working for an open issue item. Hence, the contributor will create a branch named as `issue-<issue-number>`.

E.g: `issue-1` branch will be created if the GitHub issue number the contributor is working on is `1`.

## Protecting `main` branch

We have following steps to protect the `main` branch:

- Any merge required to be made in `main` branch, will need a PR to be created from `issue-<issue-number>` branch.
- PR will required approval from any one member of `boyka-core` team. So make sure to add the `boyka-core` team as the reviewer of the PR.
- All the commits in the PR should be [GPG signed][gpg-sign].
- All PR checks should be green.
- All open review conversations should be resolved.

## Exceptions to the above

Members of `boyka-core` team can bypass any of the above mentioned branch protection rules.

[gpg-sign]: https://docs.github.com/en/authentication/managing-commit-signature-verification/signing-commits
