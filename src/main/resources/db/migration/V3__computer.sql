DROP TABLE IF EXISTS computer CASCADE;

CREATE SEQUENCE computer_id_seq START 2000;

CREATE TABLE IF NOT EXISTS computer
(

    id            BIGINT DEFAULT nextval('computer_id_seq') PRIMARY KEY,
    mac_address   VARCHAR(255) NOT NULL UNIQUE,
    computer_name VARCHAR(255) NOT NULL UNIQUE,
    ip_address    VARCHAR(255) NOT NULL UNIQUE,
    description   VARCHAR(255),
    employee_id   BIGINT,

    CONSTRAINT computer__employee__fkey FOREIGN KEY (employee_id) REFERENCES employee (id)

);

create index idx__computer__computer_id on computer (id);
create index idx__computer__employee_id on computer (employee_id);