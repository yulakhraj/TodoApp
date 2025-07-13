pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }
        
        stage('Unit Tests') {
            steps {
                bat 'mvn clean test -Dmaven.test.failure.ignore=true'
            }
            post {
                always {
                    junit(
                        allowEmptyResults: true,
                        testResults: 'target/surefire-reports/*.xml',
                        skipPublishingChecks: true
                    )
                }
            }
        }
        
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv(installationName: 'sonarqube') {
                    bat 'mvn clean verify sonar:sonar -Dsonar.host.url=http://localhost:9090 -Dsonar.projectKey=TodoWebApp -Dsonar.java.binaries=target/classes -Dsonar.coverage.exclusions=**/*Test*.java'
                }
                echo 'SonarQube Analysis completed. Check results at http://localhost:9090/dashboard?id=TodoWebApp'
            }
        }
        
        stage('Integration Tests') {
            steps {
                bat 'mvn verify -Dmaven.test.failure.ignore=true'
            }
            post {
                always {
                    junit(
                        allowEmptyResults: true,
                        testResults: 'target/failsafe-reports/*.xml',
                        skipPublishingChecks: true
                    )
                }
            }
        }
        
        stage('Package') {
            steps {
                bat 'mvn package -DskipTests'
            }
            post {
                success {
                    archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
                }
            }
        }

        stage('Deploy to Artifactory') {
            steps {
                script {
                    def server = Artifactory.server('Artifactory') // Use your Artifactory server ID
                    def buildInfo = Artifactory.newBuildInfo()
                    def rtMaven = Artifactory.newMavenBuild()
                    rtMaven.resolver server: server, releaseRepo: 'libs-release', snapshotRepo: 'libs-snapshot'
                    rtMaven.deployer server: server, releaseRepo: 'libs-release-local', snapshotRepo: 'libs-snapshot-local'
                    rtMaven.run pom: 'pom.xml', goals: 'deploy', buildInfo: buildInfo
                    server.publishBuildInfo buildInfo
                }
            }
        }
    }
    
    post {
        always {
            cleanWs()
        }
        success {
            echo 'Build completed successfully!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}
