BUILD_TARGET = ["main"]
DEPLOY_TARGET = ["main"]
namespace = "service-cloud-message"
switch(JOB_BASE_NAME) {
    case "main":
        SHORT_ENV           = "prod"
        PROJECT_NAME        = "${SHORT_ENV}-${namespace}"
        ENVIRONMENT         = "main"
        BUILD_PROJECT_NAME  = "prod-${namespace}" // Used the homologation enviroment for construction
    break
}

pipeline {
    stages {
        stage("Aproval") {
            when {
                environment name:"DEPLOY_TO", value:"main"
            }
            steps {
                //echo sh(script: "env|sort", returnStdout: true)
                echo "Aproval"
            }
        }
    }
}