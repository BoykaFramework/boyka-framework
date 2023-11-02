import type { Config } from '@docusaurus/types';
import type * as Preset from '@docusaurus/preset-classic';
import type * as Docs from '@docusaurus/plugin-content-docs';
import type * as Tag from '@docusaurus/plugin-google-tag-manager';
import { themes } from 'prism-react-renderer';

const config: Config = {
  title: 'Boyka-framework',
  tagline:
    'Ultimate test automation framework for automating Web, Android, iOS and API applications',
  url: 'https://BoykaFramework.github.io',
  baseUrl: '/boyka-framework/',
  deploymentBranch: 'gh-pages',
  onBrokenLinks: 'throw',
  onBrokenMarkdownLinks: 'throw',
  favicon: 'img/Boyka.png',
  organizationName: 'BoykaFramework', // Usually your GitHub org/user name.
  projectName: 'boyka-framework', // Usually your repo name.

  plugins: [
    [
      '@docusaurus/plugin-content-docs',
      {
        id: 'api',
        path: './docs/api',
        routeBasePath: 'api',
        editUrl: 'https://github.com/BoykaFramework/boyka-framework/edit/main/website/',
        editCurrentVersion: true,
        sidebarPath: require.resolve('./docs/api/sidebars.js'),
        showLastUpdateAuthor: true,
        showLastUpdateTime: true,
      } satisfies Docs.Options,
    ],
    [
      '@docusaurus/plugin-content-docs',
      {
        id: 'contributing',
        path: './docs/contributing',
        routeBasePath: 'contributing',
        editUrl: 'https://github.com/BoykaFramework/boyka-framework/edit/main/website/',
        editCurrentVersion: true,
        sidebarPath: require.resolve('./docs/contributing/sidebars.js'),
        showLastUpdateAuthor: true,
        showLastUpdateTime: true,
      } satisfies Docs.Options,
    ],
    [
      '@docusaurus/plugin-content-docs',
      {
        id: 'community',
        path: './docs/community',
        routeBasePath: 'community',
        editUrl: 'https://github.com/BoykaFramework/boyka-framework/edit/main/website/',
        editCurrentVersion: true,
        sidebarPath: require.resolve('./docs/community/sidebars.js'),
        showLastUpdateAuthor: true,
        showLastUpdateTime: true,
      } satisfies Docs.Options,
    ],
    [
      '@docusaurus/plugin-google-tag-manager',
      {
        containerId: 'GTM-MV7JWTN',
      } satisfies Tag.PluginOptions,
    ],
  ],
  presets: [
    [
      'classic',
      {
        docs: {
          showLastUpdateAuthor: true,
          showLastUpdateTime: true,
          path: './docs/framework-docs',
          routeBasePath: '/docs',
          sidebarPath: require.resolve('./docs/framework-docs/sidebars.js'),
          editUrl: 'https://github.com/BoykaFramework/boyka-framework/edit/main/website/',
        },
        blog: {
          showReadingTime: true,
          postsPerPage: 10,
          sortPosts: 'ascending',
          editUrl: 'https://github.com/BoykaFramework/boyka-framework/edit/main/website/blog/',
        },
        theme: {
          customCss: require.resolve('./src/css/custom.css'),
        },
        sitemap: {
          changefreq: 'daily',
          priority: 0.5,
        },
        googleAnalytics: {
          trackingID: 'G-1QT63P70E7',
          anonymizeIP: true,
        },
        gtag: {
          trackingID: 'G-1QT63P70E7',
          anonymizeIP: true,
        },
      } satisfies Preset.Options,
    ],
  ],
  themeConfig: {
    metadata: [
      {
        name: 'google-site-verification',
        content: 'i3x-x1g3IFyNUzu6z4EMXpwviY9o7FrCYkBXDNq90wE',
      },
    ],
    announcementBar: {
      id: 'star-the-repo',
      content: `⭐ Don't forget to Star the repo on 👉 
<a target='_blank' rel='noopener noreferrer' 
href='https://github.com/BoykaFramework/boyka-framework'>GitHub</a> ⭐ |
🤝 Support us by Sponsoring us on <a target='_blank' rel='noopener noreferrer' 
href='https://github.com/sponsors/BoykaFramework'>GitHub Sponsors</a> 🤝`,
      backgroundColor: '#ffb600',
      textColor: '#000000',
      isCloseable: false,
    },
    algolia: {
      appId: 'M6T24BY13H',
      apiKey: '11afb7c9d506ae1308ffd375ec5da5e1',
      indexName: 'boyka-framework',
    },
    colorMode: {
      defaultMode: 'dark',
      disableSwitch: false,
      respectPrefersColorScheme: true,
    },
    tableOfContents: {
      minHeadingLevel: 2,
      maxHeadingLevel: 6,
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
          label: 'Documentation',
        },
        {
          to: '/api/intro',
          sidebarId: 'api',
          label: 'API',
        },
        {
          to: '/contributing/welcome',
          sidebarId: 'contributing',
          label: 'Contributing',
        },
        {
          to: '/community/our-supporters',
          sidebarId: 'community',
          docId: 'community',
          label: 'Community',
        },
        { to: '/blog', label: 'Blogs' },
        {
          href: 'https://github.com/BoykaFramework/boyka-framework',
          position: 'right',
          className: 'header-github-link',
          'aria-label': 'GitHub repository',
        },
        {
          href: 'https://dub.sh/boyka-linkedin',
          position: 'right',
          className: 'header-linkedin-link',
          'aria-label': 'LinkedIn page',
        },
        {
          href: 'https://dub.sh/boyka-twitter',
          className: 'header-twitter-link',
          'aria-label': 'Twitter Profile',
          position: 'right',
        },
      ],
    },
    footer: {
      style: 'dark',
      links: [
        {
          title: 'Read This',
          items: [
            {
              label: 'Documentation',
              to: '/docs/intro',
            },
            {
              to: '/api/intro',
              label: 'API',
            },
            {
              to: '/contributing/welcome',
              label: 'Contributing',
            },
            {
              to: '/community/our-supporters',
              label: 'Community',
            },
          ],
        },
        {
          title: 'Follow us',
          items: [
            {
              label: 'Join our Discord server',
              href: 'https://dub.sh/boyka-discord',
            },
            {
              label: 'Twitter',
              href: 'https://dub.sh/boyka-twitter',
            },
            {
              label: 'LinkedIn',
              href: 'https://dub.sh/boyka-linkedin',
            },
            {
              label: 'StackOverflow',
              href: 'https://stackoverflow.com/questions/tagged/boyka-framework',
            },
          ],
        },
        {
          title: 'More',
          items: [
            {
              label: 'Blogs',
              to: '/blog',
            },
            {
              label: 'GitHub',
              href: 'https://github.com/BoykaFramework/boyka-framework',
            },
          ],
        },
      ],
      copyright: `Copyright ©️ ${new Date().getFullYear()} Wasiq Bhamla.
Built in 🇮🇳 with ❤️ using <a href="https://docusaurus.io/" target="_blank">Docusaurus</a>.`,
    },
    prism: {
      theme: themes.github,
      darkTheme: themes.dracula,
      additionalLanguages: ['java', 'json'],
    },
  } satisfies Preset.ThemeConfig,
};

export default config;
