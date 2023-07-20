import React from 'react';
import clsx from 'clsx';
import Link from '@docusaurus/Link';
import useBaseUrl from '@docusaurus/useBaseUrl';

export const ActionButton = ({ href, type, target, text }): JSX.Element => {
  return (
    <Link className={clsx('button', type)} to={useBaseUrl(href)} target={target}>
      {text}
    </Link>
  );
};
