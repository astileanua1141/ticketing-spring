
    CREATE TABLE IF NOT EXISTS events (
        id VARCHAR(36) PRIMARY KEY,
        event_name VARCHAR(50) NOT NULL,
        price double NOT NULL
    );

    CREATE TABLE IF NOT EXISTS tickets (
        id VARCHAR(36) PRIMARY KEY,
        event_id varchar(36),
        isCheckedIn boolean DEFAULT FALSE,

        CONSTRAINT fk_event
            FOREIGN KEY (event_id)
            REFERENCES events(id)
    );

    CREATE TABLE IF NOT EXISTS orders (
        id VARCHAR(36) PRIMARY KEY,
        ticket_id VARCHAR(36),

        CONSTRAINT fk_ticket
            FOREIGN KEY (ticket_id)
            REFERENCES tickets(id)
    );





