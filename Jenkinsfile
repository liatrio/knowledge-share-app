pipeline {
    agent {
        label "jenkins-jx-base"
    }
    environment {
      DOCKER_REGISTRY = 'docker.artifactory.liatr.io'
      TEAM_NAME = 'flywheel'
      ORG = 'liatrio'
    }
    stages {
        stage('Build') {
            steps {
                //skaffoldBuild()
                echo 'skipping for now'
            }
        }
        stage ('Send Build Event') {
            steps {
                mavenParsePom()
                sendBuildEvent(eventType:'build')
            }
        }
    }
}

def mavenParsePom() {
  container('maven') {
    script {
      def pom = readMavenPom file: 'pom.xml'
      def appVersion = pom.version.split("-")[0] + "-${GIT_COMMIT[0..10]}"
      env.VERSION = appVersion
      env.APP_NAME = pom.artifactId
      env.GROUP_ID = pom.groupId
    }
  }
}

def sendBuildEvent() {
  requestParams.teamName = env.TEAM_NAME ? env.TEAM_NAME : env.ORG
  requestParams.appName = env.APP_NAME
  requestParams.branch = env.BRANCH_NAME
  requestParams.groupID = env.GROUP_ID
  requestParams.versionNumber = env.VERSION
  requestParams.gitCommit = env.GIT_COMMIT
    
  def requestBody = JsonOutput.toJson(requestParams)
  def url = env.elasticUrl ? env.elasticUrl : "localhost:9000"
    
  def response = httpRequest acceptType: 'APPLICATION_JSON', contentType: 'APPLICATION_JSON', httpMode: 'POST', requestBody: requestBody, url: url
  println('Status: ' + response.status)
  println('Response: ' + response.content)
}


def skaffoldBuild() {
    container('jx-base') {
        withCredentials([string(credentialsId: 'sonarqube', variable: 'sonarqubeToken')]) {
            sh "echo 'sonar.login=${sonarqubeToken}' >> sonar.properties"
        }
        docker.withRegistry("https://${DOCKER_REGISTRY}", 'artifactory-credentials') {
            sh "skaffold build -p jenkins"
        }
    }
}

def functionalTest(){
    container('maven') {
        sh "cd functional-tests && mvn clean test -DappUrl=${APP_URL}"
    }
}
