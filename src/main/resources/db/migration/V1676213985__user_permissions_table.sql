DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission`
(
    `id`                int(11) NOT NULL AUTO_INCREMENT,
    `permission_name`   varchar(255) NOT NULL,
    `description`       varchar(255) DEFAULT NULL,
    `created_by`        int(11) NOT NULL,
    `modified_by`       int(11) DEFAULT NULL,
    `creation_time`     datetime     NOT NULL,
    `modification_time` datetime     DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY                 `permission_FK` (`created_by`),
    KEY                 `permission_FK_1` (`modified_by`)
) ENGINE = InnoDB DEFAULT CHARSET = latin1;

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role`
(
    `id`                int(11) NOT NULL AUTO_INCREMENT,
    `role_name`         varchar(255) NOT NULL,
    `description`       varchar(255) DEFAULT NULL,
    `created_by`        int(11) NOT NULL,
    `modified_by`       int(11) DEFAULT NULL,
    `creation_time`     datetime     NOT NULL,
    `modification_time` datetime     DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY                 `role_FK_1` (`created_by`),
    KEY                 `role_FK_2` (`modified_by`)
) ENGINE = InnoDB DEFAULT CHARSET = latin1;

DROP TABLE IF EXISTS `role_permission`;

CREATE TABLE `role_permission`
(
    `role_id`       int(11) NOT NULL,
    `permission_id` int(11) NOT NULL,
    UNIQUE KEY `role_permission_UN` (`role_id`,
        `permission_id`),
    KEY             `role_permission_FK` (`permission_id`)
) ENGINE = InnoDB DEFAULT CHARSET = latin1;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    `id`                int(11) NOT NULL AUTO_INCREMENT,
    `user_name`         varchar(255) NOT NULL,
    `first_name`        varchar(255) NOT NULL,
    `last_name`         varchar(255) NOT NULL,
    `email`             varchar(255) NOT NULL,
    `password`          varchar(455) NOT NULL,
    `mobile`            varchar(255) NOT NULL,
    `is_active`         varchar(255) NOT NULL,
    `description`       varchar(255) DEFAULT NULL,
    `role_id`           int(11) NOT NULL,
    `created_by`        int(11) NOT NULL,
    `modified_by`       int(11) DEFAULT NULL,
    `creation_time`     datetime     NOT NULL,
    `modification_time` datetime     DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY                 `role_fk` (`role_id`),
    KEY                 `user_FK` (`created_by`),
    KEY                 `user_FK_1` (`modified_by`)
) ENGINE = InnoDB DEFAULT CHARSET = latin1;

INSERT INTO `user`(user_name, first_name, last_name, email, password, mobile, is_active, description, role_id, created_by, creation_time)
VALUES ('admin', 'admin firstName', 'lastName', 'test@test.com', 'yq2Dq6hosT3V9tJFWMNiwA==', 9999999999, 1, 'admin', 1, 1, now());

INSERT INTO `role`(role_name, description, created_by, creation_time)
VALUES ('Admin', 'Admin', 1, now());

ALTER TABLE `permission`
    ADD CONSTRAINT `permission_FK` FOREIGN KEY (`created_by`) REFERENCES `user` (`id`);

ALTER TABLE `permission`
    ADD CONSTRAINT `permission_FK_1` FOREIGN KEY (`modified_by`) REFERENCES `user` (`id`);

ALTER TABLE `role`
    ADD CONSTRAINT `role_FK_1` FOREIGN KEY (`created_by`) REFERENCES `user` (`id`);

ALTER TABLE `role`
    ADD CONSTRAINT `role_FK_2` FOREIGN KEY (`modified_by`) REFERENCES `user` (`id`);

ALTER TABLE `role_permission`
    ADD CONSTRAINT `role_permission_FK` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`);

ALTER TABLE `role_permission`
    ADD CONSTRAINT `role_permission_FK_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

ALTER TABLE `user`
    ADD CONSTRAINT `role_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

ALTER TABLE `user`
    ADD CONSTRAINT `user_FK` FOREIGN KEY (`created_by`) REFERENCES `user` (`id`);

ALTER TABLE `user`
    ADD CONSTRAINT `user_FK_1` FOREIGN KEY (`modified_by`) REFERENCES `user` (`id`);