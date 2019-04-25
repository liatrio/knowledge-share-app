pipeline {
    agent {
        label "jenkins-maven-java11"
    }
    environment {
      DOCKER_REGISTRY = 'docker.artifactory.liatr.io'
    }
    stages {
        stage('Build') {
            steps {
                mavenBuild()
            }
        }
        stage('Sonar Scan') {
            steps {
                sonarScan()
            }
        }
        stage('Publish Artifactory') {
            steps {
              publishArtifactory()
            }
        }
    }
}

def mavenBuild() {
    container('maven') {
        sh "mvn clean install"
    }
}

def sonarScan() {
    container('maven') {
        withCredentials([string(credentialsId: 'sonarqube', variable: 'sonarqubeToken')]) {
            sh "mvn sonar:sonar -Dsonar.login=${sonarqubeToken}"
        }
    }
}

def functionalTest(){
    container('maven') {
        sh "cd functional-tests && mvn clean test -DappUrl=${APP_URL}"
    }
}

def publishArtifactory() {
  container('maven') {
    docker.withRegistry("https://${DOCKER_REGISTRY}", 'artifactory-credentials') {
        sh "skaffold build -p build"
    }
  }
}
    
