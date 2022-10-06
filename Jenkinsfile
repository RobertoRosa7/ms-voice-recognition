APP_NAME = "service-cloud-message"
VERSION = '1.0.0'
CONTAINER="con"
switch(JOB_BASE_NAME) {
	case "main":
		ENVIRONMENT="prod"
	break
	case "dev":
		ENVIRONMENT="dev"
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
				sh ("mvn -DskipTests install")
			}
		}
		stage('Docker Build') {
			steps{
				sh ("docker build -t ${ENVIRONMENT}-${APP_NAME}:${VERSION} .")
			}
		}
		stage("Docker Login and Push Image") {
      steps {
        script{
          withCredentials([usernamePassword(credentialsId: '484e578a-bdcf-4ff6-9f30-3e38aead052f', passwordVariable: 'docker_pass', usernameVariable: 'docker_user')]) {
            sh("docker login -u ${docker_user} -p ${docker_pass}")
            sh("""docker tag ${ENVIRONMENT}-${APP_NAME}:${VERSION} "${docker_user}/${ENVIRONMENT}-${APP_NAME}:${VERSION}" """)
            sh("""docker push "${docker_user}/${ENVIRONMENT}-${APP_NAME}:${VERSION}" """)

            try {
							sh ("""docker run -d --network mongo -p 8081:8081 --name ${ENVIRONMENT}-${CONTAINER}-${APP_NAME} "${docker_user}/${ENVIRONMENT}-${APP_NAME}:${VERSION}" """)
						} catch(Exception e) {
							sh ("docker rm -f ${ENVIRONMENT}-${CONTAINER}-${APP_NAME}")
							sh ("""docker run -d --network mongo -p 8081:8081 --name ${CONTAINER}-${ENVIRONMENT}-${APP_NAME} "${docker_user}/${ENVIRONMENT}-${APP_NAME}:${VERSION}" """)
						}
          }
        }
      }
    }
    stage("Docker Clean") {
    	steps {
    		script{
    			sh("docker image rm ${ENVIRONMENT}-${APP_NAME}:${VERSION}")
    		}
    	}
		}
	}
}