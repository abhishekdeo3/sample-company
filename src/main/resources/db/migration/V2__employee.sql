DROP TABLE IF EXISTS employee CASCADE;

CREATE SEQUENCE employee_id_seq START 1000;

CREATE TABLE IF NOT EXISTS employee
(

    id           BIGINT DEFAULT nextval('employee_id_seq') PRIMARY KEY,
    first_name   VARCHAR(255) NOT NULL,
    last_name    VARCHAR(255) NOT NULL,
    abbreviation VARCHAR(255) NOT NULL UNIQUE
);

create index idx__employee__employee_id on employee (id);