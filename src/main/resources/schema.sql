
    CREATE TABLE IF NOT EXISTS event (
        id VARCHAR(36) PRIMARY KEY,
        event_name VARCHAR(50) NOT NULL,
        price double NOT NULL
    );

    CREATE TABLE IF NOT EXISTS ticket (
        id VARCHAR(36) PRIMARY KEY,
        event_name VARCHAR(50) NOT NULL,
        price double NOT NULL
        );





