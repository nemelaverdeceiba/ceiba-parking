pipeline {
 //Donde se va a ejecutar el Pipeline
	 agent {
	 	label 'Slave_Induccion'
	 }
	
	 //Opciones específicas de Pipeline dentro del Pipeline
	 options {
		//Mantener artefactos y salida de consola para el # específico de ejecuciones recientes del Pipeline.
		buildDiscarder(logRotator(numToKeepStr: '5'))
		
		//No permitir ejecuciones concurrentes de Pipeline
		disableConcurrentBuilds()
	 }
	 
	  //Una sección que define las herramientas para “autoinstalar” y poner en la PATH
	 tools {
	 	jdk 'JDK8_Centos' //Preinstalada en la Configuración del Master
		gradle 'Gradle4.5_Centos' //Preinstalada en la Configuración del Master
	 }
	 
	 //Aquí comienzan los “items” del Pipeline
	 stages{
	 	stage('Checkout') {
	 		steps{
		 			echo "------------>Checkout<------------"
		 			checkout([$class: 'GitSCM', 
		 			branches: [[name: '*/develop']],
					doGenerateSubmoduleConfigurations: false, 
					extensions: [], 
					gitTool: 'Git_Centos', 
					submoduleCfg: [], 
					userRemoteConfigs: 
						[[
							credentialsId: 'GitHub_nemelaverdeceiba', 
							url: 'https://github.com/nemelaverdeceiba/ceiba-parking'
						]]])
						
					sh 'gradle clean'
	 			}
	 	}
	 	
	 	stage('Compile') {
			steps{
					echo "------------>Compile<------------"
					sh 'gradle --b ./build.gradle compileJava'
			}
		}
	
		stage('Unit Tests') {
			steps{
		 			echo "------------>Unit Tests<------------"
		 			//sh 'gradle --stacktrace test'
					//j7unit '**/build/test-results/test/*.xml' //aggregate test results - JUnit
					//step( [ $class: 'JacocoPublisher' ] )
				       //// sh 'gradle --b ./build.gradle test'
					sh 'gradle --b ./build.gradle clean test'
	 			}
	 	}
	 
	 	stage('Integration Tests') {
	 		steps {
	 				echo "------------>Integration Tests<------------"
	 			}
		}
	 
	 	stage('Static Code Analysis') {
	 		steps{
		 			echo '------------>Análisis de código estático<------------'
		 			withSonarQubeEnv('Sonar') {
					sh "${tool name: 'SonarScanner', type: 'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner"
					}
	 			}
	 	}
	 	
	 	stage('Build') {
	 		steps {
		 			echo "------------>Build<------------"
		 			sh 'gradle --b ./build.gradle build -x test'
	 			}
		}
		 
		 
	 }
	
	// Pasos posteriores
	post {
		// Ante fallo
		failure {
			echo 'This will run only if failed'
			mail (to: 'nelson.laverde@ceiba.com.co', subject: "Failed Pipeline:${currentBuild.fullDisplayName}", 
			      body: "Something is wrong with ${env.BUILD_URL}")
		}
		// Ante exito
		success {
			echo 'This will run only if successful'
			junit '**/jacoco/test-results/*.xml'
		}
}
}
