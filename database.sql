create database sag_mini_project_db;
use sag_mini_project_db;
CREATE TABLE `sag_mini_project_db`.`categories` (
	`id` INT NOT NULL AUTO_INCREMENT , 
	`name` VARCHAR(255) NOT NULL , 
	`created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
	`updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
	`deleted_at` TIMESTAMP NULL, 
	PRIMARY KEY (`id`)
);
CREATE TABLE `sag_mini_project_db`.`products` (
	`id` INT NOT NULL AUTO_INCREMENT , 
	`name` VARCHAR(255) NOT NULL , 
	`quantity` INT DEFAULT 0 ,
	`id_category` INT NULL ,	
	`created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
	`updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
	`deleted_at` TIMESTAMP NULL, 
	PRIMARY KEY (`id`),
    CONSTRAINT fk_products_id_category FOREIGN KEY (id_category) REFERENCES categories(id)
);
CREATE TABLE `sag_mini_project_db`.`purchases` ( 
	`id` INT NOT NULL AUTO_INCREMENT , 
	`created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
	`updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
	`deleted_at` TIMESTAMP NULL,
	PRIMARY KEY (`id`)
);
CREATE TABLE `sag_mini_project_db`.`products_purchases` ( 
	`id_product` INT NOT NULL , 
	`id_purchase` INT NOT NULL , 
	`quantity` INT NOT NULL , 
    CONSTRAINT fk_products_purchases_id_product FOREIGN KEY (id_product) REFERENCES products(id),
    CONSTRAINT fk_products_purchases_id_purchase FOREIGN KEY (id_purchase) REFERENCES purchases(id)
);
CREATE TABLE `sag_mini_project_db`.`sales` ( 
	`id` INT NOT NULL AUTO_INCREMENT , 
	`created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
	`updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
	`deleted_at` TIMESTAMP NULL,
	PRIMARY KEY (`id`)
);
CREATE TABLE `sag_mini_project_db`.`products_sales` ( 
	`id_product` INT NOT NULL , 
	`id_sale` INT NOT NULL , 
	`quantity` INT NOT NULL , 
    CONSTRAINT fk_products_sales_id_product FOREIGN KEY (id_product) REFERENCES products(id),
    CONSTRAINT fk_products_sales_id_sale FOREIGN KEY (id_sale) REFERENCES sales(id)
);

INSERT INTO `categories` (`name`) values ("Food");
INSERT INTO `categories` (`name`) values ("Beverage");
INSERT INTO `categories` (`name`) values ("Healthcare");
INSERT INTO `categories` (`name`) values ("Electronics");
INSERT INTO `categories` (`name`) values ("Clothing");

INSERT INTO `products` (`name`,`quantity`,`id_category`) values ("Oats",10,2);
INSERT INTO `products` (`name`,`quantity`,`id_category`) values ("Cheese",10,2);
INSERT INTO `products` (`name`,`quantity`,`id_category`) values ("Chocolate",10,2);
INSERT INTO `products` (`name`,`quantity`,`id_category`) values ("Bread",10,2);
INSERT INTO `products` (`name`,`quantity`,`id_category`) values ("Cookies",10,2);

INSERT INTO `products` (`name`,`quantity`,`id_category`) values ("Coke",10,2);
INSERT INTO `products` (`name`,`quantity`,`id_category`) values ("Juice",10,2);
INSERT INTO `products` (`name`,`quantity`,`id_category`) values ("Syrup",10,2);
INSERT INTO `products` (`name`,`quantity`,`id_category`) values ("Milk",10,2);
INSERT INTO `products` (`name`,`quantity`,`id_category`) values ("Coffee",10,2);

INSERT INTO `products` (`name`,`quantity`,`id_category`) values ("Shampoo",10,3);
INSERT INTO `products` (`name`,`quantity`,`id_category`) values ("Soap",10,3);
INSERT INTO `products` (`name`,`quantity`,`id_category`) values ("Toothpaste",10,3);
INSERT INTO `products` (`name`,`quantity`,`id_category`) values ("Conditioner",10,3);
INSERT INTO `products` (`name`,`quantity`,`id_category`) values ("Handbody",10,3);

INSERT INTO `products` (`name`,`quantity`,`id_category`) values ("Handphone",10,4);
INSERT INTO `products` (`name`,`quantity`,`id_category`) values ("TV",10,4);
INSERT INTO `products` (`name`,`quantity`,`id_category`) values ("Laptop",10,4);
INSERT INTO `products` (`name`,`quantity`,`id_category`) values ("Headset",10,4);
INSERT INTO `products` (`name`,`quantity`,`id_category`) values ("Printer",10,4);

INSERT INTO `products` (`name`,`quantity`,`id_category`) values ("Shirt",10,5);
INSERT INTO `products` (`name`,`quantity`,`id_category`) values ("Trousers",10,5);
INSERT INTO `products` (`name`,`quantity`,`id_category`) values ("Tie",10,5);
INSERT INTO `products` (`name`,`quantity`,`id_category`) values ("Skirt",10,5);
INSERT INTO `products` (`name`,`quantity`,`id_category`) values ("Belt",10,5);

INSERT INTO `purchases` values ();
INSERT INTO `purchases` values ();
INSERT INTO `purchases` values ();
INSERT INTO `purchases` values ();
INSERT INTO `purchases` values ();
INSERT INTO `purchases` values ();
INSERT INTO `purchases` values ();
INSERT INTO `purchases` values ();
INSERT INTO `purchases` values ();
INSERT INTO `purchases` values ();

INSERT INTO `sales` values ();
INSERT INTO `sales` values ();
INSERT INTO `sales` values ();
INSERT INTO `sales` values ();
INSERT INTO `sales` values ();
INSERT INTO `sales` values ();
INSERT INTO `sales` values ();
INSERT INTO `sales` values ();
INSERT INTO `sales` values ();
INSERT INTO `sales` values ();

INSERT INTO `products_purchases` (`id_product`,`id_purchase`,`quantity`) values (1,1,2);
INSERT INTO `products_purchases` (`id_product`,`id_purchase`,`quantity`) values (6,2,2);
INSERT INTO `products_purchases` (`id_product`,`id_purchase`,`quantity`) values (18,3,6);
INSERT INTO `products_purchases` (`id_product`,`id_purchase`,`quantity`) values (22,4,3);
INSERT INTO `products_purchases` (`id_product`,`id_purchase`,`quantity`) values (23,5,5);
INSERT INTO `products_purchases` (`id_product`,`id_purchase`,`quantity`) values (5,6,5);
INSERT INTO `products_purchases` (`id_product`,`id_purchase`,`quantity`) values (7,7,5);
INSERT INTO `products_purchases` (`id_product`,`id_purchase`,`quantity`) values (9,8,5);
INSERT INTO `products_purchases` (`id_product`,`id_purchase`,`quantity`) values (15,9,5);
INSERT INTO `products_purchases` (`id_product`,`id_purchase`,`quantity`) values (17,10,5);
INSERT INTO `products_purchases` (`id_product`,`id_purchase`,`quantity`) values (8,2,2);
INSERT INTO `products_purchases` (`id_product`,`id_purchase`,`quantity`) values (2,5,1);
INSERT INTO `products_purchases` (`id_product`,`id_purchase`,`quantity`) values (3,6,3);
INSERT INTO `products_purchases` (`id_product`,`id_purchase`,`quantity`) values (4,3,4);
INSERT INTO `products_purchases` (`id_product`,`id_purchase`,`quantity`) values (10,8,2);

INSERT INTO `products_sales` (`id_product`,`id_sale`,`quantity`) values (2,1,2);
INSERT INTO `products_sales` (`id_product`,`id_sale`,`quantity`) values (5,2,7);
INSERT INTO `products_sales` (`id_product`,`id_sale`,`quantity`) values (9,3,4);
INSERT INTO `products_sales` (`id_product`,`id_sale`,`quantity`) values (10,4,6);
INSERT INTO `products_sales` (`id_product`,`id_sale`,`quantity`) values (12,5,3);
INSERT INTO `products_sales` (`id_product`,`id_sale`,`quantity`) values (11,6,8);
INSERT INTO `products_sales` (`id_product`,`id_sale`,`quantity`) values (1,7,2);
INSERT INTO `products_sales` (`id_product`,`id_sale`,`quantity`) values (15,8,5);
INSERT INTO `products_sales` (`id_product`,`id_sale`,`quantity`) values (19,9,9);
INSERT INTO `products_sales` (`id_product`,`id_sale`,`quantity`) values (20,10,4);
INSERT INTO `products_sales` (`id_product`,`id_sale`,`quantity`) values (3,3,2);
INSERT INTO `products_sales` (`id_product`,`id_sale`,`quantity`) values (4,7,3);
INSERT INTO `products_sales` (`id_product`,`id_sale`,`quantity`) values (21,2,5);
INSERT INTO `products_sales` (`id_product`,`id_sale`,`quantity`) values (24,8,4);
INSERT INTO `products_sales` (`id_product`,`id_sale`,`quantity`) values (25,5,1);



