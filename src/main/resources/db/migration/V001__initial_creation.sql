CREATE TABLE event (
	id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(60) NOT NULL,
    description VARCHAR(60) NOT NULL,
    active TINYINT(1) NOT NULL,
    local VARCHAR(60) NOT NULL,

    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE session (
	id BIGINT NOT NULL AUTO_INCREMENT,
	event_id BIGINT NOT NULL,
    date_hour DATETIME NOT NULL,
    active TINYINT(1) NOT NULL,
    total_tickets SMALLINT(6) NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_session_event FOREIGN KEY (event_id) REFERENCES event (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE category (
	id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(60) NOT NULL,

    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE ticket (
	id BIGINT NOT NULL AUTO_INCREMENT,
	session_id BIGINT NOT NULL,
	category_id BIGINT NOT NULL,
    price DECIMAL(6,2) NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_ticket_session FOREIGN KEY (session_id) REFERENCES session (id),
    CONSTRAINT fk_ticket_category FOREIGN KEY (category_id) REFERENCES category (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE client (
	id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(60) NOT NULL,
    document VARCHAR(20) NOT NULL,
    birthdate DATETIME NOT NULL,

    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE command (
	id BIGINT NOT NULL AUTO_INCREMENT,
    client_id BIGINT NOT NULL,
    ticket_id BIGINT NOT NULL,
    date DATETIME NOT NULL,
    status VARCHAR(10) NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_order_client FOREIGN KEY (client_id) REFERENCES client (id),
    CONSTRAINT fk_order_ticket FOREIGN KEY (ticket_id) REFERENCES ticket (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



