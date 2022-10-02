BUILD_TARGET = ["main"]
DEPLOY_TARGET = ["main"]
APP_NAME = "service-cloud-message"
ENVIRONMENT = "prod"
APP_VERSION = '1.0.0'
switch(JOB_BASE_NAME) {
    case "main":
        ENVIRONMENT           = "prod"
        PROJECT_NAME        = "${ENVIRONMENT}-${APP_NAME}"
        ENVIRONMENT         = "main"
        BUILD_PROJECT_NAME  = "prod-${APP_NAME}"
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
        stage('Docker Build') {
            steps {
                script{
                    sh ("docker build -t ${ENVIRONMENT}-${APP_NAME}:${APP_VERSION} .")
                }
            }
        }
//         stage('Compress Artifact') {
//            steps {
//                script{
//                    sh ("tar -czpf ${APP_NAME}.tgz * --exclude-vcs --exclude=Jenkinsfile --exclude='.ansible/' --exclude=${APP_NAME}.tar.gz; mv ${APP_NAME}.tgz /tmp/")
//                }
//            }
//        }
//        stage('list file on tmp') {
//             steps {
//                 script {
//                     sh "ls /tmp"
//                     sh "echo ${JOB_BASE_NAME}"
//
//                 }
//             }
//        }
//        stage("Deploy App") {
//            steps {
//                  withCredentials([sshUserPrivateKey(credentialsId: 'b188d8b1-7dce-429e-99e1-82ff15559618', keyFileVariable: 'keyfile')]) {
//                        dir("${WORKSPACE}/.ansible") {
//                             sh """ ansible-playbook main.yml -i hosts.ini -u vagrant --private-key=${keyfile} --extra-vars '{"target":"${ENVIRONMENT}"}' """
//                             sh "ansible all --list-hosts"
//                        }
//                  }
//            }
//        }
   }
}