pipeline {
  agent none
  stages {
    stage('Build') {
      agent {
        label "lead-toolchain-skaffold"
      }
      steps {
        container('skaffold') {
          sh "skaffold build --quiet > image.json"
        }
      }
    }
    stage ('Deploy to Staging') {
      agent {
        label "lead-toolchain-skaffold"
      }
      environment { 
        TILLER_NAMESPACE = "${env.stagingNamespace}"
        INGRESS_DOMAIN   = "${env.stagingDomain}"
      }
      steps {
        container('skaffold') {
          sh "skaffold deploy -a image.json -n ${TILLER_NAMESPACE}"
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
    stage ('Approval') {
      agent none
      when {
          branch 'master'
      }
      input {
          message "Deploy to production?"
      }
    }
    stage ('Deploy to Production') {
      when {
          branch 'master'
      }
      agent {
        label "lead-toolchain-skaffold"
      }
      environment {
        TILLER_NAMESPACE = "${env.productionNamespace}"
        INGRESS_DOMAIN   = "${env.productionDomain}"
      }
      steps {
        container('skaffold') {
          sh "skaffold deploy -a image.json -n ${TILLER_NAMESPACE}"
        }
      }
    }
  }
}
