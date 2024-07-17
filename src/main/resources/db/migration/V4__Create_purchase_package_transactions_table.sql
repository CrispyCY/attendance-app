CREATE TABLE purchase_package_transactions (
                          id SERIAL PRIMARY KEY,
                          org_id UUID NOT NULL,
                          package_id UUID NOT NULL,
                          student_id UUID NOT NULL,
                          created_at TIMESTAMP,
                          updated_at TIMESTAMP
);