team: bflux
pipeline: basin-cf-snpaas-training
feature_toggles:
- update-pipeline
triggers:
- type: git
  watched_paths:
  - basin-cf-snpaas-training
tasks:
- type: run
  name: build
  script: ./mvnw package
  save_artifacts:
  - target/basin-cf-snpaas-training-0.0.1-SNAPSHOT.jar
  docker:
    image: openjdk:8-jdk-stretch

- type: deploy-cf
  name: deploy-dev
  api: ((cloudfoundry.api-dev))
  space: bflux-training
  manual_trigger: false
  manifest: manifest-files/local/manifest.yml
  deploy_artifact: target/basin-cf-snpaas-training-0.0.1-SNAPSHOT.jar
  notify_on_success: true
