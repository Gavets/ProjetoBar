pipeline {
    agent any 
    stages {
        stage('Build') {
            steps {
                echo 'Passo de build!'
                sh "mvn clean package"
            }
        }
        stage('Teste') {
            steps {
                echo 'Passo de teste automatizado!' 
            }
        }
        stage('Deploy') {
            steps {
                echo 'Passo de deploy!' 
            }
        }
    }
}
