pipeline {
  environment {
    registry = "gustavoapolinario/docker-test"
    registryCredential = "dockerhub"
  }
  agent any
  stages {
    stage('Cloning Git') {
      steps {
        git 'https://github.com/sah-lob/tsahlobbot.git'
      }
    }
    stage('Building image') {
      steps{
        script {
          docker.build registry + ":$BUILD_NUMBER"
        }
      }
    }
    stage('Deploy Image') {
      steps{
        script {
          docker.withRegistry( 'https://registry.hub.docker.com', 'docker-hub-credentials') {
            // dockerImage.push()
            dockerImage.push("${env.BUILD_NUMBER}")
            dockerImage.push("latest")
          }
        }
      }
    }
  }
}