DROP DATABASE IF EXISTS almaximoti;
CREATE DATABASE IF NOT EXISTS almaximoti;
USE almaximoti;

CREATE TABLE typeProduct (
    id INT PRIMARY KEY AUTO_INCREMENT,
    typeName VARCHAR(255),
    deleted tinyint(1) DEFAULT 0,
    createDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updateDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    description VARCHAR(255)
);

CREATE TABLE product (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(64),
    price DECIMAL(10, 2),
    productKey VARCHAR(255),
    typeProductId INT,
    deleted tinyint(1) DEFAULT 0,
    createDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updateDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (typeProductId) REFERENCES typeProduct(id)
);

CREATE TABLE supplier (
    id INT PRIMARY KEY AUTO_INCREMENT,
    supplierName VARCHAR(255),
    description VARCHAR(255),
    deleted tinyint(1) DEFAULT 0,
    createDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updateDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP

);

CREATE TABLE productSupplier (
    productSupplierId INT PRIMARY KEY AUTO_INCREMENT,
    productId INT,
    supplierId INT,
    supplierProductKey VARCHAR(255),
    supplierCost DECIMAL(10, 2),
    deleted tinyint(1) DEFAULT 0,
    createDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updateDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (productId) REFERENCES product(id),
    FOREIGN KEY (supplierId) REFERENCES supplier(id)
);



-- Create procedures 
-- Type Product
DELIMITER $$
CREATE PROCEDURE sp_get_all_type_products()
BEGIN
    SELECT * FROM typeProduct WHERE deleted = 0;
END$$

DELIMITER $$
CREATE PROCEDURE sp_get_one_type_product(IN id INT)
BEGIN
    SELECT * FROM typeProduct WHERE id = id AND deleted = 0;
END$$

DELIMITER $$
CREATE PROCEDURE sp_create_type_product(IN typeName VARCHAR(255), IN description VARCHAR(255))
BEGIN
    INSERT INTO typeProduct (typeName, description) VALUES (typeName, description);
END$$

-- Get all
DELIMITER $$
CREATE PROCEDURE sp_get_all_products()
BEGIN
    SELECT 
        p.id,
        p.name,
        p.price,
        p.productKey,
        tp.typeName
     FROM product p
     INNER JOIN typeProduct tp ON p.typeProductId = tp.id
      WHERE p.deleted = 0;
END$$

-- Get one
DELIMITER $$
CREATE PROCEDURE sp_get_one_product(IN id INT)
BEGIN
    SELECT 
        p.id,
        p.name,
        p.price,
        p.productKey,
        tp.id,
        tp.typeName
     FROM product p
        INNER JOIN typeProduct tp ON p.typeProductId = tp.id
     WHERE p.id = id AND p.deleted = 0;
END$$

-- get all suppliers of a product
DELIMITER $$
CREATE PROCEDURE sp_get_all_suppliers_of_product(IN id INT)
BEGIN
    SELECT 
        ps.id,
        ps.supplierId,
        ps.supplierProductKey,
        ps.supplierCost,
        s.supplierName
     FROM productSupplier ps
     INNER JOIN supplier s ON ps.supplierId = s.id
     WHERE ps.productId = id AND ps.deleted = 0;
END$$

-- Create
-- Recibe product name, description
-- Se inserta tambien en la tabla relacionada el 
DELIMITER $$
CREATE PROCEDURE sp_create_product(IN name VARCHAR(64), IN price DECIMAL(10, 2), IN productKey VARCHAR(64), IN typeProductId INT, IN supplierId INT, IN supplierProductKey VARCHAR(255), IN supplierCost DECIMAL(10, 2))
BEGIN
    --start transaction
    START TRANSACTION;
    INSERT INTO product (name, price, productKey, typeProductId) VALUES (name, price, productKey, typeProductId);
    SET @last_id = LAST_INSERT_ID();
    INSERT INTO productSupplier (productId, supplierId, supplierProductKey, supplierCost) VALUES (@last_id, supplierId, supplierProductKey, supplierCost);
    COMMIT;
END$$

-- Update
DELIMITER $$
CREATE PROCEDURE sp_update_product(IN id INT, IN name VARCHAR(64), IN price DECIMAL(10, 2), IN typeProductId INT)
BEGIN
    UPDATE product SET name = name, price = price, typeProductId = typeProductId WHERE id = id;
END$$

-- Delete
DELIMITER $$
create PROCEDURE sp_delete_product(IN id INT)
-- update deleted to 1
BEGIN
    UPDATE product SET deleted = 1 WHERE id = id;
END$$

-- Product Supplier
-- Get all
DELIMITER $$
CREATE PROCEDURE sp_get_all_product_suppliers()
BEGIN
    SELECT * FROM productSupplier WHERE deleted = 0;
END$$

-- Get one
DELIMITER $$
CREATE PROCEDURE sp_get_one_product_supplier(IN id INT)
BEGIN
    SELECT * FROM productSupplier WHERE id = id AND deleted = 0;
END$$

-- Create
DELIMITER $$
CREATE PROCEDURE sp_create_supplier(IN supplierName VARCHAR(255), IN description VARCHAR(255))
BEGIN
    INSERT INTO supplier (supplierName, description) VALUES (supplierName, description);
END$$

-- Update
DELIMITER $$
CREATE PROCEDURE sp_update_supplier(IN id INT, IN supplierName VARCHAR(255), IN description VARCHAR(255))
BEGIN
    UPDATE supplier SET supplierName = supplierName, description = description WHERE id = id;
END$$




-- INSERTS TYPE PRODUCT name,description
call sp_create_type_product('Bebidas alcoholicas', 'Descripcion de bebidas alcoholicas');
call sp_create_type_product('Electronicos', 'Descripcion de electronicos');
call sp_create_type_product('Comida', 'Descripcion de comida');
call sp_create_type_product('Ropa', 'Descripcion de ropa');
call sp_create_type_product('Herramientas', 'Descripcion de herramientas');

-- INSERTS SUPPLIER supplierName,description
call sp_create_supplier('Cerveceria 1', 'Descripcion de Cerveceria Modelo');
call sp_create_supplier('Cerveceria 2', 'Descripcion de Cerveceria Corona');
call sp_create_supplier('Cerveceria 3', 'Descripcion de Cerveceria Tecate');

call sp_create_supplier('Samsung', 'Descripcion de Samsung');
call sp_create_supplier('Apple', 'Descripcion de Apple');
call sp_create_supplier('Huawei', 'Descripcion de Huawei');

-- INSERTS PRODUCT name,price,productKey,typeProductId,supplierId,supplierProductKey,supplierCost
call sp_create_product('Cerveza Modelo', 20.00, '1-CM-001', 1, 1, 'CM001', 15.00);
call sp_create_product('Cerveza Corona', 20.00, '1-CC-001', 1, 1, 'CC001', 15.00);
call sp_create_product('Cerveza Victoria', 20.00, '1-CV-001', 1, 1, 'CV001', 15.00);
call sp_create_product('Cerveza Tecate', 20.00, '1-CT-001', 1, 1, 'CT001', 15.00);

call sp_create_product('Cerveza Modelo', 20.00, '1-CM-002', 1, 2, 'CM002', 15.50);
call sp_create_product('Cerveza Corona', 20.00, '1-CC-002', 1, 2, 'CC002', 15.50);
call sp_create_product('Cerveza Victoria', 20.00, '1-CV-002', 1, 2, 'CV002', 15.50);
call sp_create_product('Cerveza Tecate', 20.00, '1-CT-002', 1, 2, 'CT002', 15.50);

call sp_create_product('Samsung Galaxy S10', 20000.00, '2-SG-001', 2, 4, 'SG001', 15000.00);
call sp_create_product('Samsung Galaxy S20', 30000.00, '2-SG-002', 2, 4, 'SG002', 25000.00);
call sp_create_product('Samsung Galaxy S30', 40000.00, '2-SG-003', 2, 4, 'SG003', 35000.00);

call sp_create_product('Iphone 11', 20000.00, '2-IP-001', 2, 5, 'IP001', 15000.00);
call sp_create_product('Iphone 12', 30000.00, '2-IP-002', 2, 5, 'IP002', 25000.00);
call sp_create_product('Iphone 13', 40000.00, '2-IP-003', 2, 5, 'IP003', 35000.00);




