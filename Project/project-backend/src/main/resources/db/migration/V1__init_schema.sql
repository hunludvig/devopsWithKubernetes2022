CREATE TABLE todo(
    id SERIAL PRIMARY KEY,
    content VARCHAR(140),
    created_at TIMESTAMP NOT NULL
);