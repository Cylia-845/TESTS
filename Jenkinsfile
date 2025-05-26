pipeline {
    agent any
    tools {
        jdk 'jdk-21'         // nom JDK configuré dans Jenkins
        maven 'Maven-3.9.9'  // nom Maven configuré dans Jenkins
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/Cylia-845/TESTS'
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean package' // Pour Windows PowerShell / CMD
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
