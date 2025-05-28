pipeline {
    agent any
    tools {
        jdk 'jdk-17'         // nom JDK configuré dans Jenkins
        maven 'Maven-3.9.9'  // nom Maven configuré dans Jenkins
    }
    environment {
        SONAR_PROJECT_KEY = 'demo' // même nom que celui dans SonarQube
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

        stage('Test') {
    steps {
        catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
            sh 'mvn test'
        }
    }
}

        stage('SonarQube Analysis') {
            environment {
                SONARQUBE_SCANNER_HOME = tool 'SonarScanner' // nom configuré dans Jenkins
            }
            steps {
                withSonarQubeEnv('SonarQube') { // nom du serveur configuré dans Jenkins
                    sh """
                        ${env.SONARQUBE_SCANNER_HOME}/bin/sonar-scanner \
                        -Dsonar.projectKey=${env.SONAR_PROJECT_KEY} \
                        -Dsonar.projectName=SpringBootDemo \
                        -Dsonar.projectVersion=1.0 \
                        -Dsonar.sources=src \
                        -Dsonar.java.binaries=target/classes
                    """
                }
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
         always {
        junit 'target/surefire-reports/*.xml'
    }
    }
}
