pipeline {
    agent {
        label "lead-toolchain-skaffold"
    }
    environment {
      SKAFFOLD_DEFAULT_REPO = 'artifactory-artifactory.toolchain.svc.cluster.local:8081/liatrio'
    }
    stages {
        stage('Build') {
            steps {
              // Create and test image with skaffold
              container('skaffold') {
                script {
                  docker.withRegistry("https://${SKAFFOLD_DEFAULT_REPO}", 'jenkins-credential-artifactory') {
                    sh "skaffold build"
                  }
                }
              }
            }
        }
        stage ('Deploy to Staging') {
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
