pipeline {
    agent {
        docker { image 'maven:3-alpine' }
    }
    stages {
        stage('Code pull') {
            steps {
                checkout scm
            }
        }
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
