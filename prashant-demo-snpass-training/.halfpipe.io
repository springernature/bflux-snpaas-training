team: production
pipeline: prashant-demo-snpass-training

feature_toggles:
- update-pipeline

triggers:
- type: git
  watched_paths:
  - prashant-demo-snpass-training
  
tasks:
- type: run
  name: build
  script: build.sh
  save_artifacts:
  - build/distributions
  docker:
    image: openjdk:8-jdk-stretch

- type: deploy-cf
  name: deploy-test
  api: ((cloudfoundry.api-snpaas))
  space: bflux-training
  manual_trigger: true
  manifest: manifest-files/dev/manifest.yml
  deploy_artifact: build/distributions/demo-snpass-training.zip
