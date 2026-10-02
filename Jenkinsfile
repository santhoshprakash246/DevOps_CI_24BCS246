pipeline {
    agent any

    tools {
        maven 'Maven-3.9.14'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/santhoshprakash246/DevOps_CI_24BCS246.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Result') {
            steps {
                echo 'Blood Bank project build and tests completed successfully.'
            }
        }
    }
}