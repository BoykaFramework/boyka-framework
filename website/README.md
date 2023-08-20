# Website

This website is built using [Docusaurus 2](https://docusaurus.io/), a modern static website generator.

## Installation

Run following commands from root directory of the repository:

```bash
> pnpm i
```

## Local Development

```bash
> pnpm start:site
```

This command starts a local development server and opens up a browser window. Most changes are reflected live without having to restart the server.

## Build

```bash
> pnpm build:site
```

This command generates static content into the `build` directory and can be served using any static contents hosting service.

## Deployment

Using SSH:

```bash
> USE_SSH=true pnpm deploy:site
```

Not using SSH:

```bash
> GIT_USER=<Your GitHub username> pnpm deploy:site
```

If you are using GitHub pages for hosting, this command is a convenient way to build the website and push to the `gh-pages` branch.
