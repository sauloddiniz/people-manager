node {
    stages {
        stage("Clone the project") {
            steps {
                git branch: 'master', url: 'https://github.com/sauloddiniz/people-manager.git'
            }
        }

        stage("Compilation") {
            steps {
                sh "mvn clean install -DskipTests"
            }
        }

        stage("Tests and Deployment") {
            stages {
                stage("Runing unit tests") {
                    steps {
                        sh "mvn test -Punit"
                    }
                }

                stage('Build Docker image') {
                    steps {
                        script {
                            def app = docker.build("meu_app")
                        }
                    }
                }

                stage('Push Docker Image to ECR'){
                    steps{
                        withAWS(region:'us-east-1',credentials:'aws-credentials-id'){
                            script{
                                docker.withRegistry('https://<seu-id>.dkr.ecr.us-east-1.amazonaws.com', 'ecr:us-east-1:aws-iam-role') {
                                    app.push("${env.BUILD_NUMBER}")
                                    app.push("latest")
                                }
                            }
                        }
                    }
                }

                stage('Deploy to ECS') {
                    steps {
                        ecsDeploy(awsRegion: 'us-east-1', cluster: 'ecs-cluster', service: 'app-service', taskDefinition: 'app-task', taskDefinitionFile: 'task-definition.json', image: [containerName: 'container', imageName: '${env.registry}/${env.repository}:${env.tag}'], desiredCount: 2)
                    }
                }
            }
        }
    }
}