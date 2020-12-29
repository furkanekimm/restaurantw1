ALTER TABLE cart
ADD deleted bit(1) DEFAULT b'0';

ALTER TABLE category
ADD deleted bit(1) DEFAULT b'0';

ALTER TABLE media
ADD deleted bit(1) DEFAULT b'0';

ALTER TABLE place_rest
ADD deleted bit(1) DEFAULT b'0';

ALTER TABLE product
ADD deleted bit(1) DEFAULT b'0';

ALTER TABLE roles
ADD deleted bit(1) DEFAULT b'0';

ALTER TABLE waiters
ADD deleted bit(1) DEFAULT b'0';

ALTER TABLE users
ADD deleted bit(1) DEFAULT b'0';

ALTER TABLE customer
ADD deleted bit(1) DEFAULT b'0';
