CREATE TABLE students (
                          id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                          org_id UUID NOT NULL,
                          group_id UUID,
                          name VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL,
                          dob DATE,
                          address VARCHAR(255),
                          phone_no1 VARCHAR(255),
                          phone_no2 VARCHAR(255),
                          is_active BOOLEAN DEFAULT true,
                          slot_count INT NOT NULL DEFAULT 0,
                          slot_expiry_date DATE,
                          created_at TIMESTAMP,
                          updated_at TIMESTAMP
);