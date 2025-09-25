// vars/dockerUtils.groovy
// This file contains functions callable from Jenkinsfile

def buildAndTagImage(String serviceName, String dockerHubImage, String nexusImage) {
    echo "Building Docker image for ${serviceName}"
    sh "docker-compose build ${serviceName}"
    
    echo "Tagging Docker image for DockerHub"
    sh "docker tag ${dockerHubImage} ${dockerHubImage}:latest"
    
    echo "Tagging Docker image for Nexus"
    sh "docker tag ${dockerHubImage} ${nexusImage}"
}

def pushToDockerHub(String dockerHubImage, String credentialsId='docker-hub') {
    echo "Pushing ${dockerHubImage} to DockerHub"
    withCredentials([usernamePassword(credentialsId: credentialsId, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
        sh """
            echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
            docker push ${dockerHubImage}
        """
    }
}

def pushToNexus(String nexusImage, String nexusRegistry, String credentialsId='nexus-creds') {
    echo "Pushing ${nexusImage} to Nexus at ${nexusRegistry}"
    withCredentials([usernamePassword(credentialsId: credentialsId, usernameVariable: 'NEXUS_USER', passwordVariable: 'NEXUS_PASS')]) {
        sh """
            echo "$NEXUS_PASS" | docker login -u "$NEXUS_USER" --password-stdin $nexusRegistry
            docker push ${nexusImage}
        """
    }
}
