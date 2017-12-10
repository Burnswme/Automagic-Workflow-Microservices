pipeline {
    checkout scm
        stages {
          stage('Auth') {
              steps {
                  sh 'cd aw-auth'
                  sh 'mvn clean package -DskipTests'
                  sh 'docker build -t burnswme/auth .'
                  sh 'cd ..'
              }
           }
        }
}
