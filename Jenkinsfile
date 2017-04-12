pipeline {
  agent {
    docker {
      image 'maven'
    }
    
  }
  stages {
    stage('whoami') {
      steps {
        sh '''whoami
'''
      }
    }
    stage('pwd') {
      steps {
        sh '''pwd
'''
      }
    }
    stage('Java Version') {
      steps {
        sh '''javac -version
'''
      }
    }
    stage('mvn --version') {
      steps {
        sh '''mvn --version
'''
      }
    }
    stage('cat releases') {
      steps {
        sh '''cat /etc/*-rele*
'''
      }
    }
    stage('mvn clean install') {
      steps {
        sh 'mvn clean install'
      }
    }
  }
}