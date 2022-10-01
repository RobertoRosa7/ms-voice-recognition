BUILD_TARGET = ["main"]
DEPLOY_TARGET = ["main"]
app_name = "service-cloud-message"
namespace = "service-cloud-message"
buildAgentLabel = "maven-java-11"
switch(JOB_BASE_NAME) {
    case "main":
        SHORT_ENV           = "prod"
        PROJECT_NAME        = "${SHORT_ENV}-${app_name}"
        ENVIRONMENT         = "main"
        BUILD_PROJECT_NAME  = "prod-${app_name}"
    break
}

pipeline {
   agent any
   tools {
       maven 'maven-3.6.0'
   }
   environment {
        appName="${SHORT_ENV}-${app_name}-${appVersion}"
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
   }
}