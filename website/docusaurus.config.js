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
          showLastUpdateAuthor: true,
          showLastUpdateTime: true,
          path: './docs/framework-docs',
          routeBasePath: '/docs',
          sidebarPath: require.resolve('./docs/framework-docs/sidebars.js'),
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
      announcementBar: {
        id: 'star-the-repo',
        content:
          '⭐ Don\'t forget to Star the repo on 👉 <a target="_blank" rel="noopener noreferrer" href="https://github.com/WasiqBhamla/boyka-java">GitHub</a> ⭐',
        backgroundColor: '#ffb600',
        textColor: '#000000',
        isCloseable: false,
      },
      tableOfContents: {
        minHeadingLevel: 2,
        maxHeadingLevel: 4,
      },
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
            position: 'right',
            label: 'documentation',
          },
          { to: '/blog', label: 'blogs', position: 'right' },
          {
            href: 'https://github.com/WasiqBhamla/boyka-java',
            label: 'github',
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
                label: 'documentation',
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
                label: 'blogs',
                to: '/blog',
              },
              {
                label: 'github',
                href: 'https://github.com/WasiqBhamla/boyka-java',
              },
            ],
          },
        ],
        copyright: `copyright ©️ ${new Date().getFullYear()} Wasiq Bhamla. Built in 🇮🇳 with ❤️ using <a href="https://docusaurus.io/" target="_blank">Docusaurus</a>.`,
      },
      prism: {
        theme: lightCodeTheme,
        darkTheme: darkCodeTheme,
        additionalLanguages: ['java', 'json'],
      },
    }),
};

module.exports = config;
