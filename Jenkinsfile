pipeline {
    agent any

    environment {
        registry = "saulodias/repository"
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

        stage('Copy war file to tomcat server') {
            steps {
                sshagent(credentials: ['ec2-user']) {
                    sh '''
                        scp target/people-manager-0.0.1-SNAPSHOT.war ubuntu@54.232.197.138:/home/ubuntu
                    '''
                }
            }
        }
    }
}