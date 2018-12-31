-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema sportsbeatting
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sportsbeatting
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sportsbeatting` DEFAULT CHARACTER SET utf8 ;
USE `sportsbeatting` ;

-- -----------------------------------------------------
-- Table `sport_event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sport_event` (
  `type` VARCHAR(31) NOT NULL,
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `end_date` DATETIME NULL DEFAULT NULL,
  `start_date` DATETIME NULL DEFAULT NULL,
  `title` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bet` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `type` VARCHAR(255) NULL DEFAULT NULL,
  `sport_event_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKqfnes7j03c6mio3qpd5grqltv` (`sport_event_id` ASC),
  CONSTRAINT `FKqfnes7j03c6mio3qpd5grqltv`
    FOREIGN KEY (`sport_event_id`)
    REFERENCES `sport_event` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `outcome`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `outcome` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `value` VARCHAR(255) NULL DEFAULT NULL,
  `bet_id` BIGINT(20) NOT NULL,
  `win` SMALLINT(1) NULL,
  PRIMARY KEY (`id`),
  INDEX `FK5tkkatukb6c8emvesq8xvba51` (`bet_id` ASC),
  CONSTRAINT `FK5tkkatukb6c8emvesq8xvba51`
    FOREIGN KEY (`bet_id`)
    REFERENCES `bet` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `outcome_odd`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `outcome_odd` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `valid_from` DATE NULL DEFAULT NULL,
  `valid_to` DATE NULL DEFAULT NULL,
  `value` DOUBLE NULL DEFAULT NULL,
  `outcome_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK320q13v7e5b5e43hhy6kijsfj` (`outcome_id` ASC),
  CONSTRAINT `FK320q13v7e5b5e43hhy6kijsfj`
    FOREIGN KEY (`outcome_id`)
    REFERENCES `outcome` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `account_number` VARCHAR(255) NULL DEFAULT NULL,
  `balance` INT(11) NULL DEFAULT NULL,
  `currency` VARCHAR(255) NULL DEFAULT NULL,
  `date_of_birth` DATE NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `wager`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wager` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `amount` INT(11) NULL DEFAULT NULL,
  `currency` VARCHAR(255) NULL DEFAULT NULL,
  `processed` SMALLINT(1) NULL DEFAULT NULL,
  `timestamp` DATETIME NULL DEFAULT NULL,
  `win` SMALLINT(1) NULL DEFAULT NULL,
  `outcome_odd_id` BIGINT(20) NOT NULL,
  `player_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKjuacdqkfih8pwpqvm4pemkep7` (`outcome_odd_id` ASC),
  INDEX `FKmrysb77jy71u1enhrc032a8nf` (`player_id` ASC),
  CONSTRAINT `FKjuacdqkfih8pwpqvm4pemkep7`
    FOREIGN KEY (`outcome_odd_id`)
    REFERENCES `outcome_odd` (`id`),
  CONSTRAINT `FKmrysb77jy71u1enhrc032a8nf`
    FOREIGN KEY (`player_id`)
    REFERENCES `user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
