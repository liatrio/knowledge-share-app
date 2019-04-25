pipeline {
    agent {
        label "jx-base"
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
    container('js-base') {
        withCredentials([string(credentialsId: 'sonarqube', variable: 'sonarqubeToken')]) {
            sh "echo 'sonar.login=${sonarqubeToken}' >> sonar.properties"
            sh "skaffold build"
        }
    }
}

def functionalTest(){
    container('maven') {
        sh "cd functional-tests && mvn clean test -DappUrl=${APP_URL}"
    }
}