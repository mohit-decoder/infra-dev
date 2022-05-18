pipeline{
 agent any 
 stages{
 stage("git-pull"){
 steps{
 sh 'sudo apt-get update -y'
 sh 'sudo apt-get install git -y'
 git credentialsId: 'gitnew', url: 'git@github.com:usertan123/student-ui.git'
 }
 }
 stage("Maven-Build"){
 steps{
 sh 'sudo apt-get update -y'
 sh 'sudo apt-get install maven curl unzip -y'
 sh 'mvn clean package'
 }
 }
            // stage("push-artifact"){
            // steps{
            // sh 'curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"'
            // sh 'unzip awscliv2.zip'
            // sh 'sudo ./aws/install'
            // sh 'aws s3 sync **/*.war s3://dev-artifact'
            // }
            // stage("Dev-Deployment"){
            // steps{
            // sh 'ssh -i ec2.pem ubuntu@'
            // sh 'curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"'
            // sh 'unzip awscliv2.zip'
            // sh 'sudo ./aws/install'
            // sh 'aws s3 sync s3://dev-artifact/**.war /opt/tomcat/webapps/' 
            // sh './opt/tomcat/bin/startup.sh' 
            // }
            // }
 }
}