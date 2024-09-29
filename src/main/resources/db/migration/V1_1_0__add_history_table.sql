CREATE TABLE IF NOT EXISTS history (
       id BIGSERIAL PRIMARY KEY,
       driver_name VARCHAR(100) NOT NULL,
       driver_surname VARCHAR(100) NOT NULL,
       vehicle_manufacturer VARCHAR(50) NOT NULL,
       vehicle_model VARCHAR(50) NOT NULL,
       cargo_amount DOUBLE PRECISION NOT NULL,
       payout DOUBLE PRECISION NOT NULL,
       cargo_type VARCHAR(50) NOT NULL,
       destination_city VARCHAR(50) NOT NULL,
       destination_country VARCHAR(50) NOT NULL,
       modified_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);