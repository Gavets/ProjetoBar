pipeline {

    agent { label 'java8' }
	
    environment {
        EMAIL_RECIPIENTS = 'arthur.bueno@acad.pucrs.br'
    	}
	
	stages {

        	stage('Build') {
    			print 'Stage de build'
        	}
		
		stage('Testes') {
			print 'Stage de Teste Unitário'
		}
		
		stage('Deploy') {
			print 'Stage de Deploy'
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
