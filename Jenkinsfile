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
                        ssh -o StrictHostKeyChecking=no ubuntu@54.232.197.138
                    '''
                }
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
                        scp target/people-manager-0.0.1-SNAPSHOT.war ubuntu@54.232.197.138:~/people-manager.war
                        ssh ubuntu@54.232.197.138 "sudo mv ~/people-manager.war /opt/tomcat/webapps/"
                    '''

                }
            }
        }
    }
}