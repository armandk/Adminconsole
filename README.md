# FROM SPRING BOOT WAR TO DOCKER IMAGE


This is a Spring Boot Web Application creates from a Docker image. The Web App uses  Primefaces vs 5.0
1. The Maven Plugin for Docker used is called  fabric8  (spottify is another one)
2. Docker file uses a predefined Tomcat version, instead of the Spring Boot Default	
3. Build the image:  $>  mvn clean install -Dmaven.test.skip=false  docker:build
4. Run the image as viewing the logs:  $>  docker run -p 8080:8080 --name mywebapp adminconsole
5. Open your browser at http://IP_ADDRESS:8080/myebapp

