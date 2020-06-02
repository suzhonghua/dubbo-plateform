FROM java:8
ADD /target/upms-biz.jar /
VOLUME /opt/volumes/upms
EXPOSE 9702
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev","/upms-biz.jar"]