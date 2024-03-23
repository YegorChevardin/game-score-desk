FROM gradle:8.6-jdk21
WORKDIR ./app
COPY ./ ./
RUN gradle clean build
ENTRYPOINT ["java", "-jar", "build/libs/game-score-desk.jar"]