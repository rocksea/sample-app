CREATE DATABASE sample;
CREATE USER sample IDENTIFIED BY 'sample';
GRANT ALL PRIVILEGES ON sample.* TO 'sample'@'%';
FLUSH PRIVILEGES;
