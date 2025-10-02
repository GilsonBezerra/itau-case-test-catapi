# Etapa 1: build do jar
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: imagem final leve
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copia o jar construído
COPY --from=build /app/target/*.jar app.jar

# Variáveis padrão (podem ser sobrescritas no docker-compose.yml)
ENV SPRING_PROFILES_ACTIVE=default
ENV JAVA_OPTS=""

EXPOSE 8080

# Start da aplicação
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

