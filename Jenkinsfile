pipeline {
    agent any

    tools {
        jdk 'jdk-17'         // Nom JDK configuré dans Jenkins
        maven 'Maven-3.9.9'  // Nom Maven configuré dans Jenkins
    }

    environment {
        SONAR_PROJECT_KEY = 'demo' // Même nom que celui dans SonarQube
    }

    stages {
        stage('Checkout') {
            steps {
                // Récupération du code depuis le dépôt Git
                git branch: 'main', url: 'https://github.com/Cylia-845/TESTS'
            }
        }

        stage('Build and Test') {
            steps {
                // Exécution de la construction et des tests
                catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                    sh 'mvn clean verify'
                }
            }
        }

        stage('SonarQube Analysis') {
            environment {
                SONARQUBE_SCANNER_HOME = tool 'SonarScanner' // Nom configuré dans Jenkins
            }
            steps {
                // Analyse avec SonarQube
                withSonarQubeEnv('SonarQube') { // Nom du serveur configuré dans Jenkins
                    sh """
                        ${env.SONARQUBE_SCANNER_HOME}/bin/sonar-scanner \
                        -Dsonar.projectKey=${env.SONAR_PROJECT_KEY} \
                        -Dsonar.projectName=SpringBootDemo \
                        -Dsonar.projectVersion=1.0 \
                        -Dsonar.sources=src \
                        -Dsonar.java.binaries=target/classes \
                        -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
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
            // Publication des rapports JUnit
            junit 'target/surefire-reports/*.xml'
        }
    }
}
