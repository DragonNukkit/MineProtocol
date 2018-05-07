pipeline {
    tools {
        maven 'Maven 3'
        jdk 'OracleJDK 8'
    }

    agent any

    options {
        timestamps()
        timeout(time: 5, unit: 'MINUTES')
    }

    triggers {
        githubPush()
    }

    stages {
        stage ('check-commit') {
            steps {
                script {
                    env.CI_SKIP = "false"
                    result = sh (script: "git log -1 | grep '(?s).[CI[-\\s]SKIP].*'", returnStatus: true)
                    if (result == 0) {
                        env.CI_SKIP = "true"
                        error "'[CI-SKIP]' found in git commit message. Aborting."
                    }
                }
            }
        }
        stage ('compile') {
            steps {
                echo 'Compiling...'
                sh 'mvn -B clean verify'
            }
            post {
                always {
                    echo 'Archiving artifacts...'
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
            }
        }
        stage ('deploy') {
            when {
                branch "master"
            }
            steps {
                echo 'Master branch detected, deploying to maven repository...'
                sh 'mvn -DskipTests deploy'
            }
        }
    }

    post {
        always {
            script {
                if (env.CI_SKIP == "true") {
                    currentBuild.result = 'NOT_BUILT'
                }
            }
        }
        success {
            githubNotify description: 'The jenkins build was successful',  status: 'SUCCESS'
        }
        failure {
            githubNotify description: 'The jenkins build failed',  status: 'FAILURE'
        }
    }
}
