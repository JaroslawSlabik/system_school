
CREATE DATABASE db_system_uczelnia;
ALTER DATABASE db_system_uczelnia DEFAULT CHARACTER SET utf8 COLLATE utf8_polish_ci;

USE db_system_uczelnia;

CREATE TABLE studenci
(
	id_studenta BIGINT PRIMARY KEY AUTO_INCREMENT,
	imie VARCHAR(20) NOT NULL,
	nazwisko VARCHAR(35) NOT NULL,
	miasto VARCHAR(30),
	data_utworzenia TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	usuniety BOOLEAN DEFAULT FALSE
);
ALTER TABLE studenci DEFAULT CHARACTER SET utf8 COLLATE utf8_polish_ci;

CREATE TABLE specjalizacje
(
	id_specjalizacji INT PRIMARY KEY AUTO_INCREMENT,
	nazwa VARCHAR(25) NOT NULL,
	usunieta BOOLEAN DEFAULT FALSE
);
ALTER TABLE specjalizacje DEFAULT CHARACTER SET utf8 COLLATE utf8_polish_ci;

CREATE TABLE grupy
(
	id_grupy BIGINT PRIMARY KEY AUTO_INCREMENT,
	id_specjalizacji INT REFERENCES specjalizacje(id_specjalizacji),
	nazwa VARCHAR(20) NOT NULL,
	rok_akademicki TINYINT,
	usunieta BOOLEAN DEFAULT FALSE
);
ALTER TABLE grupy DEFAULT CHARACTER SET utf8 COLLATE utf8_polish_ci;

CREATE TABLE student_grupa
(
	id_student_grupa BIGINT PRIMARY KEY AUTO_INCREMENT,
	id_studenta BIGINT REFERENCES studenci(id_studenta),
	id_grupy BIGINT REFERENCES grupy(id_grupy),
	data_utworzenia TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
	usunieta BOOLEAN DEFAULT FALSE
);
ALTER TABLE student_grupa DEFAULT CHARACTER SET utf8 COLLATE utf8_polish_ci;
						
