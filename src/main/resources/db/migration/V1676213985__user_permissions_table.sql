
--------------- TABLES 

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`                int NOT NULL AUTO_INCREMENT,
    `user_name`         varchar(15) NOT NULL,
    `first_name`        varchar(75) NOT NULL,
    `last_name`         varchar(75) NOT NULL,
    `email`             varchar(25) NOT NULL,
    `password`          varchar(127) NOT NULL,
    `mobile`            varchar(15) NOT NULL,
    `is_active`         boolean DEFAULT TRUE,
    `description`       varchar(175) DEFAULT NULL,
    `role_id`           int NOT NULL,
    `created_by`        int DEFAULT NULL,
    `modified_by`       int DEFAULT NULL,
    `creation_time`     datetime DEFAULT CURRENT_TIMESTAMP,
    `modification_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) 
) ENGINE = InnoDB DEFAULT CHARSET = latin1;


DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    `id`                int NOT NULL AUTO_INCREMENT,
    `role_name`         varchar(25) NOT NULL,
    `description`       varchar(150) DEFAULT NULL,
    `created_by`        int DEFAULT NULL,
    `modified_by`       int DEFAULT NULL,
    `creation_time`     datetime DEFAULT CURRENT_TIMESTAMP,
    `modification_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) 
) ENGINE = InnoDB DEFAULT CHARSET = latin1;


DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`
(
    `id`                int NOT NULL AUTO_INCREMENT,
    `permission_name`   varchar(45) NOT NULL,
    `description`       varchar(150) DEFAULT NULL,
    `created_by`        int NOT NULL,
    `modified_by`       int DEFAULT NULL,
    `creation_time`     datetime DEFAULT CURRENT_TIMESTAMP,
    `modification_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = latin1;


DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`
(
    `role_id`       int NOT NULL,
    `permission_id` int NOT NULL,
    UNIQUE KEY `role_permission_UN` (`role_id`,
        `permission_id`),
    KEY             `role_permission_FK` (`permission_id`)
) ENGINE = InnoDB DEFAULT CHARSET = latin1;


DROP TABLE IF EXISTS `workorder`;
CREATE TABLE `workorder`
(
    `id`                int NOT NULL AUTO_INCREMENT,
    `workorder_id`      varchar(75) NOT NULL,
    `status`            enum ('NOT_STARTED','IN_PROGRESS','COMPLETED','CLOSED') DEFAULT 'NOT_STARTED',
    `is_active`         boolean DEFAULT TRUE,
    `meta_data`         blob DEFAULT NULL,
    `assigned_to`       int NOT NULL,
    `created_by`        int NOT NULL,
    `modified_by`       int NOT NULL,
    `creation_time`     datetime DEFAULT CURRENT_TIMESTAMP,
    `modification_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) 
) ENGINE = InnoDB DEFAULT CHARSET = latin1;



DROP TABLE IF EXISTS `task`;
CREATE TABLE `task`
(
    `id`                int NOT NULL AUTO_INCREMENT,
    `task_id`           varchar(100) NOT NULL,
    `workorder_id_fk`   int NOT NULL,
    `title`             varchar(100),
    `remark`            varchar(175) DEFAULT NULL,
    `status`            enum ('NOT_STARTED','IN_PROGRESS','COMPLETED','RE_ASSIGNED', 'CLOSED') DEFAULT 'NOT_STARTED',
    `assigned_to`       int NOT NULL,
    `is_active`         boolean DEFAULT TRUE,
    `created_by`        int NOT NULL,
    `modified_by`       int NOT NULL,
    `creation_time`     datetime DEFAULT CURRENT_TIMESTAMP,
    `modification_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) 
) ENGINE = InnoDB DEFAULT CHARSET = latin1;



DROP TABLE IF EXISTS `item_type`;
CREATE TABLE `item_type`
(
    `id`            int NOT NULL AUTO_INCREMENT,
    `name`          varchar(25) NOT NULL,
    `description`   varchar(125) DEFAULT NULL,
    PRIMARY KEY (`id`) 
) ENGINE = InnoDB DEFAULT CHARSET = latin1;



DROP TABLE IF EXISTS `id_type`;
CREATE TABLE `id_type`
(
    `id`            int NOT NULL AUTO_INCREMENT,
    `id_type`       varchar(25) NOT NULL,
    `description`   varchar(125) DEFAULT NULL,
    PRIMARY KEY (`id`) 
) ENGINE = InnoDB DEFAULT CHARSET = latin1;



DROP TABLE IF EXISTS `vendor_type`;
CREATE TABLE `vendor_type`
(
    `id`            int NOT NULL AUTO_INCREMENT,
    `vendor_type`   varchar(25) NOT NULL,
    `description`   varchar(125) DEFAULT NULL,
    PRIMARY KEY (`id`) 
) ENGINE = InnoDB DEFAULT CHARSET = latin1;



DROP TABLE IF EXISTS `vendor`;
CREATE TABLE `vendor`
(
    `id`                int NOT NULL AUTO_INCREMENT,
    `first_name`        varchar(75) DEFAULT NULL,
    `last_name`         varchar(75) DEFAULT NULL,
    `address`           varchar(175) DEFAULT NULL,
    `city`              varchar(25) DEFAULT NULL,
    `state`             varchar(25) DEFAULT NULL,
    `email`             varchar(25) DEFAULT NULL,
    `mobile`            varchar(15) DEFAULT NULL,
    `vendor_img`        varchar(120) DEFAULT NULL,
    `vendor_type_fk`    int NOT NULL,
    `id_type_fk`        int NOT NULL,
    `id_number`         varchar(75) DEFAULT NULL,
    `id_img`            varchar(120) DEFAULT NULL,
    `created_by`        int NOT NULL,
    `modified_by`       int NOT NULL,
    `creation_time`     datetime DEFAULT CURRENT_TIMESTAMP,
    `modification_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) 
) ENGINE = InnoDB DEFAULT CHARSET = latin1;



DROP TABLE IF EXISTS `purchase`;
CREATE TABLE `purchase`
(
    `id`                int NOT NULL AUTO_INCREMENT,
    `item_type_fk`      int NOT NULL,
    `workorder_id_fk`   int NOT NULL,
    `vendor_id_fk`      int NOT NULL,
    `price`             decimal NOT NULL,
    `weight`            decimal NOT NULL,
    `weight_unit`       enum ('KG','QUINTAL','TON') DEFAULT 'KG',
    `created_by`        int NOT NULL,
    `modified_by`       int NOT NULL,
    `creation_time`     datetime DEFAULT CURRENT_TIMESTAMP,
    `modification_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) 
) ENGINE = InnoDB DEFAULT CHARSET = latin1;


--------------- CONSTRAINTS 

ALTER TABLE `user`
    ADD CONSTRAINT `user_role_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

ALTER TABLE `user`
    ADD CONSTRAINT `user_creation_user_fk` FOREIGN KEY (`created_by`) REFERENCES `user` (`id`);

ALTER TABLE `user`
    ADD CONSTRAINT `user_modification_user_fk` FOREIGN KEY (`modified_by`) REFERENCES `user` (`id`);

ALTER TABLE `role`
    ADD CONSTRAINT `role_creation_user_fk` FOREIGN KEY (`created_by`) REFERENCES `user` (`id`);

ALTER TABLE `role`
    ADD CONSTRAINT `role_modification_user_fk` FOREIGN KEY (`modified_by`) REFERENCES `user` (`id`);

ALTER TABLE `permission`
    ADD CONSTRAINT `permission_creation_user_fk` FOREIGN KEY (`created_by`) REFERENCES `user` (`id`);

ALTER TABLE `permission`
    ADD CONSTRAINT `permission_modification_user_fk` FOREIGN KEY (`modified_by`) REFERENCES `user` (`id`);

ALTER TABLE `role_permission`
    ADD CONSTRAINT `permission_fk` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`);

ALTER TABLE `role_permission`
    ADD CONSTRAINT `role_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

ALTER TABLE `workorder`
    ADD CONSTRAINT `workorder_assigned_user_fk` FOREIGN KEY (`assigned_to`) REFERENCES `user` (`id`);

ALTER TABLE `workorder`
    ADD CONSTRAINT `workorder_creation_user_fk` FOREIGN KEY (`created_by`) REFERENCES `user` (`id`);

ALTER TABLE `workorder`
    ADD CONSTRAINT `workorder_modification_user_fk` FOREIGN KEY (`modified_by`) REFERENCES `user` (`id`);

ALTER TABLE `task`
    ADD CONSTRAINT `task_workorder_fk` FOREIGN KEY (`workorder_id_fk`) REFERENCES `workorder` (`id`);

ALTER TABLE `task`
    ADD CONSTRAINT `task_assigned_user_fk` FOREIGN KEY (`assigned_to`) REFERENCES `user` (`id`);

ALTER TABLE `task`
    ADD CONSTRAINT `task_creation_user_fk` FOREIGN KEY (`created_by`) REFERENCES `user` (`id`);

ALTER TABLE `task`
    ADD CONSTRAINT `task_modification_user_fk` FOREIGN KEY (`modified_by`) REFERENCES `user` (`id`);

ALTER TABLE `vendor`
    ADD CONSTRAINT `vendor_vendor_type_fk` FOREIGN KEY (`vendor_type_fk`) REFERENCES `vendor` (`id`);

ALTER TABLE `vendor`
    ADD CONSTRAINT `vendor_id_type_fk` FOREIGN KEY (`id_type_fk`) REFERENCES `id_type` (`id`);

ALTER TABLE `vendor`
    ADD CONSTRAINT `vendor_creation_user_fk` FOREIGN KEY (`created_by`) REFERENCES `user` (`id`);

ALTER TABLE `vendor`
    ADD CONSTRAINT `vendor_modification_user_fk` FOREIGN KEY (`modified_by`) REFERENCES `user` (`id`);

ALTER TABLE `purchase`
    ADD CONSTRAINT `purchase_item_type_fk` FOREIGN KEY (`item_type_fk`) REFERENCES `item_type` (`id`);

ALTER TABLE `purchase`
    ADD CONSTRAINT `purchase_workorder_type_fk` FOREIGN KEY (`workorder_id_fk`) REFERENCES `workorder` (`id`);

ALTER TABLE `purchase`
    ADD CONSTRAINT `purchase_vendor_fk` FOREIGN KEY (`vendor_id_fk`) REFERENCES `vendor` (`id`);

ALTER TABLE `purchase`
    ADD CONSTRAINT `purchase_creation_user_fk` FOREIGN KEY (`created_by`) REFERENCES `user` (`id`);

ALTER TABLE `purchase`
    ADD CONSTRAINT `purchase_modification_user_fk` FOREIGN KEY (`modified_by`) REFERENCES `user` (`id`);



-- DELETE CONSTRAINTS -- PLEASE KEEP COMMENTED
---- ALTER TABLE `user` DROP CONSTRAINT `user_role_fk`
---- ALTER TABLE `user` DROP CONSTRAINT `user_creation_user_fk`
---- ALTER TABLE `user` DROP CONSTRAINT `user_modification_user_fk`
---- ALTER TABLE `role` DROP CONSTRAINT `role_creation_user_fk`
---- ALTER TABLE `role` DROP CONSTRAINT `role_modification_user_fk`
---- ALTER TABLE `permission` DROP CONSTRAINT `permission_creation_user_fk`
---- ALTER TABLE `permission` DROP CONSTRAINT `permission_modification_user_fk`
---- ALTER TABLE `role_permission` DROP CONSTRAINT `permission_fk`
---- ALTER TABLE `role_permission` DROP CONSTRAINT `role_fk`



--------------- DUMMY DATA

INSERT INTO `role`(role_name, description)
VALUES ('ROLE_ADMIN', 'This is Administrator role. This is the highest level Role in the organization which has all the permissions');

INSERT INTO `user`(user_name, first_name, last_name, email, password, mobile, is_active, description, role_id)
VALUES ('alice', 'Alice', 'Abernathy', 'alice_kapas@gmail.com', 'ResidentEvil', 999-999-9999, 1, 'My name is alice. I used to work for Umbrella Corporation. The Umbrella Corporation has an highly unstable and homicidal AI called Red Queen. And Im going to kill her.', 1);

INSERT INTO `permission`(permission_name, description, created_by)
VALUES ('PRM_LOGIN', 'This Permission grants the ability to login.', 1);

INSERT INTO `permission`(permission_name, description, created_by)
VALUES ('PRM_CREATE_USER', 'This Permission grants the ability to create new users.', 1);

INSERT INTO `permission`(permission_name, description, created_by)
VALUES ('PRM_CREATE_ROLE', 'This Permission grants the ability to create new roles.', 1);

INSERT INTO `permission`(permission_name, description, created_by)
VALUES ('PRM_CREATE_PERMISSION', 'This Permission grants the ability to create new permissions.', 1);

INSERT INTO `permission`(permission_name, description, created_by)
VALUES ('PRM_UPDATE_USER_ROLE_PERMISSION', 'This Permission grants the ability to update any user, its roles and permissions.', 1);

INSERT INTO `permission`(permission_name, description, created_by)
VALUES ('PRM_INSERT_VENDOR_DETAILS', 'This Permission allows to insert the Vendor details in procurement process.', 1);

INSERT INTO `permission`(permission_name, description, created_by)
VALUES ('PRM_QUALITY_CHECK', 'This Permission allows to determine the item GRADE.', 1);

INSERT INTO `permission`(permission_name, description, created_by)
VALUES ('PRM_INSERT_PRICE', 'This Permission allows to determine and insert the price of the item.', 1);

INSERT INTO `role_permission`(role_id, permission_id)
VALUES (1, 1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8);

