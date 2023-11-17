INSERT INTO sample_company.employee(abbreviation, first_name, last_name)
VALUES
('mmu', 'Max', 'Mueller'),
('sam', 'Samuel', 'Johns'),
('jns', 'John', 'Sandler'),
('kid', 'Kim', 'Dave'),
('NLK', 'Nicol', 'Krister');

INSERT INTO sample_company.computer(computer_name, description, ip_address, mac_address, employee_id)
VALUES
('DEV2000', 'Development Machine', '172.80.79.255', 'AA:12334:OOPCF:5656', 1000),
('TEST2001', 'TEST Machine', '172.80.79.256', 'AA:12334:OOPCF:5657', 1000),
('BUS2002', 'BUSINESS Machine', '172.80.79.257', 'AA:12334:OOPCF:5658', 1000),
('DEV2003', 'Development Machine', '172.80.79.258', 'AA:12334:OOPCF:5659', 1002),
('DEV2004', 'Development Machine', '172.80.79.259', 'AA:12334:OOPCF:5660', 1002),
('DEV2005', 'Development Machine', '172.80.79.260', 'AA:12334:OOPCF:5661', 1003),
('DEV2006', 'Development Machine', '172.80.79.261', 'AA:12334:OOPCF:5662', null);