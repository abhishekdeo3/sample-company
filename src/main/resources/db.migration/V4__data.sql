INSERT INTO sample_company.employee(id, abbreviation, first_name, last_name)
VALUES
(1000, 'mmu', 'Max', 'Mueller'),
(1001, 'sam', 'Samuel', 'Johns'),
(1002, 'jns', 'John', 'Sandler'),
(1003, 'kid', 'Kim', 'Dave'),
(1004, 'NLK', 'Nicol', 'Krister');

INSERT INTO sample_company.computer(id, computer_name, description, ip_address, mac_address, employee_id)
VALUES
(2000, 'DEV2000', 'Development Machine', '172.80.79.255', 'AA:12334:OOPCF:5656', 1000),
(2001, 'TEST2001', 'TEST Machine', '172.80.79.256', 'AA:12334:OOPCF:5657', 1000),
(2002, 'BUS2002', 'BUSINESS Machine', '172.80.79.257', 'AA:12334:OOPCF:5658', 1000),
(2003, 'DEV2003', 'Development Machine', '172.80.79.258', 'AA:12334:OOPCF:5659', 1002),
(2004, 'DEV2004', 'Development Machine', '172.80.79.259', 'AA:12334:OOPCF:5660', 1002),
(2005, 'DEV2004', 'Development Machine', '172.80.79.260', 'AA:12334:OOPCF:5661', 1003),
(2006, 'DEV2004', 'Development Machine', '172.80.79.261', 'AA:12334:OOPCF:5662', null);

