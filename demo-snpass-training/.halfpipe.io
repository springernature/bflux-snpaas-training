team: production
pipeline: demo-snpass-training-ani

triggers:
- type: git
  branch: master
  watched_paths:
  - demo-snpass-training
  
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
  space: bookservices-dev
  manual_trigger: true
  manifest: manifest-files/dev/manifest.yml
  deploy_artifact: build/distributions/demo-snpass-training.zip
  notify_on_success: true
  
  
