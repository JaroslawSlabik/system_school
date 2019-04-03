
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




INSERT INTO specjalizacje VALUES(DEFAULT, 'Inżynieria Internetu', DEFAULT),
								(DEFAULT, 'Inżynieria Oprogramowania', DEFAULT),
								(DEFAULT, 'Inżynieria Baz danych', DEFAULT),
								(DEFAULT, 'Inżynieria Grafiki komputerowej', TRUE),
								(DEFAULT, 'Brak', DEFAULT);
								
								
INSERT INTO grupy VALUES(DEFAULT, 5, 'Z11', 1, DEFAULT),
						(DEFAULT, 5, 'Z12', 1, DEFAULT),
						(DEFAULT, 5, 'Z13', 1, DEFAULT),
						(DEFAULT, 5, 'Z14', 1, DEFAULT),
                                  
						(DEFAULT, 5, 'Z21', 1, DEFAULT),
						(DEFAULT, 5, 'Z22', 1, DEFAULT),
						(DEFAULT, 5, 'Z23', 1, DEFAULT),
						(DEFAULT, 5, 'Z24', 1, DEFAULT),
                                  
						(DEFAULT, 5, 'Z31', 2, DEFAULT),
						(DEFAULT, 5, 'Z32', 2, DEFAULT),
						(DEFAULT, 5, 'Z33', 2, DEFAULT),
                                  
						(DEFAULT, 5, 'Z41', 2, DEFAULT),
						(DEFAULT, 5, 'Z42', 2, DEFAULT),
						(DEFAULT, 5, 'Z43', 2, DEFAULT),

						(DEFAULT, 1, 'Z51', 3, DEFAULT),
						(DEFAULT, 2, 'Z52', 3, DEFAULT),
						(DEFAULT, 1, 'Z61', 3, DEFAULT),
						(DEFAULT, 3, 'Z62', 3, DEFAULT);
						
						
INSERT INTO studenci VALUES(DEFAULT, 'Jan', 'Alowski', 'Warszawa', DEFAULT, DEFAULT),
						   (DEFAULT, 'Sławek', 'Man', 'Warszawa', DEFAULT, DEFAULT),
						   (DEFAULT, 'Łukasz', 'Sernik', 'Warszawa', DEFAULT, DEFAULT),
						   (DEFAULT, 'Kamil', 'Ciastko', 'Warszawa', DEFAULT, DEFAULT),
						   (DEFAULT, 'Dawid', 'Czekolada', 'Warszawa', DEFAULT, DEFAULT),
						   (DEFAULT, 'Anna', 'Jogurt', 'Warszawa', DEFAULT, DEFAULT),
						   (DEFAULT, 'Kinga', 'Dżem', 'Warszawa', DEFAULT, DEFAULT),
						   (DEFAULT, 'Dawid', 'Maiden', 'Warszawa', DEFAULT, DEFAULT),
						   (DEFAULT, 'Adrianna', 'Akimow', 'Warszawa', DEFAULT, DEFAULT),
						   (DEFAULT, 'Igor', 'Zakal', 'Warszawa', DEFAULT, DEFAULT),
						   (DEFAULT, 'Bartek', 'Pion', 'Warszawa', DEFAULT, DEFAULT),
						   (DEFAULT, 'Aleksandra', 'Bąba', 'Warszawa', DEFAULT, DEFAULT),
						   (DEFAULT, 'Lucyna', 'Dąbek', 'Warszawa', DEFAULT, DEFAULT),
						   (DEFAULT, 'Paweł', 'Chola', 'Warszawa', DEFAULT, DEFAULT),
						   (DEFAULT, 'Marta', 'Zgryz', 'Warszawa', DEFAULT, DEFAULT),
						   (DEFAULT, 'Marcin', 'Alaska', 'Warszawa', DEFAULT, DEFAULT),
						   (DEFAULT, 'Katarzyna', 'Linuch', 'Warszawa', DEFAULT, DEFAULT),
						   (DEFAULT, 'Krystian', 'Real', 'Warszawa', DEFAULT, DEFAULT),
						   (DEFAULT, 'Rafał', 'Lio', 'Warszawa', DEFAULT, DEFAULT),
						   (DEFAULT, 'Tomek', 'Suzuki', 'Warszawa', DEFAULT, DEFAULT),
						   (DEFAULT, 'Monika', 'Honda', 'Warszawa', DEFAULT, DEFAULT),
						   (DEFAULT, 'Patrycja', 'Shell', 'Warszawa', DEFAULT, DEFAULT),
						   (DEFAULT, 'Magda', 'Legia', 'Warszawa', DEFAULT, TRUE),
						   (DEFAULT, 'Piotrek', 'Orlen', 'Warszawa', DEFAULT, DEFAULT),
						   (DEFAULT, 'Adrian', 'Tesco', 'Warszawa', DEFAULT, DEFAULT),
						   (DEFAULT, 'Stanisław', 'Lidl', 'Warszawa', DEFAULT, DEFAULT),
						   (DEFAULT, 'Mateusz', 'Biedronka', 'Warszawa', DEFAULT, DEFAULT),
						   (DEFAULT, 'Bartek', 'Decatlon', 'Warszawa', DEFAULT, TRUE),
						   (DEFAULT, 'Albert', 'Giza', 'Warszawa', DEFAULT, DEFAULT),
						   (DEFAULT, 'Adam', 'Piątek', 'Warszawa', DEFAULT, DEFAULT),
						   (DEFAULT, 'Michał', 'Jasiński', 'Warszawa', DEFAULT, DEFAULT),
						   (DEFAULT, 'Evelina', 'Bacha', 'Warszawa', DEFAULT, DEFAULT),
						   (DEFAULT, 'Alicja', 'Kotowska', 'Warszawa', DEFAULT, DEFAULT),
						   (DEFAULT, 'Eliza', 'Czerwona', 'Warszawa', DEFAULT, DEFAULT),
						   (DEFAULT, 'Oskar', 'Biały', 'Warszawa', DEFAULT, DEFAULT),
						   (DEFAULT, 'Paula', 'Dybek', 'Warszawa', DEFAULT, DEFAULT),
						   (DEFAULT, 'Szymon', 'Grzegorzek', 'Warszawa', DEFAULT, DEFAULT);
						   
						   
INSERT INTO student_grupa VALUES(DEFAULT, 1, 1, DEFAULT, DEFAULT),
								(DEFAULT, 2, 1, DEFAULT, DEFAULT),
								(DEFAULT, 3, 2, DEFAULT, DEFAULT),
								(DEFAULT, 4, 2, DEFAULT, DEFAULT),
								(DEFAULT, 5, 3, DEFAULT, DEFAULT),
								(DEFAULT, 6, 3, DEFAULT, DEFAULT),
								(DEFAULT, 7, 4, DEFAULT, DEFAULT),
								(DEFAULT, 8, 4, DEFAULT, DEFAULT),
								(DEFAULT, 9, 5, DEFAULT, DEFAULT),
								(DEFAULT, 10, 5, DEFAULT, DEFAULT),
								(DEFAULT, 11, 6, DEFAULT, DEFAULT),
								(DEFAULT, 12, 6, DEFAULT, DEFAULT),
								(DEFAULT, 13, 7, DEFAULT, DEFAULT),
								(DEFAULT, 14, 7, DEFAULT, DEFAULT),
								(DEFAULT, 15, 8, DEFAULT, DEFAULT),
								(DEFAULT, 16, 8, DEFAULT, DEFAULT),
								(DEFAULT, 17, 9, DEFAULT, DEFAULT),
								(DEFAULT, 18, 9, DEFAULT, DEFAULT),
								(DEFAULT, 19, 10, DEFAULT, DEFAULT),
								(DEFAULT, 20, 10, DEFAULT, DEFAULT),
								(DEFAULT, 21, 11, DEFAULT, DEFAULT),
								(DEFAULT, 22, 11, DEFAULT, DEFAULT),
								(DEFAULT, 23, 12, DEFAULT, DEFAULT),
								(DEFAULT, 24, 12, DEFAULT, DEFAULT),
								(DEFAULT, 25, 13, DEFAULT, DEFAULT),
								(DEFAULT, 26, 13, DEFAULT, DEFAULT),
								(DEFAULT, 27, 14, DEFAULT, DEFAULT),
								(DEFAULT, 28, 14, DEFAULT, DEFAULT),
								(DEFAULT, 29, 15, DEFAULT, DEFAULT),
								(DEFAULT, 30, 15, DEFAULT, DEFAULT),
								(DEFAULT, 31, 16, DEFAULT, DEFAULT),
								(DEFAULT, 32, 16, DEFAULT, DEFAULT),
								(DEFAULT, 33, 17, DEFAULT, DEFAULT),
								(DEFAULT, 34, 17, DEFAULT, DEFAULT),
								(DEFAULT, 35, 18, DEFAULT, DEFAULT),
								(DEFAULT, 36, 18, DEFAULT, DEFAULT),
								(DEFAULT, 37, 11, DEFAULT, DEFAULT);
						
