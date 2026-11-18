FROM maven:3.10.0-eclipse-temurin-21-noble

WORKDIR /workspace

CMD ["mvn", "-version"]