FROM adoptopenjdk/openjdk11:ubi-slim
RUN mkdir /usr/myapp
COPY ./build/libs/coupon-api-0.0.1-SNAPSHOT.jar /usr/myapp/app.jar
WORKDIR /usr/myapp

ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE}
ENV TZ="Asia/Seoul"
ENV SERVER_PORT=${SERVER_PORT}

EXPOSE ${SERVER_PORT}
ENTRYPOINT ["java","-Xms256m", "-Xmx512m","-jar","app.jar"]