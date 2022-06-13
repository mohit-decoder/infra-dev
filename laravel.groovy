pipeline{
 agent any 
  stages{
     stage("git-pull"){
     steps{
       sh 'sudo apt-get update -y'
        sh 'sudo apt-get install git -y'
       }
     }
     stage("Dev-Deployment"){
      steps{
        withCredentials([sshUserPrivateKey(credentialsId: 'laravel', keyFileVariable: 'laravel')]) {
         sh'''
         ssh -i ${laravel}  -o StrictHostKeyChecking=no ubuntu@52.39.143.121<<EOF
         sudo apt-get update
         sudo add-apt-repository ppa:ondrej/php
         sudo apt-get install -y php7.2-cli php7.2-mysql php7.2-fpm php7.2-xml php7.2-curl php7.1-mcrypt php7.2-mbstring
         php -v
         sudo apt-get install mysql-server -y
         sudo apt-get install nginx-y
         sudo apt-get install git 
         sudo apt-get install zip unzip
         git clone https://github.com/usertan123/aws-laravel.git html
         cd html
         ls
         curl -sS https://getcomposer.org/installer | sudo php -- --install-dir=/usr/local/bin --filename=composer
         composer install --no-dev
         cp .env.example .env
         php artisan key:generate
         cd /etc/nginx/sites-available
         sudo curl -O https://laravel-sites-available.s3.us-west-2.amazonaws.com/sites-available.txt 
         sudo mv sites-available.txt default
         cat default
         cd /var/www/
         sudo rm -rf html
         cd ~
         ls
         sudo mv html /var/www/
         cd /var/www/html
         ls 
         sudo chmod -R 777 storage
         sudo service nginx restart
         '''
        }
        }
    }
  }
}
  