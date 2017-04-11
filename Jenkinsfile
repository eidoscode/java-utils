pipeline {
  agent {
    docker {
      image 'maven'
    }
    
  }
  stages {
    stage('Build') {
      steps {
        script {
          mvn clean install
        }
        
      }
    }
  }
}