# ---------- Etapa de build ----------
FROM gradle:8.14.3-jdk17 AS build
WORKDIR /home/gradle/project

# Copiamos el repo completo (sin depender del gradlew del repo)
COPY --chown=gradle:gradle . .

# Compilamos el JAR (sin tests para agilizar y sin daemon para logs claros)
RUN gradle --no-daemon clean build -x test

# ---------- Etapa de runtime ----------
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copiamos el JAR construido
COPY --from=build /home/gradle/project/build/libs/*.jar /app/app.jar

# Render/PAAS inyectan PORT; Spring lo toma si tienes server.port=${PORT:8080}
ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java","-Dserver.port=${PORT}","-jar","/app/app.jar"]