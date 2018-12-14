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
    CONSTRAINT fk_products_purchases_id_product FOREIGN KEY (id_product) REFERENCES products(id),
    CONSTRAINT fk_products_purchases_id_sale FOREIGN KEY (id_sale) REFERENCES sales(id)
);