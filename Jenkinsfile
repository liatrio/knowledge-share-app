pipeline {
    agent {
        label "jenkins-jx-base"
    }
    environment {
      SKAFFOLD_DEFAULT_REPO = 'docker.artifactory.liatr.io/liatrio'
      TEAM_NAME = 'flywheel'
      ORG = 'liatrio'
    }
    stages {
        stage('Build') {
            steps {
                container('jx-base') {
                    withCredentials([string(credentialsId: 'sonarqube', variable: 'sonarqubeToken')]) {
                        sh "echo 'sonar.login=${sonarqubeToken}' >> sonar.properties"
                    }
                    script {
                      docker.withRegistry("https://${SKAFFOLD_DEFAULT_REPO}", 'artifactory-credentials') {
                          sh "skaffold build -p jenkins -q -o '{{ (index .Builds 0).Tag }}' > image_name"
                      }
                    }
                    sh 'curl -s https://ci-tools.anchore.io/inline_scan-v0.3.3 | bash -s -- -d Dockerfile $(< image_name)'
                }

                mavenParsePom()
                sendBuildEvent(eventType:'build')
            }
        }
        stage ('Deploy to Staging') {
            steps {
              echo 'Need to add deploy to stagin env here'
              sendBuildEvent(eventType:'deploy')
            }
        }
        stage ('Test Staging Deployment') {
            steps {
              echo 'Temp testing stage here'
              sendBuildEvent(eventType:'test')
            }
        }
        stage ('Deploy to Production') {
            steps {
              echo 'Need to add deploy to production env here'
              sendBuildEvent(eventType:'deploy')
            }
        }
        stage ('Test Prod Deployment') {
            steps {
              echo 'Temp testing stage here'
              sendBuildEvent(eventType:'test')
            }
        }
    }
    post {
        always {
            cleanWs()
        }
        fixed {
            sendHealthyEvent()
        }
        regression {
            sendUnhealthyEvent()
        }
    }    
}

import groovy.json.JsonOutput

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

def sendBuildEvent(requestParams) {
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


def functionalTest(){
    container('maven') {
        sh "cd functional-tests && mvn clean test -DappUrl=${APP_URL}"
    }
}

def sendHealthyEvent(String unit = "MILLISECONDS")  {
    def divisor = ["HOURS": 360000, "MINUTES": 60000, "SECONDS": 1000 , "MILLISECONDS": 1]
    long completedTimeStamp = currentBuild.getTimeInMillis()
    long prevTimeStamp = getTimeOfFailedHealthyBuild(currentBuild)
    recoveryTime = completedTimeStamp - prevTimeStamp
    sendBuildEvent(eventType:'state-change', state: 'healthy', priorDuration: recoveryTime  )
    return (completedTimeStamp - prevTimeStamp) / divisor[unit]
}

@NonCPS
long getTimeOfFailedHealthyBuild(currentBuild) {
  def build = currentBuild //current build is fixed
  while(build.getNumber() > 1 && build.getPreviousBuild().getResult() != 'SUCCESS') {
    build = build.getPreviousBuild()
  }
  println "build that failed first ${build.getNumber()}"
  return build.getTimeInMillis()
}

def sendUnhealthyEvent(String unit = "MILLISECONDS") {
    def divisor = ["HOURS": 360000, "MINUTES": 60000, "SECONDS": 1000 , "MILLISECONDS": 1]
    long completedTimeStamp = currentBuild.getTimeInMillis()
    long prevTimeStamp = getTimeOfFailedBuild(currentBuild)
    recoveryTime = completedTimeStamp - prevTimeStamp
    echo "last failed build was: ${recoveryTime} ago "
    sendBuildEvent(eventType:'state-change', state: 'unhealthy', priorDuration: recoveryTime  )
    return recoveryTime / divisor[unit]
} 

@NonCPS
long getTimeOfFailedBuild(currentBuild) {
  def build = currentBuild.getPreviousBuild() //start looking at previous build
  while(build.getNumber() > 1 && build.getResult() != 'FAILURE') {
      build = build.getPreviousBuild()
  }
  println "Last failed build was ${build.getNumber()}"
  return build.getTimeInMillis()
}
