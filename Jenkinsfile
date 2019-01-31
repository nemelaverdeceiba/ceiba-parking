
pipeline {
    //Donde se va a ejecutar el Pipeline
    agent {
        label 'Slave_Induccion'
    }
    //Opciones específicas de Pipeline dentro del Pipeline
    options {
        //Mantener artefactos y salida de consola para el # específico de ejecuciones
        //recientes del Pipeline.
        buildDiscarder(logRotator(numToKeepStr: '3'))
        //No permitir ejecuciones concurrentes de Pipeline
        disableConcurrentBuilds()
    }
    //Una sección que define las herramientas para “autoinstalar” y poner en la PATH
    tools {
        jdk 'JDK8_Centos' //Preinstalada en la Configuración del Master
        gradle 'Gradle4.5_Centos' //Preinstalada en la Configuración del Master
    }
    //Aquí comienzan los “items” del Pipeline
    stages {
        stage('Checkout') {
            steps {
                echo "------------>Checkout<------------"
                checkout([$class: 'GitSCM', branches: [[name: '*/develop']],
                    doGenerateSubmoduleConfigurations: false, extensions: [], gitTool:
                    'Git_Centos', submoduleCfg: [], userRemoteConfigs: [[credentialsId:
                            'GitHub_nemelaverdeceiba', url:
                            'https://github.com/nemelaverdeceiba/ceiba-parking'
                        ]
                    ]
                ])
            }
        }
        stage('Unit Tests') {
            steps {
                echo "------------>Unit Tests<------------"
                //sh 'gradle --b ./build.gradle test'
                 sh 'gradle --b  ceiba-parking/build.gradle test'
            }
        }
        stage('Integration Tests') {
            steps {
                echo "------------>Integration Tests<------------"
            }
        }
        stage('Static Code Analysis') {
            steps {
                echo '------------>Análisis de código estático<------------'
                withSonarQubeEnv('Sonar') {
                   // sh "${tool name: 'SonarScanner',type: 'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner"
                      sh "${tool name: 'SonarScanner', type:'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -Dproject.settings=sonar-project.properties"
                }
        }
    }
    stage('Build') {
        steps {
            echo "------------>Build<------------"
            //Construir sin tarea test que se ejecutó previamente 
            //sh 'gradle --b ./build.gradle build -x test' 
             sh 'gradle --b ceiba-parking/build.gradle build -x test' 
        }
    }
}

    
}
