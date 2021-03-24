CREATE TABLE `lab2java`.`computers` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `id_type` INT NULL,
  PRIMARY KEY (`id`));
CREATE TABLE `lab2java`.`types` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));