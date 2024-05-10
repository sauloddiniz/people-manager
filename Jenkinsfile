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
                        ssh -o StrictHostKeyChecking=no ec2-user@18.230.129.80
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
                        scp target/people-manager-0.0.1-SNAPSHOT.war ec2-user@18.230.129.80:~/people-manager.war
                        ssh ec2-user@18.230.129.80 "sudo mv ~/people-manager.war /opt/tomcat/webapps/"
                    '''

                }
            }
        }
    }
}