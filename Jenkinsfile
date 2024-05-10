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
                        ssh -o StrictHostKeyChecking=no ubuntu@52.67.136.147
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
                        scp target/people-manager-0.0.1-SNAPSHOT.jar ubuntu@52.67.136.147:/home/ubuntu
                    '''
                }
            }
        }

       stage('Execute project on remote server') {
            steps {
                sshagent(['ec2-user']) {
                    sh '''
                        ssh ubuntu@52.67.136.147 << EOF
                        cd /home/ubuntu
                        nohup java -jar people-manager-0.0.1-SNAPSHOT.jar &
                    '''
                }
            }
        }

    }
}