pipeline {
    agent any 
    stages {
        stage('Build') { 
            steps {
                deleteDir()
                echo 'Gerando build'
                bat 'git clone https://github.com/Gavets/ProjetoBar.git'
            }
        }
        stage('Teste Unitario') { 
            steps {
                echo 'Rodando testes unitários'
                bat 'mvn -f ./ProjetoBar clean test'
            }
        }
        stage('Deploy') { 
            steps {
                echo 'Em construcao...'
                
            }
        }
    }
}
