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
              //INGRESS_DOMAIN = "${env.stagingDomain}"
              INGRESS_DOMAIN = "${env.stagingNamespace}.lead.sandbox.liatr.io"
            }
            steps {
              container('skaffold') {
                script {
                  sh "skaffold deploy -a image.json -n ${TILLER_NAMESPACE}"
              //    sh "helm upgrade -i knowledge-share-app charts/knowledge-share-app --tiller-namespace ${env.stagingNamespace} --namespace ${env.stagingNamespace} --set ingress.domain=${INGRESS_DOMAIN}"
                }
              }
            }
        }
        stage ('Test Staging Deployment') {
            steps {
              container('maven') {
                //sh "cd functional-tests && mvn clean test -DappUrl=${APP_URL}"
                echo 'temp comment'
              }
            }
        }
        stage ('Deploy to Production') {
           environment {
             TILLER_NAMESPACE = "${env.productionNamespace}"
              //INGRESS_DOMAIN = "${env.productionDomain}"
             INGRESS_DOMAIN = "${env.productionNamespace}.lead.sandbox.liatr.io"
           }
            steps {
              container('skaffold') {
                script {
              #    sh "skaffold deploy -a image.json -n ${TILLER_NAMESPACE}"
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
