pipeline {
    agent any
    stages {
        stage('Build') { 
            steps {
                deleteDir()
                echo 'Gerando build...'
                bat 'git clone https://github.com/Gavets/ProjetoBar.git ProjetoBar'
                bat 'mvn -Dmaven.test.skip=true -f ./ProjetoBar clean package'
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
				bat 'if not exist "%USERPROFILE%\\intellijProject\\ProjetoBar\\target" mkdir "%USERPROFILE%\\intellijProject\\ProjetoBar\\target"'
				bat 'copy /y "ProjetoBar\\target\\*.jar" "%USERPROFILE%\\intellijProject\\ProjetoBar\\target"'
            }
        }
    }
}
