DROP TABLE IF EXISTS employee CASCADE;

CREATE TABLE IF NOT EXISTS employee
(

    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name   VARCHAR(255) NOT NULL,
    last_name    VARCHAR(255) NOT NULL,
    abbreviation VARCHAR(255) NOT NULL
);

create index idx__employee__employee_id on employee (id);