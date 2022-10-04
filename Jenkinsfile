BUILD_TARGET = ["main", "dev"]
DEPLOY_TARGET = ["main", "dev"]
APP_NAME = "service-cloud-message"
APP_VERSION = '1.0.0'
switch(JOB_BASE_NAME) {
    case "main":
        ENVIRONMENT         = "prod"
        PROJECT_NAME        = "${ENVIRONMENT}-${APP_NAME}"
    break
    case "dev":
        ENVIRONMENT         = "dev"
        PROJECT_NAME        = "${ENVIRONMENT}-${APP_NAME}"
    break
}

pipeline {
   agent any
   tools {
       maven 'maven-3.6.3'
   }
   environment {
        DEPLOY_TO = "${GIT_BRANCH}"
   }

   stages {
        stage('Maven Install') {
            steps{
              sh "mvn -DskipTests install"
            }
        }
        stage('Docker Version') {
           steps{
             sh "docker -v"
           }
       }
       stage('Docker Version') {
          steps{
            sh "docker build -t ${ENVIRONMENT}-${APP_NAME}:${APP_VERSION} ."
          }
      }
   }
}