CREATE TABLE IF NOT EXISTS driver (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    total_payouts INTEGER NOT NULL,
    date_of_birth DATE NOT NULL,
    experience SMALLSERIAL NOT NULL
);

CREATE TABLE IF NOT EXISTS vehicle (
    id BIGSERIAL PRIMARY KEY,
    manufacturer VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    payload INTEGER NOT NULL,
    is_broken BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS destination (
    id BIGSERIAL PRIMARY KEY,
    country VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    address VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS cargo_status (
    id BIGSERIAL PRIMARY KEY,
    status VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS cargo_order (
    id BIGSERIAL PRIMARY KEY,
    cargo_type VARCHAR(50) NOT NULL,
    cargo_amount INTEGER NOT NULL,
    payout INTEGER NOT NULL,
    days_till_complete SMALLSERIAL NOT NULL,
    min_experience_required SMALLSERIAL,
    driver_id BIGINT,
    vehicle_id BIGINT,
    cargo_status_id BIGSERIAL NOT NULL,
    destination_id BIGSERIAL NOT NULL,
    FOREIGN KEY (driver_id) REFERENCES driver (id) ON DELETE CASCADE,
    FOREIGN KEY (vehicle_id) REFERENCES vehicle (id) ON DELETE CASCADE,
    FOREIGN KEY (cargo_status_id) REFERENCES cargo_status (id) ON DELETE CASCADE,
    FOREIGN KEY (destination_id) REFERENCES destination (id) ON DELETE CASCADE
);


