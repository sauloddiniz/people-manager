pipeline {
    agent any

    environment {
        registry = "saulodias/people-manager"
        registryCredential = 'docker_hub'
        dockerImage = ''
    }

    stages {
        stage("Clone the project") {
            steps {
                echo 'Clone project'
                git branch: 'master', url: 'https://github.com/sauloddiniz/people-manager.git'
            }
        }

        stage("Tests") {
            steps {
                echo 'Beginning tests'
                sh "mvn clean install"
            }
        }

         stage('Building our image') {
            steps{
                script {
                    dockerImage = docker.build registry
                }
            }
        }

        stage('Deploy our image') {
            steps{
                script {
                    docker.withRegistry( '', registryCredential ) {
                    dockerImage.push()
                    }
                }
            }
        }

    }
}