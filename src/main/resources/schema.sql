CREATE DATABASE visitors;

CREATE TABLE user_ (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  first_name varchar(255) DEFAULT NULL,
  last_name varchar(255) DEFAULT NULL,
  birth_date timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  email varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  role_id bigint(20) DEFAULT NULL,
  office_number varchar(45) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fk_role_idx (role_id)
);

use visitors;
CREATE TABLE visitors (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  first_name varchar(30) DEFAULT NULL,
  middle_name varchar(30) DEFAULT NULL,
  last_name varchar(30) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  phone_number varchar(21) DEFAULT NULL,
  birth_date date DEFAULT NULL,
  gender_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fk_gender_idx (gender_id),
  CONSTRAINT fk_gender FOREIGN KEY (gender_id) REFERENCES gender (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE role_ (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  role_name varchar(50) DEFAULT NULL,
  description_ varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE user_role (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  role_id bigint(20) DEFAULT NULL,
  user_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fk_visitor_idx (user_id),
  KEY fk_role_idx (role_id),
  CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES role_ (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES user_ (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE appointment_ (
  appointment_id bigint(20) NOT NULL AUTO_INCREMENT,
  visitor_id bigint(20) DEFAULT NULL,
  date timestamp NOT NULL DEFAULT current_timestamp(),
  user_id bigint(20) DEFAULT NULL,
  description varchar(255) DEFAULT NULL,
  PRIMARY KEY (appointment_id),
  KEY fk_visitors_idx (visitor_id),
  KEY fk_user_idx (user_id),
  CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES user_ (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_visitors FOREIGN KEY (visitor_id) REFERENCES visitors (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE gender (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  gender_name varchar(50) DEFAULT NULL,
  description_ varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE new_order (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  new_order bigint(20) NOT NULL DEFAULT 0,
  PRIMARY KEY (id)
);

CREATE TABLE registry (
  id int(11) NOT NULL AUTO_INCREMENT,
  visitor_id bigint(20) NOT NULL,
  status int(11) NOT NULL,
  location varchar(45) DEFAULT NULL,
  user_id bigint(20) NOT NULL,
  date timestamp NOT NULL DEFAULT current_timestamp(),
  appointment_id bigint(20) NOT NULL,
  order_ varchar(45) NOT NULL,
  PRIMARY KEY (id),
  KEY fk_visitor_idx (visitor_id),
  KEY fk_registry_user_idx (user_id),
  KEY fk_registry_appointment_idx (appointment_id),
  CONSTRAINT fk_registry_appointment FOREIGN KEY (appointment_id) REFERENCES appointment_ (appointment_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_registry_user FOREIGN KEY (user_id) REFERENCES user_ (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_visitor FOREIGN KEY (visitor_id) REFERENCES visitors (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);