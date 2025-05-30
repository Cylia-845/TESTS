pipeline {
    agent any

    tools {
        jdk 'jdk-17'             // Nom du JDK configuré dans Jenkins
        maven 'Maven-3.9.9'      // Nom de Maven configuré dans Jenkins
    }

    environment {
        SONAR_PROJECT_KEY = 'demo'         // Nom du projet dans SonarQube
        SONARQUBE_SCANNER_HOME = tool 'SonarScanner'  // Nom du scanner configuré dans Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Cylia-845/TESTS'
            }
        }

        stage('Build and Test') {
            steps {
                   sh 'mvn clean verify'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') { // Nom du serveur SonarQube configuré dans Jenkins
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

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
        success {
            echo 'Build et analyse réussis !'
        }
        failure {
            echo 'Build ou analyse échoués !'
        }
    }
}
