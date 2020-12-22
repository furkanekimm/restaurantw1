
DROP TABLE IF EXISTS media;
CREATE TABLE media (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(250) CHARACTER SET utf8 DEFAULT NULL,
  file_content mediumblob DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


DROP TABLE IF EXISTS category;
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

DROP TABLE IF EXISTS product;
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

DROP TABLE IF EXISTS category_products;
CREATE TABLE category_products (
  category_id int(11) NOT NULL,
  product_id int(11) NOT NULL,
  PRIMARY KEY (category_id,product_id),
  KEY fk_category_product (product_id),
  CONSTRAINT fk_category_product FOREIGN KEY (product_id) REFERENCES product (id),
  CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES category (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


