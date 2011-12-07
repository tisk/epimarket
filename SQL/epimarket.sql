DROP DATABASE IF EXISTS `epimarket`;
CREATE DATABASE `epimarket` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

DROP TABLE IF EXISTS `epimarket`.`Category`;
CREATE TABLE `epimarket`.`Category` (
  `CategoryId` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Category Identifier',
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`CategoryId`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `epimarket`.`Product`;
CREATE TABLE `epimarket`.`Product` (
  `ProductId` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Product Identifier',
  `CategoryId` int(10) unsigned NOT NULL COMMENT 'Category Identifier',
  `price` int(10) unsigned NOT NULL COMMENT 'price of the product',
  `description` varchar(30) NOT NULL COMMENT 'description of the product',
  `name` varchar(30) NOT NULL COMMENT 'name of the product',
  PRIMARY KEY (`ProductId`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

