pipeline {
    agent any
    stages {
        stage('Git Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/ansouconde93/sonarQubeJenkins.git'    
		            echo "Code Checked-out Successfully!!";
            }
        }
        
        stage('Package') {
            steps {
                bat 'mvn package'    
		            echo "Maven Package Goal Executed Successfully!";
            }
        }
        
        stage('JUNit Reports') {
            steps {
                    junit 'target/surefire-reports/*.xml'
		                echo "Publishing JUnit reports"
            }
        }
        
        stage('Jacoco Reports') {
            steps {
                  jacoco()
                  echo "Publishing Jacoco Code Coverage Reports";
            }
        }

	stage('SonarQube analysis') {
            steps {
		// Change this as per your Jenkins Configuration
                withSonarQubeEnv('sonarqubeTest') {
                    bat 'mvn package sonar:sonar -Dsonar.login=root -Dsonar.password=root'                   
                }
            }
        }

	stage("Quality gate") {
            steps {
                waitForQualityGate abortPipeline: true
            }
        }
        
    }
    post {
        
        success {
            echo 'This will run only if successful'
        }
        failure {
            echo 'This will run only if failed'
        }
    
    }
}


// sonarQubeAccessToken: sqa_6bc30e5d1d9df6c5fc04f97e3c40f57f035b0bb7

// Analyze "sonarQubeJenkins" sqp_ad7927c74e9f14ce61005d1c4a6457525ed530ac
