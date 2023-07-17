import Link from '@docusaurus/Link';
import { FaTwitter, FaGithub, FaLinkedin } from 'react-icons/fa6';
import React from 'react';
import styles from './style.module.css';
import clsx from 'clsx';

interface Social {
  userName: string;
}

export interface TeamMember {
  avatar: string;
  name: string;
  github: string;
  twitter: string;
  linkedin: string;
  title: string;
}

const TwitterLink = ({ userName }: Social): JSX.Element => {
  if (!userName) return null;
  return (
    <Link className={styles.twitterProfile} href={`https://twitter.com/${userName}`}>
      <FaTwitter />
    </Link>
  );
};

const GitHubLink = ({ userName }: Social): JSX.Element => {
  if (!userName) return null;
  return (
    <Link className={styles.githubProfile} href={`https://github.com/${userName}`}>
      <FaGithub />
    </Link>
  );
};

const LinkedInLink = ({ userName }: Social): JSX.Element => {
  if (!userName) return null;
  return (
    <Link className={styles.linkedinProfile} href={`https://www.linkedin.com/in/${userName}`}>
      <FaLinkedin />
    </Link>
  );
};

export const TeamCard = ({ name, avatar, github, twitter, linkedin, children }): JSX.Element => {
  return (
    <article className={clsx('avatar', styles.teamProfile)}>
      <img className='avatar__photo avatar__photo--lg' src={avatar} />
      <div className='avatar__intro'>
        <h4 className='avatar__name'>
          {name}
          <GitHubLink userName={github} />
          <TwitterLink userName={twitter} />
          <LinkedInLink userName={linkedin} />
        </h4>
        <small className='avatar__subtitle'>{children}</small>
      </div>
    </article>
  );
};
