BUILD_TARGET = ["main"]
DEPLOY_TARGET = ["main"]
APP_NAME = "service-cloud-message"
switch(JOB_BASE_NAME) {
    case "main":
        SHORT_ENV           = "prod"
        PROJECT_NAME        = "${SHORT_ENV}-${APP_NAME}"
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
        stage('Compress Artifact') {
           steps {
               script{
                   sh ("tar -czpf ${APP_NAME}.tgz target --exclude-vcs --exclude=Jenkinsfile --exclude='.ansible/' --exclude=${APP_NAME}.tar.gz; mv ${APP_NAME}.tgz /tmp/")
               }
           }
       }
       stage('list file on tmp') {
            steps {
                script {
                    sh "ls /tmp"
                }
            }
       }
       stage("Deploy App") {
           steps {
                 withCredentials([sshUserPrivateKey(credentialsId: 'vagrant', keyFileVariable: 'keyfile')]) {
                       dir("${WORKSPACE}/.ansible") {
                           sh """
                               ansible-playbook main.yml -i hosts.ini -u vagrant --private-key=${keyfile} --extra-vars '{ \
                                   "target":"${SHORT_ENV}",
                               }'
                           """
                       }
                 }
           }
       }
   }
}