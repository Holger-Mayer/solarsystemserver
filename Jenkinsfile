#!/usr/bin/env groovy
pipeline {
    agent any

    environment {
       PROJECT = 'SOL'
    
    }
    
    stages {
        stage('Checkout') {
            steps {

                git url: 'https://github.com/Holger-Mayer/solarsystemserver.git', branch: 'main'

            }
        }

        stage('Build') {
            steps {

                sh 'mvn clean install'
            }
        }
        stage('Run JUnit Tests') {
            steps {

                sh 'mvn test'
            }
            post {
                always {
                junit '**/target/surefire-reports/*.xml'
            }
            }
        }
        stage('Coverage') {
            steps {
        sh 'mvn jacoco:report'
            }        
            post {
                always { 
                    jacoco execPattern:'**/target/jacoco.exec',
                    classPattern:'**/target/classes',
                    sourcePattern:'**/src/main/java',
                    exclusionPattern:'**/target/**/*'
                } 
            } 
        }
        
        stage('reportJira') {
            steps {
                withCredentials([string(credentialsId:'ZEPHYRSCALETOKEN',variable:"TOKEN")]) {
                // Run Maven on a Unix agent.
                  sh 'sh testupload.sh ${PROJECT}  ${TOKEN}'
                }
            }
        }
    }
}