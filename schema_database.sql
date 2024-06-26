-- MySQL Script generated by MySQL Workbench
-- ven 5 apr 2024, 15:31:38
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema rubrica
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema rubrica
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `rubrica` ;
USE `rubrica` ;

-- -----------------------------------------------------
-- Table `rubrica`.`utenti`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rubrica`.`utenti` (
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(128) NOT NULL,
  `salt` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rubrica`.`contatti`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rubrica`.`contatti` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `cognome` VARCHAR(45) NOT NULL,
  `indirizzo` VARCHAR(60) NOT NULL,
  `telefono` VARCHAR(20) NOT NULL,
  `eta` INT NOT NULL,
  `utenti_username` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_contatti_utenti_idx` (`utenti_username` ASC) VISIBLE,
  CONSTRAINT `fk_contatti_utenti`
    FOREIGN KEY (`utenti_username`)
    REFERENCES `rubrica`.`utenti` (`username`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
