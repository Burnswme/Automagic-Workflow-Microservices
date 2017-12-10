pipeline {
    stages {
      stage('Auth') {
          steps {
              checkout scm
              sh 'cd aw-auth'
              sh 'mvn clean package -DskipTests'
              sh 'docker build -t burnswme/auth .'
              sh 'cd ..'
          }
       }
    }
}
