FROM openjdk:8

RUN mkdir -p /usr/bs-app
WORKDIR /usr/bs-app
COPY gradle-6.5-bin.zip /usr/local

RUN \
    cd /usr/local && \
    unzip gradle-6.5-bin.zip && \
    rm gradle-6.5-bin.zip


ENV GRADLE_HOME=/usr/local/gradle-6.5
ENV PATH=$PATH:$GRADLE_HOME/bin

COPY bs-layer/bs-api/bs-api-1.0-SNAPSHOT.jar /usr/bs-app/bs-api-1.0-SNAPSHOT.jar

RUN gradle build

EXPOSE 8080/tcp

ENTRYPOINT ["java", "-jar", "/usr/bs-app/build/libs/bs-api-1.0-SNAPSHOT.jar"]