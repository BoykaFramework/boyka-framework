import stylistic from '@stylistic/eslint-plugin';
import tsParser from '@typescript-eslint/parser';
import js from '@eslint/js';
import prettierRecommended from 'eslint-plugin-prettier/recommended';
import tseslint from 'typescript-eslint';
import react from 'eslint-plugin-react';
import globals from 'globals';
import { includeIgnoreFile } from '@eslint/compat';
import path from 'node:path';
import { fileURLToPath } from 'node:url';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);
const gitignorePath = path.resolve(__dirname, '.gitignore');

export default [
  js.configs.recommended,
  prettierRecommended,
  ...tseslint.configs.recommended,
  includeIgnoreFile(gitignorePath),
  {
    languageOptions: {
      ecmaVersion: 2022,
      sourceType: 'module',
      parser: tsParser,
      parserOptions: {
        ecmaFeatures: {
          jsx: true,
        },
      },
      globals: {
        ...globals.browser,
      },
    },
    files: ['website/**/*.ts', 'website/**/*.tsx', 'website/**/*.js', 'website/**/*.jsx'],
    plugins: {
      '@stylistic/ts': stylistic,
      '@stylistic/js': stylistic,
      react,
    },
    rules: {
      '@stylistic/ts/indent': ['error', 2],
      '@stylistic/js/linebreak-style': ['error', 'unix'],
      '@stylistic/ts/quotes': ['error', 'single'],
      '@stylistic/ts/semi': ['error', 'always'],
      '@stylistic/js/max-len': ['error', { code: 100 }],
      '@typescript-eslint/no-explicit-any': ['off'],
      'react/react-in-jsx-scope': 'off',
      'react/prop-types': 'off',
      'prefer-promise-reject-errors': ['off'],
      'react/jsx-filename-extension': ['warn', { extensions: ['.tsx'] }],
      'no-return-assign': ['off'],
      '@typescript-eslint/explicit-function-return-type': [
        'error',
        {
          allowExpressions: true,
        },
      ],
      'no-shadow': 'off',
      '@typescript-eslint/no-shadow': ['error'],
      'import/prefer-default-export': 'off',
      'prettier/prettier': ['error', { singleQuote: true }],
    },
  },
];
