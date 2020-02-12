https://hub.docker.com/_/rabbitmq  
https://www.rabbitmq.com/networking.html

    sudo docker pull rabbitmq:management-alpine  
    sudo docker run --hostname velo-rabbitmq --name velo-rabbitmq -p 5672:5672 -p 15672:15672 -d rabbitmq:management-alpine  
    sudo docker logs velo-rabbitmq  