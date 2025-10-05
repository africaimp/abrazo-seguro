# Abrazo Seguro — Hackathon App

**Abrazo Seguro** es una API REST (Spring Boot + PostgreSQL) creada en un hackatón para registrar **pacientes**, sus **datos de contacto** y los **servicios** que reciben.

---

## Requisitos
- JDK 17  
- Gradle 8.14.3  
- Docker (opcional)

---

## Variables de entorno

DB_URL

DB_USER

DB_PASSWORD

SPRING_PROFILES_ACTIVE

---

## Ejecutar con Gradle

```bash
./gradlew bootRun
```


## Ejecutar JAR

```bash
./gradlew clean build -x test
java -jar build/libs/<tu-jar>-SNAPSHOT.jar
```


## Ejecutar Docker

```bash
docker build -t abrazo-seguro .
docker run -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=default \
  -e DB_URL="$DB_URL" \
  -e DB_USER="$DB_USER" \
  -e DB_PASSWORD="$DB_PASSWORD" \
  abrazo-seguro
```
---

## Endpoints principales

GET /registro/servicios → lista de servicios

POST /registro/contacto → registrar contacto

GET /registro/paciente/{pacienteId} → paciente por ID

GET /registro/pacientes → lista de pacientes
