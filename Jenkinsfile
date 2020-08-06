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
            try {
                sh "docker rm -vf \$(docker ps -a -q)"
                sh "docker rmi \$(docker images --filter=reference='sahlob/t*')"
            } catch (err) {
                echo err.getMessage()
            }
          customImage = docker.build registry
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


