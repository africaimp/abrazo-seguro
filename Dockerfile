# ---------- Etapa de build ----------
FROM gradle:8.9-jdk21 AS build
WORKDIR /home/gradle/project

# Copiamos el repo completo (con dueño gradle para evitar permisos)
COPY --chown=gradle:gradle . .

# Construimos el jar sin tests (y sin daemon para logs claros)
RUN gradle --no-daemon clean build -x test

# ---------- Etapa de runtime ----------
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copiamos el jar generado
# Ajusta el patrón si tu jar tiene otro nombre
COPY --from=build /home/gradle/project/build/libs/*.jar /app/app.jar

# Render/PAAS suelen inyectar PORT; Spring lo lee si tienes server.port=${PORT:8080}
ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java","-Dserver.port=${PORT}","-jar","/app/app.jar"]