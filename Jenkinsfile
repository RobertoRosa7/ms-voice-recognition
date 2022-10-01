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
       maven 'maven-3.6.0'
   }
   stages {
        stage('Build') {
            steps{
                sh "mvn -v"
                sh "mvn -DskipTests install"
            }
        }
   }
}