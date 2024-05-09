node {
  stage("Clone the project") {
    git branch: 'master', url: 'https://github.com/sauloddiniz/people-manager.git'
  }

  stage("Compilation") {
    sh "./mvnw clean install -DskipTests"
  }

  stage("Tests and Deployment") {
    stage("Runing unit tests") {
      sh "./mvnw test -Punit"
    }
    stage("Deployment") {
      sh 'nohup ./mvnw spring-boot:run -Dserver.port=8080 &'
    }
  }
}