import React from 'react';
import GitHubButton from 'react-github-btn';

type GitHubButtonType = {
  id: number;
  userId: string;
  repoName: string;
  type: 'Watch' | 'Star' | 'Fork' | 'Follow';
};

const GitHubCountButton = ({
  userId,
  repoName = undefined,
  type = 'Follow',
}: GitHubButtonType): JSX.Element => {
  let repoUrl = `${userId}`;
  let label = `${type}`;
  let text = `${type}`;

  if (type !== 'Follow' && repoName) {
    repoUrl += `/${repoName}`;
    label += ` ${repoUrl}`;
  }
  if (type === 'Watch') repoUrl += '/subscription';
  else if (type === 'Fork') repoUrl += '/fork';
  else if (type === 'Follow') text += ` @${userId}`;
  return (
    <GitHubButton
      href={`https://github.com/${repoUrl}`}
      data-color-scheme='no-preference: dark_dimmed; light: dark_dimmed; dark: dark_dimmed;'
      data-size='large'
      data-show-count='true'
      aria-label={`${label} on GitHub`}
    >
      {text}
    </GitHubButton>
  );
};

export { GitHubCountButton };
