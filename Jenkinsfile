pipeline {
    agent any
    stages {
        stage('Build') { 
            steps {
                echo 'Gerando build...'
                bat 'git clone https://github.com/Gavets/ProjetoBar.git ProjetoBar'
                bat 'mvn -f ./ProjetoBar clean package'
            }
        }
        stage('Teste Unitario') { 
            steps {
                echo 'Rodando testes unitarios...'
                bat 'mvn -f ./ProjetoBar test'
            }
        }
        stage('Deploy') { 
            steps {
                echo 'Em construcao...'                
            }
        }
    }
    post {
        always {
            deleteDir()
        }
    }
}
