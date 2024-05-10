pipeline {

    agent any

    stages {
        stage("Clone the project") {
            steps {
                git branch: 'master', url: 'https://github.com/sauloddiniz/people-manager.git'
            }
        }

        stage('Display files') {
            steps {
                sh "ls -la"
            }
        }

        stage("Tests") {
            steps {
                sh "mvn test -Punit"
            }
        }

        stage('Build Docker image') {
            steps {
                sh "docker build --no-cache -t app ."
            }
        }
    }
}