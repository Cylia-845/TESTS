pipeline {
    agent any
    tools {
        jdk 'jdk-17'         // nom JDK configuré dans Jenkins
        maven 'Maven-3.9.9'  // nom Maven configuré dans Jenkins
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Cylia-845/TESTS'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
    }
    post {
        success {
            echo 'Build réussi !'
        }
        failure {
            echo 'Build échoué !'
        }
    }
}
