CREATE TABLE classes
(
    id         UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    org_id     UUID         NOT NULL,
    name       VARCHAR(255) NOT NULL,
    is_active  BOOLEAN          DEFAULT true,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);