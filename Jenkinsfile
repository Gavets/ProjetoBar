pipeline {
    agent any
    
    environment {
        EMAIL_RECIPIENTS = 'mahmoud.romeh@test.com'
    }
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
                emailext body: '', recipientProviders: [culprits()], subject: '', to: 'arthur.bueno@acad.pucrs.br'
                
            }
        }
    }
    post {
        // Always runs. And it runs before any of the other post conditions.
        always {
            // Let's wipe out the workspace before we finish!
            deleteDir()
        }
        success {
            sendEmail("Successful");
        }
        unstable {
            sendEmail("Unstable");
        }
        failure {
            sendEmail("Failed");
        }
}
