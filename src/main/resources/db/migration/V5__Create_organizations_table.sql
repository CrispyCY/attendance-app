CREATE TABLE organizations
(
    id         UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name       VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    address    VARCHAR(255),
    phone_no1  VARCHAR(255),
    phone_no2  VARCHAR(255),
    is_active  BOOLEAN          DEFAULT true,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);