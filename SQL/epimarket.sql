DROP DATABASE IF EXISTS `epimarket`;
CREATE DATABASE `epimarket` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

DROP TABLE IF EXISTS `epimarket`.`customer`;
CREATE TABLE `epimarket`.`customer` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `communication_type` int(32) unsigned NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `adress` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(30) NOT NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `epimarket`.`reduction_customer`;
CREATE TABLE `epimarket`.`reduction_customer` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `customer_id` varchar(30) NOT NULL,
  `reduction_id` int(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `epimarket`.`reduction`;
CREATE TABLE `epimarket`.`reduction` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `description` varchar(512) NOT NULL,
  `type` int(32) unsigned NOT NULL,
  `value` int(32) unsigned NOT NULL,
  `target` int(32) unsigned NOT NULL,
  `deadline` DATE NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `epimarket`.`order`;
CREATE TABLE `epimarket`.`order` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` int(32) unsigned NOT NULL,
  `product_id` int(32) unsigned NOT NULL,
  `customer_id` int(32) unsigned NOT NULL,
  `quantity` int(32) unsigned NOT NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `epimarket`.`product`;
CREATE TABLE `epimarket`.`product` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `categoryId` int(32) unsigned NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(512) NOT NULL,
  `picture` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `epimarket`.`category`;
CREATE TABLE `epimarket`.`category` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(512) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `epimarket`.`stock`;
CREATE TABLE `epimarket`.`stock` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` int(32) unsigned NOT NULL,
  `quantity` int(32) unsigned NOT NULL,
  `buy_price` int(32) unsigned NOT NULL,
  `sell_price` int(32) unsigned NOT NULL,
  `next_buying` DATE NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
