CREATE TABLE packages (
                          id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                          org_id UUID NOT NULL,
                          name VARCHAR(255) NOT NULL,
                          price DECIMAL(18,2) NOT NULL DEFAULT 0.00,
                          is_active BOOLEAN DEFAULT true,
                          slot INT NOT NULL DEFAULT 0,
                          expiry_days INT NOT NULL DEFAULT 0,
                          created_at TIMESTAMP,
                          updated_at TIMESTAMP
);