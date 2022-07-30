---
title: 📩 Commit steps
sidebar_position: 5
---

We are using [Conventional Commits](https://conventionalcommits.org/) to ensure that our code follows a consistent commit message format. To make sure that we are following the rules, we normally commit our changes with VSCode using the following steps:

## Add Conventional Commits extension

Search for `Conventional Commits` in the VSCode Marketplace and install it.

![Conventional Commits Extension](/img/docs/contributing/commit-ext.png)

## Disable auto commit

After installing the extension, you can disable the auto commit feature in the settings.

![Conventional Commits Extension Setting](/img/docs/contributing/commit-ext-setting.png)

To access the settings, click on the `Settings` icon as shown in the screenshot above.

![Conventional Commits Extension Setting Page](/img/docs/contributing/commit-ext-setting-page.png)

Once you click on the `Settings` icon, you will see a list of settings for the `Conventional Commits` extension. Here you can uncheck the `Auto commit` option.

## Build commit message

When you are ready to commit, press on Conventional commit icon in source control tab to open the commit prompt.

![Conventional Commit Prompt](/img/docs/contributing/commit-prompt.png)

### Add commit type

When you click on the `Conventional Commit` button, you will see a list of available commit types. Select the one that best describes your changes.

![Conventional Commit Type](/img/docs/contributing/commit-type.png)

### Add commit scope

After you select the commit type, you will see a list of available scopes. Select the one that best describes your changes. If you don't see any scope, you can let the `boyka-core` team know about it and we will add it for you.

![Conventional Commit Scope](/img/docs/contributing/commit-scope.png)

### Add relatable Emoji

After you select the commit scope, you will see a list of available Emoji's. Select the one that best describes your changes.

![Conventional Commit Emoji](/img/docs/contributing/commit-emoji.png)

### Add short description

After you select the commit emoji, you will see a text field where you can write a short description of your changes.

![Conventional Commit Description](/img/docs/contributing/commit-description.png)

### Add long description (optional)

After you write the short description, you will see a text field where you can write a long description of your changes. This is optional, but it is recommended to write a detailed description of your changes.

![Conventional Commit Long Description](/img/docs/contributing/commit-long-description.png)

### Add breaking changes details (optional)

After you write the long description, you will see a text field where you can write a detailed description of the breaking changes. This is optional, but it is recommended to write a detailed description of the breaking changes.

Make sure to append the `BREAKING CHANGE:` tag before the description.

![Conventional Commit Breaking Changes](/img/docs/contributing/commit-breaking-changes.png)

## Commit the changes

Once the commit message is built, you can copy it and paste it on the terminal and commit using the following command:

```shell
> git commit -m "<copied-message>"
```

## Pre-commit checks

When you try to commit using the command in previous step, pre-commit checks will get triggered.

Following checks will happen when you commit:

- Commit message lint to check if the commit message is in correct format
- ESLint to check if the website code complies with the [ESLint](https://eslint.org/) rules. It will only run if there is change to `.js`, `.jsx`, `.ts` or `.tsx` files.
- Prettier to check if the website code complies with the [Prettier](https://prettier.io/) rules. It will only run if there is change to `.js`, `.jsx`, `.ts` or `.tsx` files.
- Build the documentation website to check if the website builds successfully.
- Build and run tests from the Java project to check if the check style, code compilation is successful and overall code coverage is above `70%`.

## Push your commit

You can push your changes from the Terminal by executing the following command:

```shell
> git push
```

Or, from VSCode, you can click on the `Push` button in the source control tab.

![Push Commit](/img/docs/contributing/commit-push-1.png)

When you click on the `Push` button, you will see a pop-up asking you to confirm your push.

![Push Commit](/img/docs/contributing/commit-push-2.png)

## Create draft PR

As soon as you push your commit, you must create a draft PR on GitHub. Because our workflows will only get triggered on PR's that are raised against `main` branch.

[commit-sign]:https://docs.github.com/en/authentication/managing-commit-signature-verification/signing-commits
