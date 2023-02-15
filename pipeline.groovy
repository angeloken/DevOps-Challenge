pipeline {

  environment {
    dockerimagename = "angeloken/test"
    dockerImage = ""
  }

  agent any

  stages {

    stage('Checkout Source') {
      steps {
        git 'https://github.com/angeloken/DevOps-Challenge.git'
      }
    }

    stage('Build image') {
      steps{
        script {
          dockerImage = docker.build dockerimagename
        }
      }
    }

    stage('Pushing Image') {
      environment {
               registryCredential = 'dockerhublogin'
           }
      steps{
        script {
          docker.withRegistry( 'https://registry.hub.docker.com', registryCredential ) {
            dockerImage.push("latest")
          }
        }
      }
    }

    stage('Deploying App to Kubernetes') {
      steps {
        script {
          kubernetesDeploy(configs: "sosmed-app.yaml", kubeconfigId: "kubernetes")
        }
      }
    }

  }

}

pipeline {

  environment {
    dockerimagename = "angeloken/test"
    dockerImage = ""
  }

  agent any

  stages {

    stage('Checkout Source') {
      steps {
        git 'https://github.com/angeloken/sosial-media.git'
      }
    }

    stage('Build image') {
      steps{
        script {
          dockerImage = docker.build dockerimagename
        }
      }
    }

    stage('Pushing Image') {
      environment {
               registryCredential = 'dockerhublogin'
           }
      steps{
        script {
          docker.withRegistry( 'https://registry.hub.docker.com', registryCredential ) {
            dockerImage.push("${BUILD_NUMBER}")
          }
        }
      }
    }
    stage('Yaml Source') {
      steps {
         git 'https://github.com/angeloken/DevOps-Challenge.git'
      }
    }
    stage('Deploy to Server') {
        steps{
          sh "sed -i 's+DOCKER_TAG+${BUILD_NUMBER}+g' deployappx.yaml"
          sh "kubectl apply -f deployappx.yaml -n dev"        
        }
      }
  }

}