/*INSERT INTO USERS(username,password,enabled) VALUES ('user','{noop}user',true);
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
INSERT INTO PLACE_REST(NAME,TABLE_PIECE) VALUES('TERRACE',9);*/

/*INSERT INTO TABLE_REST(NAME,PLACE_ID) VALUES('TABLE 1',1);
INSERT INTO TABLE_REST(NAME,PLACE_ID) VALUES('TABLE 2',1);
INSERT INTO TABLE_REST(NAME,PLACE_ID) VALUES('TABLE 3',1);

INSERT INTO TABLE_REST(NAME,PLACE_ID) VALUES('TABLE 1',2);
INSERT INTO TABLE_REST(NAME,PLACE_ID) VALUES('TABLE 2',2);
INSERT INTO TABLE_REST(NAME,PLACE_ID) VALUES('TABLE 3',2);

INSERT INTO TABLE_REST(NAME,PLACE_ID) VALUES('TABLE 1',3);
INSERT INTO TABLE_REST(NAME,PLACE_ID) VALUES('TABLE 2',3);
INSERT INTO TABLE_REST(NAME,PLACE_ID) VALUES('TABLE 3',3);*/

DROP TABLE IF EXISTS "category";
        CREATE TABLE "category" (
        "id" int(11) NOT NULL AUTO_INCREMENT,
        "name" varchar(50) CHARACTER SET utf8 DEFAULT NULL,
        "description" varchar(50) CHARACTER SET utf8 DEFAULT NULL,
        "url_to_image" varchar(250) CHARACTER SET utf8 DEFAULT NULL,
        "media_id" int(11) DEFAULT NULL,
        PRIMARY KEY ("id"),
        KEY "fk_media_id" ("media_id"),
        CONSTRAINT "fk_media_id" FOREIGN KEY ("media_id") REFERENCES "media" ("id")
        ) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

        INSERT INTO "category" VALUES ('1','minus','Aut et maxime saepe aliquam. Saepe et vero in aper','http://www.uptonconsidine.com/','1'),
        ('2','sed','Ut maxime qui facere ex ratione. Placeat accusamus','http://bosco.com/','2'),
        ('3','officiis','Atque quo eveniet magni. Non voluptates et nihil. ','http://www.harber.biz/','3'),
        ('4','quos','Ut deserunt excepturi impedit in saepe. Ad alias i','http://www.stracke.org/','4'),
        ('5','et','Ut consequatur similique corporis iste unde dolore','http://dickinson.com/','5'),
        ('6','sit','Quasi sint qui accusantium necessitatibus nihil vo','http://daniel.net/','6'),
        ('7','soluta','Nesciunt voluptatem repellendus consequatur qui et','http://www.strackeluettgen.biz/','7'),
        ('8','dolores','Quia quo autem cupiditate est id. Qui quisquam vol','http://www.dare.com/','8'),
        ('9','distinctio','Quis dignissimos eveniet nihil in iusto explicabo.','http://kozeyabbott.biz/','9'),
        ('10','architecto','Sapiente inventore temporibus ipsum consequatur. E','http://goldner.com/','10'),
        ('11','provident','Nihil quam ex et et fugit. Eaque est aut et sunt s','http://welch.com/','1'),
        ('12','quo','Aspernatur eum nesciunt ea fuga ab itaque. Consect','http://www.fritsch.com/','2'),
        ('13','possimus','Id qui qui ex occaecati doloremque dolorem qui. Al','http://cruickshankbrown.org/','3'),
        ('14','cumque','Minus dolorem error veritatis quis natus hic. Omni','http://funk.com/','4'),
        ('15','nobis','In atque repudiandae quia doloribus optio nihil pr','http://www.kihn.com/','5'),
        ('16','nesciunt','Adipisci necessitatibus molestiae dolorem illo con','http://www.mcclurebrekke.biz/','6'),
        ('17','aliquid','Illum nisi est ut omnis adipisci nam. Repellat id ','http://dickinson.com/','7'),
        ('18','eveniet','Voluptatem sit velit explicabo. Non quos aperiam i','http://www.predovicwisoky.biz/','8'),
        ('19','impedit','Voluptas officia aperiam pariatur eius temporibus ','http://zieme.biz/','9'),
        ('20','id','Ut voluptatum iure distinctio eveniet numquam volu','http://www.wiegand.com/','10');


        DROP TABLE IF EXISTS "category_products";
        CREATE TABLE "category_products" (
        "category_id" int(11) NOT NULL,
        "product_id" int(11) NOT NULL,
        PRIMARY KEY ("category_id","product_id"),
        KEY "fk_category_product" ("product_id"),
        CONSTRAINT "fk_category_product" FOREIGN KEY ("product_id") REFERENCES "product" ("id"),
        CONSTRAINT "fk_product_category" FOREIGN KEY ("category_id") REFERENCES "category" ("id")
        ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

        INSERT INTO "category_products" VALUES ('1','51'),
        ('1','67'),
        ('1','73'),
        ('1','91'),
        ('1','96'),
        ('2','52'),
        ('2','68'),
        ('2','75'),
        ('2','92'),
        ('2','97'),
        ('3','53'),
        ('3','69'),
        ('3','76'),
        ('3','93'),
        ('3','98'),
        ('4','54'),
        ('4','70'),
        ('4','77'),
        ('4','94'),
        ('4','99'),
        ('5','55'),
        ('5','72'),
        ('5','78'),
        ('5','95'),
        ('5','100'),
        ('6','51'),
        ('6','57'),
        ('6','73'),
        ('6','80'),
        ('6','96'),
        ('7','52'),
        ('7','58'),
        ('7','75'),
        ('7','81'),
        ('7','97'),
        ('8','53'),
        ('8','59'),
        ('8','76'),
        ('8','82'),
        ('8','98'),
        ('9','54'),
        ('9','60'),
        ('9','77'),
        ('9','83'),
        ('9','99'),
        ('10','55'),
        ('10','61'),
        ('10','78'),
        ('10','84'),
        ('10','100'),
        ('11','51'),
        ('11','57'),
        ('11','62'),
        ('11','80'),
        ('11','85'),
        ('12','52'),
        ('12','58'),
        ('12','63'),
        ('12','81'),
        ('12','87'),
        ('13','53'),
        ('13','59'),
        ('13','64'),
        ('13','82'),
        ('13','88'),
        ('14','54'),
        ('14','60'),
        ('14','65'),
        ('14','83'),
        ('14','89'),
        ('15','55'),
        ('15','61'),
        ('15','66'),
        ('15','84'),
        ('15','90'),
        ('16','57'),
        ('16','62'),
        ('16','67'),
        ('16','85'),
        ('16','91'),
        ('17','58'),
        ('17','63'),
        ('17','68'),
        ('17','87'),
        ('17','92'),
        ('18','59'),
        ('18','64'),
        ('18','69'),
        ('18','88'),
        ('18','93'),
        ('19','60'),
        ('19','65'),
        ('19','70'),
        ('19','89'),
        ('19','94'),
        ('20','61'),
        ('20','66'),
        ('20','72'),
        ('20','90'),
        ('20','95');


        DROP TABLE IF EXISTS "media";
        CREATE TABLE "media" (
        "id" int(11) NOT NULL AUTO_INCREMENT,
        "name" varchar(250) CHARACTER SET utf8 DEFAULT NULL,
        "file_content" mediumblob DEFAULT NULL,
        PRIMARY KEY ("id")
        ) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

        INSERT INTO "media" VALUES ('1','dolore','http://lorempixel.com/640/480/'),
        ('2','molestias','http://lorempixel.com/640/480/'),
        ('3','molestias','http://lorempixel.com/640/480/'),
        ('4','quo','http://lorempixel.com/640/480/'),
        ('5','debitis','http://lorempixel.com/640/480/'),
        ('6','unde','http://lorempixel.com/640/480/'),
        ('7','voluptas','http://lorempixel.com/640/480/'),
        ('8','id','http://lorempixel.com/640/480/'),
        ('9','voluptatem','http://lorempixel.com/640/480/'),
        ('10','iste','http://lorempixel.com/640/480/');


        DROP TABLE IF EXISTS "product";
        CREATE TABLE "product" (
        "id" int(11) NOT NULL AUTO_INCREMENT,
        "product_name" varchar(50) CHARACTER SET utf8 DEFAULT NULL,
        "description" varchar(50) CHARACTER SET utf8 DEFAULT NULL,
        "price" int(11) DEFAULT NULL,
        "url_to_image" varchar(250) CHARACTER SET utf8 DEFAULT NULL,
        "media_id" int(11) DEFAULT NULL,
        PRIMARY KEY ("id"),
        KEY "fk_media_product" ("media_id"),
        CONSTRAINT "fk_media_product" FOREIGN KEY ("media_id") REFERENCES "media" ("id")
        ) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

        INSERT INTO "product" VALUES ('51','et','Sed ea ratione laborum quas distinctio dolor. Faci','20074708','http://glover.biz/','8'),
        ('52','alias','Voluptatem quis eaque quisquam ut maiores similiqu','0','http://koelpinwaters.com/','7'),
        ('53','at','Dolor et natus consequatur voluptate minima illum.','0','http://smitham.com/','7'),
        ('54','doloribus','Qui occaecati totam aut ut nisi alias rerum. Quia ','84928','http://www.buckridgedaniel.biz/','4'),
        ('55','quis','Minima facilis optio et est qui autem. Quo eum nis','127','http://www.leuschke.com/','7'),
        ('57','totam','Voluptas perferendis quidem amet labore. Aperiam e','11091801','http://auer.com/','7'),
        ('58','numquam','Voluptatem ab ut officiis cumque autem beatae volu','3','http://www.larkin.com/','7'),
        ('59','veritatis','Neque non voluptatem cum accusamus est. Ut nobis r','0','http://effertzkris.info/','9'),
        ('60','dolorem','Rem earum et aut eius. Magnam expedita possimus eu','5900','http://www.bayer.com/','3'),
        ('61','odit','At aperiam asperiores quidem eveniet quis eaque es','6','http://considine.info/','7'),
        ('62','vero','Molestias dolore tenetur molestiae. Facilis tempor','175096334','http://yundt.com/','8'),
        ('63','autem','Commodi sed fugit repellendus. Veniam non rerum it','409','http://schmitt.com/','7'),
        ('64','et','Similique iste magni assumenda. Ea et est autem qu','449278','http://www.homenick.com/','2'),
        ('65','fugiat','Aliquam culpa ad et ipsa pariatur sed cumque. A qu','263','http://www.schulist.com/','8'),
        ('66','ratione','Voluptatem at id qui quod qui quos. Qui quisquam r','43022','http://jaskolskijaskolski.com/','7'),
        ('67','quisquam','Dolorum pariatur aut aperiam omnis doloremque inci','11234','http://larkin.org/','7'),
        ('68','fuga','Ipsum quos provident sint nobis. Ut qui sunt verit','40032718','http://www.oconnercrona.com/','5'),
        ('69','laborum','Consectetur beatae enim est soluta quae. Doloribus','4','http://www.maggioprosacco.com/','8'),
        ('70','rerum','Fugiat nulla illo consequatur qui quod. Perferendi','30','http://www.armstrongchamplin.com/','8'),
        ('72','quas','Inventore earum exercitationem voluptas. Facilis f','0','http://conroykuhlman.com/','4'),
        ('73','est','Tempore accusamus corporis vel inventore deserunt ','25794169','http://www.gaylordspinka.biz/','3'),
        ('75','aut','Doloribus autem natus minus. Ipsum dolores totam i','56134','http://nitzsche.com/','1'),
        ('76','voluptas','Molestiae laboriosam soluta cum temporibus. Porro ','22582','http://www.willms.net/','9'),
        ('77','aliquam','Et doloremque corporis praesentium doloribus volup','417352','http://feeney.biz/','8'),
        ('78','et','Ut aspernatur ipsum animi sunt sed. Ea voluptas et','10818','http://www.towne.com/','3'),
        ('80','cupiditate','Unde ad ratione rem aut quo. Laborum quidem dolor ','30096108','http://dickens.com/','3'),
        ('81','consequatur','Doloremque voluptas nulla dolores perspiciatis. Re','4','http://www.oreillyziemann.com/','7'),
        ('82','aut','Nemo sapiente ut quidem cum praesentium. Ratione e','9024','http://www.moore.org/','2'),
        ('83','sed','Eveniet beatae distinctio eum architecto facilis. ','27279','http://rosenbaumnicolas.com/','7'),
        ('84','hic','Dolores dicta fuga corporis ullam aut qui eos. Tem','3035371','http://www.jacobi.org/','9'),
        ('85','dicta','Impedit est quo optio qui soluta et consequuntur v','78389742','http://www.turcotte.com/','9'),
        ('87','reprehenderit','Est sed aut officia tenetur. Distinctio laborum pa','7758','http://www.ondricka.com/','2'),
        ('88','doloremque','Aut qui laborum rerum sint. Quis aperiam quae nobi','0','http://swaniawskimcdermott.net/','3'),
        ('89','aperiam','Explicabo similique a in et. Qui est illum laborio','4082','http://www.prosacco.org/','4'),
        ('90','deserunt','Autem nam distinctio et id ut molestias corrupti. ','126981','http://www.pouros.com/','7'),
        ('91','dicta','Velit accusamus voluptate quaerat. Aut eius occaec','84162254','http://hilpert.org/','6'),
        ('92','facilis','Sunt totam id rerum natus ratione quidem expedita.','4','http://www.connelly.com/','1'),
        ('93','quia','Hic enim ipsa qui qui. Quia sit sit esse ut conseq','205069','http://www.becker.info/','1'),
        ('94','sed','Et voluptatem deleniti consequatur iste ut maxime ','2453','http://gibson.com/','5'),
        ('95','nam','Minus dolorem veritatis et quod est temporibus. Qu','5891','http://strosin.com/','8'),
        ('96','nam','Doloremque saepe tempora blanditiis facilis tempor','548084','http://greenholt.info/','2'),
        ('97','quos','Eos praesentium quia commodi. Voluptatem ratione i','1','http://www.oconnell.biz/','6'),
        ('98','sit','Aut cupiditate similique qui et nobis et at sapien','1','http://www.bode.com/','5'),
        ('99','ducimus','Hic ab laboriosam dolor. Alias nesciunt aut nihil ','3142','http://www.wisoky.com/','6'),
        ('100','ad','Id culpa quo nesciunt odio praesentium voluptate n','881','http://www.kuphal.com/','4');


