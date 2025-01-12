CREATE TABLE IF NOT EXISTS user_ (
id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, 
first_name VARCHAR(255),
last_name VARCHAR(255),
birth_date TIMESTAMP,
email VARCHAR(255),
password VARCHAR(255)
);

use visitors;
CREATE TABLE IF NOT EXISTS visitors (
id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, 
first_name VARCHAR(30),
middle_name VARCHAR(30),
last_name VARCHAR(30),
gender VARCHAR(25),
password VARCHAR(255),
email VARCHAR(255),
phone_number VARCHAR(21)
);

create table IF NOT exists role_(
id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, 
role_name varchar(50),
description_ varchar(255)
);
create table IF NOT exists user_role(
id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, 
role_id bigint,
user_id bigint
);
