pipeline {
    agent any

    stages {
        stage("Clone the project") {
            steps {
                echo 'Clone project'
                git branch: 'master', url: 'https://github.com/sauloddiniz/people-manager.git'
            }
        }

        stage('Verify SSH connect') {
            steps {
                sshagent(credentials: ['ec2-user']) {
                    sh '''
                        ssh -o StrictHostKeyChecking=no ec2-user@18.228.36.202
                    '''
                }
            }
        }

        stage("Tests") {
            steps {
                echo 'Beginning tests'
                sh "mvn test -Punit"
            }
        }

        stage('Copy jar to remote server') {
            steps {
                sshagent(credentials: ['ec2-user']) {
                    sh '''
                        scp target/people-manager-0.0.1-SNAPSHOT.jar ec2-user@18.228.36.202:/home/ec2-user
                    '''
                }
            }
        }
    }
}