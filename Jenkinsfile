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
              TILLER_NAMESPACE = 'jon-test-staging'
            }
            steps {
              container('skaffold') {
                script {
                  sh "skaffold deploy"
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
            steps {
              echo 'Need to add deploy to production env here'
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
