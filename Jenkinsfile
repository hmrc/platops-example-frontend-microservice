pipeline {
    agent {
        label 'default-slave'
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building '
                build job: 'platops-example-frontend-microservice'
            }
        }
        stage('Build a library') {
            steps {
                echo 'Triggering another job'
                build job: 'platops-example-library'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}