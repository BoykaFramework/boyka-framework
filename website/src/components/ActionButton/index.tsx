import React from 'react';
import clsx from 'clsx';
import Link from '@docusaurus/Link';

export const ActionButton = ({ href, type, target, text }): JSX.Element => {
  return (
    <Link className={clsx('button', type)} to={href} target={target}>
      {text}
    </Link>
  );
};
