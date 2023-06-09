DROP TABLE dbservantes IF EXISTS;
DROP TABLE restaurantes IF EXISTS;
DROP TABLE votacao IF EXISTS;

CREATE TABLE restaurantes (
  id         INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  nome       VARCHAR(30) NOT NULL,
  votos      INTEGER
);

CREATE TABLE dbservantes (
  id         INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  nome       VARCHAR(30) NOT NULL,
  sobrenome  VARCHAR(30) NOT NULL,
  equipe     VARCHAR(30) NOT NULL,
  restaurante_id   INTEGER
);

ALTER TABLE dbservantes ADD CONSTRAINT fk_db_resto FOREIGN KEY (restaurante_id) REFERENCES restaurantes (id);
CREATE INDEX dbservante_nome ON dbservantes (nome);

CREATE TABLE votacao (
  id          INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  restaurante_id       INTEGER,
  data        DATE,
  dia_semana  VARCHAR(30),
  hora        TIME,
  votos       INTEGER
);
ALTER TABLE votacao ADD CONSTRAINT fk_votacao_restaurantes FOREIGN KEY (restaurante_id) REFERENCES restaurantes (id);
CREATE INDEX votacao_db_id ON votacao (restaurante_id);
