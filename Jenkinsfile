pipeline {
    agent any
    
    environment {
        EMAIL_RECIPIENTS = 'arthur.bueno@acad.pucrs.br'
    }
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
        // Always runs. And it runs before any of the other post conditions.
        always {
            // Let's wipe out the workspace before we finish!
            deleteDir()
        }
        success {
            sendEmail("Successful");
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

def sendEmail(status) {
    mail(
            to: "$EMAIL_RECIPIENTS",
            subject: "Build GCS - " + status + " (${currentBuild.fullDisplayName})",
        body: "Changes:\n " + ${BUILD_DISPLAY_NAME} + "\n\n Check console output at: $BUILD_URL/console" + "\n")
}
