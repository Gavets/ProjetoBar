pipeline {
    agent any
    stages {
        stage('Build') { 
            steps {
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
        always {
            deleteDir()
        }
        success {
            mail bcc: '', body: '(${currentBuild.fullDisplayName})', cc: '', from: '', replyTo: '', subject: 'Pipeline GCS - Status', to: 'arthur.bueno@acad.pucrs'
        }
        unstable {
            sendEmail("Unstable");
        }
        failure {
            sendEmail("Failed");
        }
    }
}
