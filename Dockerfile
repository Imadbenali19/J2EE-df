FROM openjdk:17
add target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]