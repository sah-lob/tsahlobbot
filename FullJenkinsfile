pipeline {
  environment {
    registry = "sahlob/t${env.BUILD_NUMBER}"
    registryCredential = "dockerhub"
    customImage = ""
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
           customImage = docker.build registry
        }
      }
    }
    stage('push image') {
      steps{
        script {
          docker.withRegistry( 'https://registry.hub.docker.com', 'docker-hub-credentials') {
             sh "docker push ${registry}"
          }
        }
      }
    }
   stage('run docker image') {
       steps{
           script {
               docker.withRegistry( 'https://registry.hub.docker.com', 'docker-hub-credentials') {
                  sh "docker run -d -p 8085:8085 ${registry}"
                }
           }
       }
   }
  }
}