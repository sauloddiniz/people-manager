node {
    stage("Clone the project") {
        git branch: 'master', url: 'https://github.com/sauloddiniz/people-manager.git'
    }

    stage('Display files') {
        sh "ls -la"
    }

    stage("Tests and Build image docker") {
        stage("Runing unit tests") {
          sh "mvn test -Punit"
        }
        stage('Build Docker image') {
          sh "docker build -t app ."
        }
    }
}