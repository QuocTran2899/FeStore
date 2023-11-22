CREATE DATABASE festore;
USE festore;

CREATE TABLE user (
    id 			int auto_increment,
    user_name 	varchar(255),
    first_name 	varchar(255),
    last_name 	varchar(255),
    full_name 	varchar(255),
    email 		varchar(255),
    password 	varchar(255,
    phone 		varchar(20),
    address 	text,
    id_role 	int,
    
    PRIMARY KEY (id)
);

CREATE TABLE role (
    id          int auto_increment,
    name        varchar(30),
    create_date datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    PRIMARY KEY (id)
);

CREATE TABLE product (
    id          int auto_increment,
    name        nvarchar(50),
    image       varchar(225),
    price       decimal(12, 3),
    description text,
    quantity    int,
    id_category int,
    id_size     int,
    id_color    int,
    create_date datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    PRIMARY KEY (id)
);

CREATE TABLE product_order (
    id_product  int,
    id_order    int,
    quantity    int,
    price       decimal(12, 3),
    create_date datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    PRIMARY KEY (id_order, id_product)
);

CREATE TABLE status (
    id          int auto_increment,
    name        varchar(50),
    create_date datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE orders (
    id          int auto_increment,
    id_user     int,
    id_status   int,
    create_date datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    PRIMARY KEY (id)
);

CREATE TABLE size (
    id          int auto_increment,
    name        nvarchar(50),
    create_date datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    PRIMARY KEY (id)
);

CREATE TABLE color (
    id          int auto_increment,
    name        nvarchar(50),
    create_date datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    PRIMARY KEY (id)
);

CREATE TABLE cart (
    id          int auto_increment,
    id_product  int,
    quantity     int,
    id_user     int,
    create_date datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    PRIMARY KEY (id)
);

CREATE TABLE category (
    id          int auto_increment,
    name        varchar(50),
    create_date datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    PRIMARY KEY (id)
);	

CREATE TABLE carousel (
    id          int auto_increment,
    title       varchar(255),
    image       varchar(225),
    content     text,
    id_category int,
    create_date datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    PRIMARY KEY (id)
);

CREATE TABLE file (
	id 			int auto_increment,
	name 		varchar(255),
	type 		varchar(255),
	data 		longblob,
	
	PRIMARY KEY (id)
);

ALTER TABLE user 			ADD CONSTRAINT fk_user_id_role              FOREIGN KEY (id_role)   	REFERENCES role (id);
ALTER TABLE cart 			ADD CONSTRAINT fk_cart_id_product           FOREIGN KEY (id_product)    REFERENCES product (id);
ALTER TABLE cart 			ADD CONSTRAINT fk_cart_id_user              FOREIGN KEY (id_user)       REFERENCES user (id);
ALTER TABLE product 		ADD CONSTRAINT fk_product_id_category       FOREIGN KEY (id_category)   REFERENCES category (id);
ALTER TABLE product 		ADD CONSTRAINT fk_product_id_size           FOREIGN KEY (id_size)       REFERENCES size (id);
ALTER TABLE product 		ADD CONSTRAINT fk_product_id_color          FOREIGN KEY (id_color)      REFERENCES color (id);
ALTER TABLE orders			ADD CONSTRAINT fk_orders_id_user            FOREIGN KEY (id_user)       REFERENCES user (id);
ALTER TABLE orders			ADD CONSTRAINT fk_orders_id_status          FOREIGN KEY (id_status)     REFERENCES status (id);
ALTER TABLE product_order	ADD CONSTRAINT fk_product_order_id_product  FOREIGN KEY (id_product)    REFERENCES product (id);
ALTER TABLE product_order	ADD CONSTRAINT fk_product_order_id_order    FOREIGN KEY (id_order)      REFERENCES orders (id);
ALTER TABLE carousel		ADD CONSTRAINT fk_carousel_id_category      FOREIGN KEY (id_category)   REFERENCES category (id);







