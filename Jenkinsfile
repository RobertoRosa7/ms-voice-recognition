BUILD_TARGET = ["main"]
DEPLOY_TARGET = ["main"]
namespace = "service-cloud-message"
buildAgentLabel = "maven-java-11"
switch(JOB_BASE_NAME) {
    case "main":
        SHORT_ENV           = "prod"
        PROJECT_NAME        = "${SHORT_ENV}-${namespace}"
        ENVIRONMENT         = "main"
        BUILD_PROJECT_NAME  = "prod-${namespace}"
    break
}

pipeline {
   agent any
   tools {
        maven 'Maven 3.6.0'
        jdk 'jdk11'
   }
   stages {
        stage('Build') {
            steps{
                echo "this is first"
            }
        }
   }
}