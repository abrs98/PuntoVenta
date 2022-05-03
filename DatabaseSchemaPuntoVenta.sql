-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema negocio_ventas
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema negocio_ventas
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `negocio_ventas` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `negocio_ventas` ;

-- -----------------------------------------------------
-- Table `negocio_ventas`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `negocio_ventas`.`categoria` (
  `id_categoria` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  `descripcion` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id_categoria`),
  UNIQUE INDEX `id_categoria_UNIQUE` (`id_categoria` ASC) VISIBLE,
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `negocio_ventas`.`proveedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `negocio_ventas`.`proveedor` (
  `id_proveedor` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `direccion` VARCHAR(100) NULL DEFAULT NULL,
  `correo` VARCHAR(100) NULL DEFAULT NULL,
  `telefono` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`id_proveedor`),
  UNIQUE INDEX `id_proveedor_UNIQUE` (`id_proveedor` ASC) VISIBLE,
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC) VISIBLE,
  UNIQUE INDEX `telefono_UNIQUE` (`telefono` ASC) VISIBLE,
  UNIQUE INDEX `direccion_UNIQUE` (`direccion` ASC) VISIBLE,
  UNIQUE INDEX `correo_UNIQUE` (`correo` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `negocio_ventas`.`producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `negocio_ventas`.`producto` (
  `codigo` VARCHAR(15) NOT NULL,
  `nombre` VARCHAR(100) NOT NULL,
  `precio` FLOAT NOT NULL,
  `stock` FLOAT NOT NULL,
  `unidad_medida` VARCHAR(10) NOT NULL,
  `id_categoria` INT NOT NULL,
  `id_proveedor` INT NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE INDEX `Nombre` (`nombre` ASC) VISIBLE,
  UNIQUE INDEX `id_producto_UNIQUE` (`codigo` ASC) VISIBLE,
  INDEX `id_categoria_idx` (`id_categoria` ASC) VISIBLE,
  INDEX `id_proveedor_idx` (`id_proveedor` ASC) VISIBLE,
  CONSTRAINT `id_categoria`
    FOREIGN KEY (`id_categoria`)
    REFERENCES `negocio_ventas`.`categoria` (`id_categoria`),
  CONSTRAINT `id_proveedor`
    FOREIGN KEY (`id_proveedor`)
    REFERENCES `negocio_ventas`.`proveedor` (`id_proveedor`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `negocio_ventas`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `negocio_ventas`.`usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  `contraseña` VARCHAR(50) NOT NULL,
  `administrador` TINYINT(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_usuario`),
  UNIQUE INDEX `id_usuario_UNIQUE` (`id_usuario` ASC) VISIBLE,
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `negocio_ventas`.`venta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `negocio_ventas`.`venta` (
  `id_venta` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATE NOT NULL,
  `total` FLOAT NOT NULL,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`id_venta`),
  UNIQUE INDEX `id_venta_UNIQUE` (`id_venta` ASC) VISIBLE,
  INDEX `id_usuario_idx` (`id_usuario` ASC) VISIBLE,
  CONSTRAINT `id_usuario`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `negocio_ventas`.`usuario` (`id_usuario`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `negocio_ventas`.`producto_venta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `negocio_ventas`.`producto_venta` (
  `id_producto_venta` INT NOT NULL AUTO_INCREMENT,
  `id_venta` INT NOT NULL,
  `cantidad` FLOAT NOT NULL,
  `precio` FLOAT NOT NULL,
  `importe` FLOAT NOT NULL,
  `id_producto` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id_producto_venta`),
  UNIQUE INDEX `id_producto_venta_UNIQUE` (`id_producto_venta` ASC) VISIBLE,
  INDEX `id_venta_idx` (`id_venta` ASC) VISIBLE,
  INDEX `id_producto_idx` (`id_producto` ASC) VISIBLE,
  CONSTRAINT `id_producto`
    FOREIGN KEY (`id_producto`)
    REFERENCES `negocio_ventas`.`producto` (`codigo`),
  CONSTRAINT `id_venta`
    FOREIGN KEY (`id_venta`)
    REFERENCES `negocio_ventas`.`venta` (`id_venta`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

