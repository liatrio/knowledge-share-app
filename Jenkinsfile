pipeline {
    agent {
        label "lead-toolchain-skaffold"
    }
    stages {
        stage('Build') {
            steps {
              container('skaffold') {
                script {
                  sh "skaffold build"
                }
              }
            }
        }
        stage ('Deploy to Staging') {
            environment { 
              TILLER_NAMESPACE = "${env.stagingNamespace}"
            }
            steps {
              container('skaffold') {
                script {
                  sh "skaffold deploy --namespace ${TILLER_NAMESPACE}"
                }
              }
            }
        }
        stage ('Test Staging Deployment') {
            steps {
              container('maven') {
                sh "cd functional-tests && mvn clean test -DappUrl=${APP_URL}"
              }
            }
        }
        stage ('Deploy to Production') {
           environment {
             TILLER_NAMESPACE = "${env.productionNamespace}"
           }
            steps {
              container('skaffold') {
                script {
                  sh "skaffold deploy"
                }
              }
            }
        }
        stage ('Test Prod Deployment') {
            steps {
              echo 'Temp testing stage here'
            }
        }
    }
    post {
        always {
            cleanWs()
        }
    }    
}
