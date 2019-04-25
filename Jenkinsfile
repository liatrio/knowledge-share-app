pipeline {
    agent {
        label "jenkins-jx-base"
    }
    environment {
      DOCKER_REGISTRY = 'docker.artifactory.liatr.io'
    }
    stages {
        stage('Build') {
            steps {
                skaffoldBuild()
            }
        }
    }
}

def skaffoldBuild() {
    container('jx-base') {
        withCredentials([string(credentialsId: 'sonarqube', variable: 'sonarqubeToken')]) {
            sh "echo 'sonar.login=${sonarqubeToken}' >> sonar.properties"
        }
        docker.withRegistry("https://${DOCKER_REGISTRY}", 'artifactory-credentials' {
            sh "skaffold build -p jenkins"
        }
    }
}

def functionalTest(){
    container('maven') {
        sh "cd functional-tests && mvn clean test -DappUrl=${APP_URL}"
    }
}
