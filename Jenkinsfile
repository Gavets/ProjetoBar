pipeline {
    environment {
        EMAIL_RECIPIENTS = 'arthur.bueno@acad.pucrs.br'
    	}
	
	stages {
        	stage('Build') {
			steps{
				echo 'Stage de build'
        		}
		}
		
		stage('Testes') {
			steps{
				echo 'Stage de Teste Unitário'
			}
		}
		
		stage('Deploy') {
			steps{
				echo 'Stage de Deploy'
			}
        	}
	}
}
