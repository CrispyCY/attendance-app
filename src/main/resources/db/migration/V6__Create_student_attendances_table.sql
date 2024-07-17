CREATE TABLE student_attendances
(
    id         SERIAL PRIMARY KEY,
    org_id     UUID NOT NULL,
    class_id   UUID NOT NULL,
    student_id UUID NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);