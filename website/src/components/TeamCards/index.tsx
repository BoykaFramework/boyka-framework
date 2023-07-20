import React from 'react';
import { TeamCard, TeamMember } from '../TeamCard';
import Data from '../../data/teams.json';
import styles from './style.module.css';

export const TeamCards = (): JSX.Element => {
  return (
    <section className={styles.teamSection}>
      {Data.members.map((member: TeamMember) => (
        <TeamCard
          key={member.name}
          avatar={member.avatar}
          name={member.name}
          github={member.github}
          twitter={member.twitter}
          linkedin={member.linkedin}
        >
          {member.title}
        </TeamCard>
      ))}
    </section>
  );
};
