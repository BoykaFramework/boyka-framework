---
title: 📩 Commit steps
sidebar_position: 6
---

We are using [Conventional Commits](https://conventionalcommits.org/) to ensure that our code follows a consistent commit message format. To make sure that we are following the rules, we normally commit our changes with VSCode using the following steps:

## Setup Auto GPG commit signing

We have branch protection setup in our project which requires that all commits should be GPG signed. Let's walk through how you can sign your commits.

### Install GPG command line

Install the latest GPG command line from [their site](https://www.gnupg.org/download/).

On Mac, you can also install GPG using [Homebrew](https://brew.sh/) by executing `brew install gnupg`.

### Generate GPG keys

To generate a new GPG keys, run the following command:

```shell
> gpg --full-generate-key
```

Enter the prompts properly and set a good passphrase.

### Fetch the GPG key

Once the key is created, you need to get the GPG key ID, run the command:

```shell
> gpg --list-secret-keys --keyid-format=long
```

You will see the output similar to the following:

```shell
/Users/username/.gnupg/pubring.kbx
-------------------------------------
// highlight-next-line
sec   rsa4096/AB510283YYYYYYYY 2018-07-03 [SC]
      AAAAAAAAAA2010DD804CBB15AB510283YYYYYYYY
uid                 [ unknown] Your Name (Your role) <your.email@gmail.com>
ssb   rsa4096/AAAAA90AB0B84BE 2018-07-03 [E]
```

The key you need is marked with `Y` 8 chars in the lines highlighted above.

### Set GPG key in GitHub

Execute the following command to get the GPG public key:

```shell
> gpg --armor --export <your-8-digit-gpg-id>
```

Copy the output from the above command,

- Open your [GitHub GPG settings page](https://github.com/settings/keys)
- Click on `New GPG Key` button
- Add any suitable `Title`
- Paste your GPG public key copied from above in `Key` textarea
- Click on `Add GPG key` button

### Setup Git to Auto sign commits

Setup your Git configuration on your machine to tell it to automatically sign your commits by using the following commands:

```shell
> git config --global gpg.program gpg
> git config --global user.signingkey <your-8-digit-gpg-id>
> git config --global commit.gpgsign true
```

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
- Build and run check styles from the Java project to check if the check style and code compilation is successful.

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

As soon as you push your commit, you must create a draft PR on GitHub. Because our workflows will only get triggered on PR's that are raised against `develop` branch.

## Ping on Discord

Once PR is raised, ping in the `#contributor-discussion` channel on our [Discord server](https://dub.sh/boyka-discord) to let all the contributors know and you can schedule a demo with the contributors to showcase your changes.
