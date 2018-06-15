pipeline {
    agent any
    stages {
        stage('Build') { 
            steps {
                deleteDir()
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
                bat 'chmod -r 777 "C:/Program Files (x86)/Jenkins"'
                bat 'move "C:/Program Files (x86)/Jenkins/workspace/GCS - Teste/ProjetoBar/target/ProjetoBar-1.0-SNAPSHOT-jar-with-dependencies.jar" C:/Users/arthu/intellijProject/ProjetoBar/target/'
            }
        }
    }
}
