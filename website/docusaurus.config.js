// @ts-check
// Note: type annotations allow type checking and IDEs autocompletion

const lightCodeTheme = require('prism-react-renderer/themes/github');
const darkCodeTheme = require('prism-react-renderer/themes/dracula');

/** @type {import('@docusaurus/types').Config} */
const config = {
  title: 'boyka',
  tagline:
    'Ultimate test automation framework for automating Web, Android, iOS and API applications',
  url: 'https://wasiqbhamla.github.io',
  baseUrl: '/boyka-java/',
  onBrokenLinks: 'throw',
  onBrokenMarkdownLinks: 'throw',
  favicon: 'img/boyka-favicon.png',
  organizationName: 'WasiqBhamla', // Usually your GitHub org/user name.
  projectName: 'boyka-java', // Usually your repo name.

  presets: [
    [
      'classic',
      /** @type {import('@docusaurus/preset-classic').Options} */
      ({
        docs: {
          sidebarPath: require.resolve('./sidebars.js'),
          editUrl: 'https://github.com/WasiqBhamla/boyka-java/edit/main/website/',
        },
        blog: {
          showReadingTime: true,
          postsPerPage: 10,
          sortPosts: 'ascending',
          editUrl: 'https://github.com/WasiqBhamla/boyka-java/edit/main/website/blog/',
        },
        theme: {
          customCss: require.resolve('./src/css/custom.css'),
        },
        sitemap: {
          changefreq: 'weekly',
          priority: 0.5,
        },
      }),
    ],
  ],

  themeConfig:
    /** @type {import('@docusaurus/preset-classic').ThemeConfig} */
    ({
      navbar: {
        title: 'boyka',
        logo: {
          alt: 'boyka logo',
          src: 'img/boyka-favicon.png',
        },
        items: [
          {
            type: 'doc',
            docId: 'intro',
            position: 'left',
            label: 'tutorial',
          },
          { to: '/blog', label: 'blog', position: 'left' },
          {
            href: 'https://github.com/WasiqBhamla/boyka-java',
            label: 'gitHub',
            position: 'right',
          },
        ],
      },
      footer: {
        style: 'dark',
        links: [
          {
            title: 'docs',
            items: [
              {
                label: 'tutorial',
                to: '/docs/intro',
              },
            ],
          },
          {
            title: 'community',
            items: [
              {
                label: 'stack overflow',
                href: 'https://stackoverflow.com/questions/tagged/boyka-java',
              },
              {
                label: 'discord',
                href: 'https://discord.gg/dUg8K9DAsR',
              },
              {
                label: 'twitter',
                href: 'https://twitter.com/WasiqBhamla',
              },
            ],
          },
          {
            title: 'more',
            items: [
              {
                label: 'blog',
                to: '/blog',
              },
              {
                label: 'github',
                href: 'https://github.com/WasiqBhamla/boyka-java',
              },
            ],
          },
        ],
        copyright: `copyright ©️ ${new Date().getFullYear()} Wasiq Bhamla. Built with ❤️ using Docusaurus.`,
      },
      prism: {
        theme: lightCodeTheme,
        darkTheme: darkCodeTheme,
      },
    }),
};

module.exports = config;
