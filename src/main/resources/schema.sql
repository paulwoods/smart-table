CREATE TABLE contact
(
    id         UUID default random_uuid(),
    first_name VARCHAR(100),
    last_name  VARCHAR(100),
    CONSTRAINT pk_contact PRIMARY KEY (id)
);

CREATE INDEX idx_contact_first_name ON contact (first_name, last_name);

CREATE INDEX idx_contact_last_name ON contact (last_name);