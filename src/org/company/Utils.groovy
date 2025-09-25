// src/org/company/Utils.groovy
// Optional utilities for Jenkins pipelines

package org.company

class Utils {
    static void printEnv() {
        println "Printing important environment variables:"
        println "DOCKERHUB_REGISTRY: ${System.getenv('DOCKERHUB_REGISTRY')}"
        println "NEXUS_REGISTRY: ${System.getenv('NEXUS_REGISTRY')}"
        println "PROJECT_NAME: ${System.getenv('PROJECT_NAME')}"
    }

    static String getImageName(String service, String registry, String projectName) {
        return "${registry}/${projectName}-${service}:latest"
    }
}
