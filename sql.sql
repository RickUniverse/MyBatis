CREATE DATABASE mybatis;
USE mybatis;
CREATE TABLE tb_employee(
	id INT(11) PRIMARY KEY AUTO_INCREMENT,
	last_name VARCHAR(255),
	gender CHAR(1),
	email VARCHAR(255)
)
CREATE TABLE tb_department(
	id INT PRIMARY KEY AUTO_INCREMENT,
	dept_name VARCHAR(255)
)
ALTER TABLE tb_employee ADD COLUMN dept_id INT;
ALTER TABLE tb_employee ADD COLUMN emp_state VARCHAR(11);
ALTER TABLE tb_employee ADD COLUMN `version` INT(11);
ALTER TABLE tb_employee ADD COLUMN `logic_flag` INT(11);
ALTER TABLE tb_employee ADD COLUMN `address` VARCHAR(111);
ALTER TABLE tb_employee ADD COLUMN `createtime` CHAR(19);


CREATE TABLE tb_role(
	id INT PRIMARY KEY AUTO_INCREMENT,
	role_name VARCHAR(50)
)
INSERT INTO tb_role(role_name) VALUES('PM-项目经理'), 
('SE-软件工程师'),
('PG-程序员'),
('TL-组长'),
('GL-组长'),
('QC-品质控制'),
('SA-软件架构师'),
('CMO/CMS-配置项目'),
('SYSTEM-项目管理');
CREATE TABLE tb_employee_role(
	id INT PRIMARY KEY AUTO_INCREMENT,
	eid INT,
	rid INT
)
ALTER TABLE tb_employee_role ADD CONSTRAINT fk_emp_role_empid
FOREIGN KEY (eid) REFERENCES tb_employee(id);

ALTER TABLE tb_employee_role ADD CONSTRAINT fk_emp_role_roleid
FOREIGN KEY (rid) REFERENCES tb_role(id);


#创建树结构的表, 使用一张表自己关联自己
CREATE TABLE t_permission(
	id INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(33),
	pid INT,
	url VARCHAR(255)
)
#跟角色表的关联关系所用的表
CREATE TABLE t_role_permission(
	id INT PRIMARY KEY AUTO_INCREMENT,
	role_id INT,
	permission_id INT
)
# 外键
ALTER TABLE t_role_permission ADD CONSTRAINT fk_permission_permission_id
FOREIGN KEY (permission_id) REFERENCES t_permission(id);

ALTER TABLE t_role_permission ADD CONSTRAINT fk_role_role_id
FOREIGN KEY (role_id) REFERENCES tb_role(id);
# 没有补充角色, 创建这个表代替
CREATE TABLE t_employee_permission(
	id INT PRIMARY KEY AUTO_INCREMENT,
	employee_id INT,
	permission_id INT
)
# 外键
ALTER TABLE t_employee_permission ADD CONSTRAINT fk_employee_permission_id
FOREIGN KEY (permission_id) REFERENCES t_permission(id);

ALTER TABLE t_employee_permission ADD CONSTRAINT fk_employee_employee_id
FOREIGN KEY (employee_id) REFERENCES tb_employee(id);

SELECT
            *
        FROM t_permission
            WHERE id IN (
                SELECT
                    permission_id
                FROM t_role_permission
                    WHERE role_id IN (
                        SELECT
                            rid
                        FROM tb_employee_role
                            WHERE eid = 10082
                    )
            )

# 插入数据形成层级关系
INSERT INTO t_permission(`name`,pid,url) VALUES('系统菜单', 0, NULL),
('控制面板', 1, NULL),
('权限管理', 1, NULL),
('系统菜单', 3, NULL),
('用户维护', 3, NULL),
('角色维护', 3, NULL),
('许可维护', 3, NULL);
# 添加图标列 t_permission
ALTER TABLE t_permission ADD COLUMN `icon` VARCHAR(255);



SELECT * FROM tb_employee WHERE id = 10031

ALTER TABLE tb_employee ADD CONSTRAINT fk_emp_dept
FOREIGN KEY (dept_id) REFERENCES tb_department(id);

SELECT e.`id` eid, d.`id` did, e.*, d.* FROM tb_employee e, tb_department d WHERE e.`dept_id` = d.`id` AND e.`id` = 1

SELECT * FROM tb_employee WHERE id = 1

SELECT * FROM tb_department WHERE id = 1


SELECT d.id did, e.id eid, d.*, e.* FROM tb_department d 
LEFT JOIN tb_employee e
ON d.`id` = e.`dept_id`
WHERE d.`id` = 1

SELECT * FROM tb_employee WHERE dept_id = 1

SELECT * FROM tb_employee e WHERE e.`id` = 1 AND e.`email` = 'a' AND e.`gender` = 1 AND e.`last_name` LIKE 

SELECT 
	'true' AS QUERYID, e.id e_id, last_name, gender, email, dept_id, emp_state, VERSION, logic_flag, address, createtime, 
	e.id d_id, dept_name
FROM tb_employee e 
LEFT JOIN tb_department d 
ON e.`dept_id` = d.`id`
WHERE e.id = 3