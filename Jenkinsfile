pipeline {
    agent {
        label "lead-toolchain-skaffold"
    }
    stages {
        stage('Build') {
            steps {
              container('skaffold') {
                script {
                  sh "skaffold build --quiet > image.json"
                }
              }
            }
        }
        stage ('Deploy to Staging') {
            environment { 
              TILLER_NAMESPACE = "${env.stagingNamespace}"
              INGRESS_DOMAIN   = "${env.stagingDomain}"
            }
            steps {
              container('skaffold') {
                script {
                  sh "skaffold deploy -a image.json -n ${TILLER_NAMESPACE}"
                }
              }
            }
        }
        stage ('Test Staging Deployment') {
            agent {
                label "lead-toolchain-maven"
            }
            steps {
              container('maven') {
                sh "mvn clean test -DappUrl=https://knowledge-share-app.${env.stagingDomain} -f functional-tests"
              }
            }
        }
        stage ('Deploy to Production') {
           environment {
             TILLER_NAMESPACE = "${env.productionNamespace}"
             INGRESS_DOMAIN   = "${env.productionDomain}"
           }
            steps {
              container('skaffold') {
                script {
                  sh "skaffold deploy -a image.json -n ${TILLER_NAMESPACE}"
                }
              }
            }
        }
    }
    post {
        always {
            cleanWs()
        }
    }    
}
