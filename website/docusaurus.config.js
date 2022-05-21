// @ts-check
// Note: type annotations allow type checking and IDEs autocompletion

const lightCodeTheme = require('prism-react-renderer/themes/github');
const darkCodeTheme = require('prism-react-renderer/themes/dracula');

/** @type {import('@docusaurus/types').Config} */
const config = {
  title: 'Boyka-framework',
  tagline:
    'Ultimate test automation framework for automating Web, Android, iOS and API applications',
  url: 'https://wasiqbhamla.github.io',
  baseUrl: '/boyka-framework/',
  onBrokenLinks: 'throw',
  onBrokenMarkdownLinks: 'throw',
  favicon: 'img/Boyka.png',
  organizationName: 'WasiqBhamla', // Usually your GitHub org/user name.
  projectName: 'boyka-framework', // Usually your repo name.

  plugins: [
    [
      '@docusaurus/plugin-content-docs',
      /** @type {import('@docusaurus/plugin-content-docs').Options} */
      ({
        id: 'api',
        path: './docs/api',
        routeBasePath: 'api',
        editUrl: 'https://github.com/WasiqBhamla/boyka-framework/edit/main/website/',
        editCurrentVersion: true,
        sidebarPath: require.resolve('./docs/api/sidebars.js'),
        showLastUpdateAuthor: true,
        showLastUpdateTime: true,
      }),
    ],
    [
      '@docusaurus/plugin-content-docs',
      /** @type {import('@docusaurus/plugin-content-docs').Options} */
      ({
        id: 'contributing',
        path: './docs/contributing',
        routeBasePath: 'contributing',
        editUrl: 'https://github.com/WasiqBhamla/boyka-framework/edit/main/website/',
        editCurrentVersion: true,
        sidebarPath: require.resolve('./docs/contributing/sidebars.js'),
        showLastUpdateAuthor: true,
        showLastUpdateTime: true,
      }),
    ],
  ],
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
          editUrl: 'https://github.com/WasiqBhamla/boyka-framework/edit/main/website/',
        },
        blog: {
          showReadingTime: true,
          postsPerPage: 10,
          sortPosts: 'ascending',
          editUrl: 'https://github.com/WasiqBhamla/boyka-framework/edit/main/website/blog/',
        },
        theme: {
          customCss: require.resolve('./src/css/custom.css'),
        },
        sitemap: {
          changefreq: 'daily',
          priority: 0.5,
        },
        googleAnalytics: {
          trackingID: 'G-FZCKMQCL6G',
          anonymizeIP: true,
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
          '⭐ Don\'t forget to Star the repo on 👉 <a target="_blank" rel="noopener noreferrer" href="https://github.com/WasiqBhamla/boyka-framework">GitHub</a> ⭐',
        backgroundColor: '#ffb600',
        textColor: '#000000',
        isCloseable: false,
      },
      colorMode: {
        defaultMode: 'dark',
        disableSwitch: true,
        respectPrefersColorScheme: true,
      },
      tableOfContents: {
        minHeadingLevel: 2,
        maxHeadingLevel: 4,
      },
      navbar: {
        title: 'Boyka-framework',
        logo: {
          alt: 'boyka logo',
          src: 'img/Boyka.png',
        },
        items: [
          {
            type: 'doc',
            docId: 'intro',
            label: 'documentation',
          },
          {
            to: '/api/intro',
            sidebarId: 'api',
            label: 'api',
          },
          {
            to: '/contributing/welcome',
            sidebarId: 'contributing',
            label: 'contributing',
          },
          { to: '/blog', label: 'blogs' },
          {
            href: 'https://github.com/WasiqBhamla/boyka-framework',
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
              {
                to: '/api/intro',
                label: 'api',
              },
              {
                to: '/contributing/welcome',
                label: 'contributing',
              },
            ],
          },
          {
            title: 'Follow us',
            items: [
              {
                label: 'stack overflow',
                href: 'https://stackoverflow.com/questions/tagged/boyka-framework',
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
                href: 'https://github.com/WasiqBhamla/boyka-framework',
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
