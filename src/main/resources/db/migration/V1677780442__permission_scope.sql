DROP TABLE IF EXISTS `scope`;
CREATE TABLE scope
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    name          VARCHAR(255)          NULL,
    `description` VARCHAR(255)          NULL,
    role_id       INT                   NOT NULL,
    CONSTRAINT pk_scope PRIMARY KEY (id)
);

ALTER TABLE scope
    ADD CONSTRAINT `scope_role_id_fk` FOREIGN KEY (role_id) REFERENCES `role` (id);

DROP TABLE IF EXISTS `permission`;
CREATE TABLE permission
(
    id       INT AUTO_INCREMENT NOT NULL,
    name     VARCHAR(255)       NOT NULL,
    scope_id BIGINT             NULL,
    CONSTRAINT pk_permission PRIMARY KEY (id)
);

ALTER TABLE permission
    ADD CONSTRAINT `permission_scope_id_fk` FOREIGN KEY (scope_id) REFERENCES scope (id);

INSERT INTO `scope`
    (id, name, role_id)
VALUES (1, 'TOKEN', 1),
       (2, 'GRADING', 1),
       (3, 'WEIGHING', 1),
       (4, 'UNLOADING', 1);

INSERT INTO permission (name, scope_id)
select 'ADD', s.id
from `scope` s;
INSERT INTO permission (name, scope_id)
select 'VIEW', s.id
from `scope` s;
INSERT INTO permission (name, scope_id)
select 'UPDATE', s.id
from `scope` s;
INSERT INTO permission (name, scope_id)
select 'DELETE', s.id
from `scope` s;