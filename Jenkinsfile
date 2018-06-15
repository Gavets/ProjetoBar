pipeline {

    agent { label 'java8' }
	
    environment {
        EMAIL_RECIPIENTS = 'arthur.bueno@acad.pucrs.br'
    }
	
	stages {

        stage('Build with unit testing') {
    
        }
		
		stage('Testes') {

		}
		
		stage('Deploy') {

        }
	}
		
		post {
			always {
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
}
