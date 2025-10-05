CREATE TABLE SERVICIO (
                          id           BIGSERIAL PRIMARY KEY,
                          nombre       TEXT NOT NULL,
                          created_at   TIMESTAMPTZ NOT NULL DEFAULT now(),
                          updated_at   TIMESTAMPTZ NOT NULL DEFAULT now(),
                          CONSTRAINT uq_servicio_nombre UNIQUE (nombre)
);

CREATE TABLE PACIENTE (
                          id                BIGSERIAL PRIMARY KEY,
                          nombre            TEXT NOT NULL,
                          fecha_nacimiento  DATE NOT NULL,
                          diagnostico       TEXT,
                          servicio_id       BIGINT REFERENCES servicio(id),
                          created_at        TIMESTAMPTZ NOT NULL DEFAULT now(),
                          updated_at        TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE datos_contacto (
                                paciente_id   BIGINT PRIMARY KEY REFERENCES paciente(id) ON DELETE CASCADE,
                                nombre_tutor  TEXT NOT NULL,
                                telefono      TEXT,
                                direccion     TEXT,
                                ciudad        TEXT,
                                email         TEXT,
                                created_at    TIMESTAMPTZ NOT NULL DEFAULT now(),
                                updated_at    TIMESTAMPTZ NOT NULL DEFAULT now()
);