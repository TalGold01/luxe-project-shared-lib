# Luxe Project Shared Library

This repository contains shared Jenkins pipeline functions and utilities for the Luxe Project CI/CD pipelines.

## Structure

- `vars/` – Global functions callable from Jenkinsfiles (e.g., `dockerUtils.groovy`)
- `src/` – Optional helper classes for pipeline logic
- `resources/` – Optional templates or files

## Usage

1. Add this repository as a Shared Library in Jenkins:
   - Go to **Manage Jenkins → Configure System → Global Pipeline Libraries**
   - Name: `luxe-shared-lib`
   - Default version: `main`
   - Include @Library in your Jenkinsfile:
     ```groovy
     @Library('luxe-shared-lib') _
     dockerUtils.buildAndPush(...)
     ```
