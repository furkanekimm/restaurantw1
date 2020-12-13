INSERT INTO USERS(username,password,enabled) VALUES ('user','{noop}user',true);
INSERT INTO USERS(username,password,enabled) VALUES ('admin','{noop}admin',true);

INSERT INTO AUTHORITIES(username,authority) VALUES ('user','ROLE_USER');
INSERT INTO AUTHORITIES(username,authority) VALUES ('admin','ROLE_ADMIN');
INSERT INTO AUTHORITIES(username,authority) VALUES ('admin','ROLE_USER');

INSERT INTO CATEGORY(description,name,url_to_image) VALUES('Cold','Drink','null');
INSERT INTO CATEGORY(description,name,url_to_image) VALUES('dessert','Dessert','null');
INSERT INTO CATEGORY(description,name,url_to_image) VALUES('Hot','Soup','null');


INSERT INTO PRODUCT(category_id,description,price,product_Name,URL_TO_IMAGE) VALUES (1,'Cold',5,'Kola','https://productimages.hepsiburada.net/s/10/375/9219505782834.jpg');
INSERT INTO PRODUCT(category_id,description,price,product_Name,URL_TO_IMAGE) VALUES (1,'Cold',6,'Fanta','https://migros-dali-storage-prod.global.ssl.fastly.net/sanalmarket/product/08020000/08020000-b2f664.jpg');
INSERT INTO PRODUCT(category_id,description,price,product_Name,URL_TO_IMAGE) VALUES (2,'Dessert',5,'Tulumba','https://www.pakmayaprofesyonellerdunyasi.com/uploads/Tarif/tulumba-tatlisi.jpg');
INSERT INTO PRODUCT(category_id,description,price,product_Name,URL_TO_IMAGE) VALUES (2,'Dessert',5,'Baklava','https://st2.myideasoft.com/idea/ep/01/myassets/products/028/fistikli-baklava-klasik.jpg?revision=1560431896');
INSERT INTO PRODUCT(category_id,description,price,product_Name,URL_TO_IMAGE) VALUES (3,'Hot',5,'Mercimek','https://im.haberturk.com/2020/04/23/ver1587621896/2655814_810x458.jpg');
INSERT INTO PRODUCT(category_id,description,price,product_Name,URL_TO_IMAGE) VALUES (3,'Hot',5,'Ezogelin','https://im.haberturk.com/2020/01/17/ver1579268613/ezogelin-corbasi-nasil-yapilir_2559708_1200x627.jpg');

INSERT INTO PLACE_REST(NAME,TABLE_PIECE) VALUES('INDOOR',10);
INSERT INTO PLACE_REST(NAME,TABLE_PIECE) VALUES('OUTDOOR',12);
INSERT INTO PLACE_REST(NAME,TABLE_PIECE) VALUES('TERRACE',9);

/*INSERT INTO TABLE_REST(NAME,PLACE_ID) VALUES('TABLE 1',1);
INSERT INTO TABLE_REST(NAME,PLACE_ID) VALUES('TABLE 2',1);
INSERT INTO TABLE_REST(NAME,PLACE_ID) VALUES('TABLE 3',1);

INSERT INTO TABLE_REST(NAME,PLACE_ID) VALUES('TABLE 1',2);
INSERT INTO TABLE_REST(NAME,PLACE_ID) VALUES('TABLE 2',2);
INSERT INTO TABLE_REST(NAME,PLACE_ID) VALUES('TABLE 3',2);

INSERT INTO TABLE_REST(NAME,PLACE_ID) VALUES('TABLE 1',3);
INSERT INTO TABLE_REST(NAME,PLACE_ID) VALUES('TABLE 2',3);
INSERT INTO TABLE_REST(NAME,PLACE_ID) VALUES('TABLE 3',3);*/


