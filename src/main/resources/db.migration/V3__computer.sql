DROP TABLE IF EXISTS computer CASCADE;

CREATE TABLE IF NOT EXISTS computer
(

    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    mac_address   VARCHAR(255) NOT NULL,
    computer_name VARCHAR(255) NOT NULL,
    ip_address    VARCHAR(255) NOT NULL,
    description   VARCHAR(255),
    employee_id   BIGINT

        CONSTRAINT computer__employee__fkey FOREIGN KEY (employee_id) REFERENCES employee (id)

);

create index idx__computer__computer_id on computer (id);
create index idx__computer__employee_id on computer (employee_id);