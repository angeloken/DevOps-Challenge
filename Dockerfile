FROM richarvey/nginx-php-fpm

WORKDIR /var/www/html

COPY . . 

EXPOSE 443 80

CMD ["/start.sh"]


