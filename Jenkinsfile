pipeline {

    agent any

    stages {
        stage("Clone the project") {
            steps {
                echo 'Clone project'
                git branch: 'master', url: 'https://github.com/sauloddiniz/people-manager.git'
            }
        }

        stage('Display files') {
            steps {
                echo 'List files'
                sh "ls -la"
            }
        }

        stage("Tests") {
            steps {
                echo 'Beginning tests'
                sh "mvn test -Punit"
            }
        }

        stage('Build Docker image') {
            steps {
                echo 'Beginning docker build image'
                sh "docker build --no-cache -t app ."
            }
        }
    }
}