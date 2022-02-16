SET foreign_key_checks = 0;

DELETE FROM event;
DELETE FROM session;
DELETE FROM category;
DELETE FROM ticket;
DELETE FROM client;
DELETE FROM command;

SET foreign_key_checks = 1;

ALTER TABLE event auto_increment = 1;
ALTER TABLE session auto_increment = 1;
ALTER TABLE category auto_increment = 1;
ALTER TABLE ticket auto_increment = 1;
ALTER TABLE client auto_increment = 1;
ALTER TABLE command auto_increment = 1;

-- Events
INSERT INTO event (id, title, description, active, local)
VALUES (1, 'Roda de samba','A melhor roda de samba da região', true, 'Sesc Pinheiros');

INSERT INTO event (id, title, description, active, local)
VALUES (2, 'Futebol solidário','O Encontro de velhos amigos para bater aquela pelada', true, 'Sesc Pompeia');

INSERT INTO event (id, title, description, active, local)
VALUES (3, 'Volei de praia','Volei em frente ao mar em Bertioga', true, 'Sesc Bertioga');

INSERT INTO event (id, title, description, active, local)
VALUES (4, 'Teatro infantil','Traga as crianças para se divertirem com essa galera animada', false, 'Sesc Itaquera');


-- Sessions
INSERT INTO session (id, event_id, date_hour, active, total_tickets)
VALUES (1, 1, utc_timestamp, true, 8);

INSERT INTO session (id, event_id, date_hour, active, total_tickets)
VALUES (2, 1,'2022-02-25 19:00:00', true, 9);

INSERT INTO session (id, event_id, date_hour, active, total_tickets)
VALUES (3, 2,'2022-03-15 19:00:00', true, 23);

INSERT INTO session (id, event_id, date_hour, active, total_tickets)
VALUES (4, 2,'2022-04-15 13:00:00', true, 24);

INSERT INTO session (id, event_id, date_hour, active, total_tickets)
VALUES (5, 3,'2022-02-20 11:00:00', true, 18);

INSERT INTO session (id, event_id, date_hour, active, total_tickets)
VALUES (6, 3,'2022-02-20 11:00:00', true, 20);

INSERT INTO session (id, event_id, date_hour, active, total_tickets)
VALUES (7, 4,'2022-02-23 10:00:00', false, 14);


-- Categories
INSERT INTO category (id, name)
VALUES (1, 'Esporte');

INSERT INTO category (id, name)
VALUES (2, 'Musical');

INSERT INTO category (id, name)
VALUES (3, 'Artes cenicas');

-- Tickets
INSERT INTO ticket (id, session_id, category_id, price)
VALUES (1, 1, 2, 25.90);

INSERT INTO ticket (id, session_id, category_id, price)
VALUES (2, 1, 2, 25.90);

INSERT INTO ticket (id, session_id, category_id, price)
VALUES (3, 2, 1, 25.90);

INSERT INTO ticket (id, session_id, category_id, price)
VALUES (4, 3, 1, 15.50);

INSERT INTO ticket (id, session_id, category_id, price)
VALUES (5, 3, 1, 15.50);

INSERT INTO ticket (id, session_id, category_id, price)
VALUES (6, 4, 1, 15.50);

INSERT INTO ticket (id, session_id, category_id, price)
VALUES (7, 5, 1, 10);

INSERT INTO ticket (id, session_id, category_id, price)
VALUES (8, 5, 1, 10);

INSERT INTO ticket (id, session_id, category_id, price)
VALUES (9, 7, 3, 5.40);


-- Clientes
INSERT INTO client (id, name, document, birthdate)
VALUES (1,'João da Silva', '48.789.545-7', '1990-10-08');

INSERT INTO client (id, name, document, birthdate)
VALUES (2,'Maria de Souza', '49.774.111-3', '1989-11-24');

INSERT INTO client (id, name, document, birthdate)
VALUES (3,'Caroline Bezerra', '41.654.321-2', '1995-05-12');

INSERT INTO client (id, name, document, birthdate)
VALUES (4,'Daniel Oliveira', '39.665.777-1', '1994-08-11');

INSERT INTO client (id, name, document, birthdate)
VALUES (5,'Kaique Santana', '50.222.333-8', '1990-10-08');


-- Commands
INSERT INTO command (id, client_id, ticket_id, date, status)
VALUES (1, 1, 1, utc_timestamp, 'CREATED');

INSERT INTO command (id, client_id, ticket_id, date, status)
VALUES (2, 2, 2, utc_timestamp, 'CREATED');

INSERT INTO command (id, client_id, ticket_id, date, status)
VALUES (3, 2, 6, utc_timestamp, 'CREATED');

INSERT INTO command (id, client_id, ticket_id, date, status)
VALUES (4, 3, 4, utc_timestamp, 'CREATED');

INSERT INTO command (id, client_id, ticket_id, date, status)
VALUES (5, 4, 7, utc_timestamp, 'CREATED');

INSERT INTO command (id, client_id, ticket_id, date, status)
VALUES (6, 5, 7, utc_timestamp, 'CREATED');