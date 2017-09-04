DROP TABLE IF EXISTS vet_specialties;
DROP TABLE IF EXISTS vets;
DROP TABLE IF EXISTS specialties;
DROP TABLE IF EXISTS visits;
DROP TABLE IF EXISTS pets;
DROP TABLE IF EXISTS types;
DROP TABLE IF EXISTS owners;
DROP TABLE IF EXISTS users;


CREATE TABLE vets (
	id         INTEGER IDENTITY PRIMARY KEY,
	first_name VARCHAR(30),
	last_name  VARCHAR(30),
	lock_version INTEGER DEFAULT 0,
	created_at DATETIME DEFAULT NOW(),
	updated_at DATETIME,
	deleted_at DATETIME,
	creator_id INTEGER,
	updater_id INTEGER,
	deleter_id INTEGER
);
CREATE INDEX vets_last_name
	ON vets (last_name);

CREATE TABLE specialties (
	id   INTEGER IDENTITY PRIMARY KEY,
	name VARCHAR(80),
	lock_version INTEGER DEFAULT 0,
	created_at DATETIME DEFAULT NOW(),
	updated_at DATETIME,
	deleted_at DATETIME,
	creator_id INTEGER,
	updater_id INTEGER,
	deleter_id INTEGER
);
CREATE INDEX specialties_name
	ON specialties (name);

CREATE TABLE vet_specialties (
	vet_id       INTEGER NOT NULL,
	specialty_id INTEGER NOT NULL
);
ALTER TABLE vet_specialties
	ADD CONSTRAINT fk_vet_specialties_vets FOREIGN KEY (vet_id) REFERENCES vets (id);
ALTER TABLE vet_specialties
	ADD CONSTRAINT fk_vet_specialties_specialties FOREIGN KEY (specialty_id) REFERENCES specialties (id);

CREATE TABLE types (
	id   INTEGER IDENTITY PRIMARY KEY,
	name VARCHAR(80),
	lock_version INTEGER DEFAULT 0,
	created_at DATETIME DEFAULT NOW(),
	updated_at DATETIME,
	deleted_at DATETIME,
	creator_id INTEGER,
	updater_id INTEGER,
	deleter_id INTEGER
);
CREATE INDEX types_name
	ON types (name);

CREATE TABLE owners (
	id         INTEGER IDENTITY PRIMARY KEY,
	first_name VARCHAR(30),
	last_name  VARCHAR_IGNORECASE(30),
	address    VARCHAR(255),
	city       VARCHAR(80),
	telephone  VARCHAR(20),
	lock_version INTEGER DEFAULT 0,
	created_at DATETIME DEFAULT NOW(),
	updated_at DATETIME,
	deleted_at DATETIME,
	creator_id INTEGER,
	updater_id INTEGER,
	deleter_id INTEGER
);
CREATE INDEX owners_last_name
	ON owners (last_name);

CREATE TABLE pets (
	id         INTEGER IDENTITY PRIMARY KEY,
	name       VARCHAR(30),
	birth_date DATE,
	type_id    INTEGER NOT NULL,
	owner_id   INTEGER NOT NULL,
	lock_version INTEGER DEFAULT 0,
	created_at DATETIME DEFAULT NOW(),
	updated_at DATETIME,
	deleted_at DATETIME,
	creator_id INTEGER,
	updater_id INTEGER,
	deleter_id INTEGER,
);
ALTER TABLE pets
	ADD CONSTRAINT fk_pets_owners FOREIGN KEY (owner_id) REFERENCES owners (id);
ALTER TABLE pets
	ADD CONSTRAINT fk_pets_types FOREIGN KEY (type_id) REFERENCES types (id);
CREATE INDEX pets_name
	ON pets (name);

CREATE TABLE visits (
	id          INTEGER IDENTITY PRIMARY KEY,
	pet_id      INTEGER NOT NULL,
	visit_date  DATE,
	description VARCHAR(255),
	lock_version INTEGER DEFAULT 0,
	created_at DATETIME DEFAULT NOW(),
	updated_at DATETIME,
	deleted_at DATETIME,
	creator_id INTEGER,
	updater_id INTEGER,
	deleter_id INTEGER,
);
ALTER TABLE visits
	ADD CONSTRAINT fk_visits_pets FOREIGN KEY (pet_id) REFERENCES pets (id);
CREATE INDEX visits_pet_id
	ON visits (pet_id);

CREATE TABLE users (
	id         INTEGER IDENTITY PRIMARY KEY,
	user       VARCHAR(30) NOT NULL,
	password   VARCHAR(255),
	first_name VARCHAR(30),
	last_name  VARCHAR_IGNORECASE(30),
	lock_version INTEGER DEFAULT 0,
	created_at DATETIME DEFAULT NOW(),
	updated_at DATETIME,
	deleted_at DATETIME,
	creator_id INTEGER,
	updater_id INTEGER,
	deleter_id INTEGER,
);
