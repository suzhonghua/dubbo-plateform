FROM java:8
ADD /target/auth.jar /
VOLUME /opt/volumes/auth
EXPOSE 9701
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev","/auth.jar"]
