FROM gradle:6.7.0-jdk14
WORKDIR /project
COPY build.gradle.kts settings.gradle.kts ./
RUN gradle -i clean build