FROM gradle:6.4.1-jdk14
WORKDIR /project
COPY build.gradle.kts settings.gradle.kts ./
RUN gradle -i clean build