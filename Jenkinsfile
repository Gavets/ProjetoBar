pipeline {

    agent { label 'java8' }
	
    environment {
        EMAIL_RECIPIENTS = 'arthur.bueno@acad.pucrs.br'
    	}
	
	stages {
        	stage('Build') {
			steps{
				print 'Stage de build'
        		}
		}
		
		stage('Testes') {
			steps{
				print 'Stage de Teste Unitário'
			}
		}
		
		stage('Deploy') {
			steps{
				print 'Stage de Deploy'
			}
        	}
	}
}
