BUILD_TARGET = ["main"]
DEPLOY_TARGET = ["main"]
APP_NAME = "service-cloud-message"
ENVIRONMENT = "prod"
APP_VERSION = '1.0.0'
switch(JOB_BASE_NAME) {
    case "main":
        ENVIRONMENT         = "prod"
        PROJECT_NAME        = "${ENVIRONMENT}-${APP_NAME}"
    break
}

pipeline {
   agent any
   tools {
       maven 'maven-3.6.0'
   }
   environment {
        DEPLOY_TO = "${GIT_BRANCH}"
   }
   stages {
        stage('Maven Install') {
            steps{
                echo DEPLOY_TO
                sh "mvn -v"
                sh "mvn -DskipTests install"
            }
        }
        stage('Docker version') {
            steps {
                script{
                    sh ("docker -v")
                }
            }
        }
   }
}