BUILD_TARGET = ["main"]
DEPLOY_TARGET = ["main"]
namespace = "service-cloud-message"
switch(JOB_BASE_NAME) {
    case "main":
        SHORT_ENV           = "prod"
        PROJECT_NAME        = "${SHORT_ENV}-${namespace}"
        ENVIRONMENT         = "main"
        BUILD_PROJECT_NAME  = "prod-${namespace}"
    break
}

pipeline {
    stages {
        stage("Aproval") {
            steps {
                echo "Aproval"
            }
        }
    }
}