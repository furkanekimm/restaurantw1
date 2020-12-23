DROP TABLE IF EXISTS waiters;

DROP TABLE IF EXISTS category_products;

DROP TABLE IF EXISTS user_roles;

DROP TABLE IF EXISTS product;

DROP TABLE IF EXISTS category;

/*DROP TABLE IF EXISTS media;*/

DROP TABLE IF EXISTS cart;

DROP TABLE IF EXISTS place_rest;

DROP TABLE IF EXISTS roles;

DROP TABLE IF EXISTS users;

/*CREATE TABLE media (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(250) CHARACTER SET utf8 DEFAULT NULL,
  file_content mediumblob DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;*/


CREATE TABLE category (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  description varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  url_to_image varchar(250) CHARACTER SET utf8 DEFAULT NULL,
  media_id int(11) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fk_media_id (media_id),
  CONSTRAINT fk_media_id FOREIGN KEY (media_id) REFERENCES media (id)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE product (
  id int(11) NOT NULL AUTO_INCREMENT,
  product_name varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  description varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  price int(11) DEFAULT NULL,
  url_to_image varchar(250) CHARACTER SET utf8 DEFAULT NULL,
  media_id int(11) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fk_media_product (media_id),
  CONSTRAINT fk_media_product FOREIGN KEY (media_id) REFERENCES media (id)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE category_products (
  category_id int(11) NOT NULL,
  product_id int(11) NOT NULL,
  PRIMARY KEY (category_id,product_id),
  KEY fk_category_product (product_id),
  CONSTRAINT fk_category_product FOREIGN KEY (product_id) REFERENCES product (id),
  CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES category (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE cart (
  id int NOT NULL AUTO_INCREMENT,
  create_date timestamp NULL DEFAULT NULL,
  piece int DEFAULT NULL,
  total int DEFAULT NULL,
  product_id int DEFAULT NULL,
  table_id int DEFAULT NULL,
  category_id int DEFAULT NULL,
  waiter_id int DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE place_rest (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  table_piece int DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE roles (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE users (
  id int NOT NULL AUTO_INCREMENT,
  username varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  email varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  password varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  enabled bit(1) DEFAULT b'1',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE waiters (
  id int NOT NULL AUTO_INCREMENT,
  waiter_name varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  waiter_last_name varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  phone_number int DEFAULT NULL,
  email varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  media_id int NOT NULL,
  PRIMARY KEY (id,media_id),
  KEY fk_waiter_image (media_id),
  CONSTRAINT fk_waiter_image FOREIGN KEY (media_id) REFERENCES media (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE user_roles (
  USER_ID int NOT NULL,
  ROLE_ID int NOT NULL,
  KEY fk_role_user (USER_ID),
  KEY fk_user_role (ROLE_ID),
  CONSTRAINT fk_role_user FOREIGN KEY (USER_ID) REFERENCES users (id),
  CONSTRAINT fk_user_role FOREIGN KEY (ROLE_ID) REFERENCES roles (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;









