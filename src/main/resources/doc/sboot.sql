/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : sboot

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2018-06-09 20:19:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` varchar(36) NOT NULL,
  `dept_name` varchar(36) DEFAULT NULL,
  `parent_dept_id` varchar(36) DEFAULT NULL,
  `dept_order` int(5) DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('30a2b126-ae94-422e-a08b-ca31500d386d', '总公司', '-1', '1');
INSERT INTO `sys_dept` VALUES ('8f0bfd3f-e64c-4cbd-888b-461dc60664c1', '行政部', '30a2b126-ae94-422e-a08b-ca31500d386d', '3');
INSERT INTO `sys_dept` VALUES ('b3c355f1-db23-4382-afc6-dc4d5e2a8d22', '市场部', '30a2b126-ae94-422e-a08b-ca31500d386d', '4');
INSERT INTO `sys_dept` VALUES ('b5087d00-b4bf-4b5c-9592-602fe9586942', '研发部', '30a2b126-ae94-422e-a08b-ca31500d386d', '1');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` varchar(36) NOT NULL,
  `menu_name` varchar(36) DEFAULT '',
  `parent_menu_id` varchar(36) DEFAULT NULL,
  `menu_order` int(5) DEFAULT NULL,
  `menu_url` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('08f53e64-c0bc-414a-ad96-1fe8cfdfe83c', '角色管理', '8a2c0312-66a1-4827-b8d8-d351cfc62c34', '2', '/system/role.html');
INSERT INTO `sys_menu` VALUES ('3222ef47-065e-462e-a35e-cccef793b561', '测试菜单', '699198c1-f371-45e8-a44c-dbcf74e2d598', '1', '啊啊啊');
INSERT INTO `sys_menu` VALUES ('3f81094d-e020-4a6e-8b89-14191c8ddcee', '部门管理', '8a2c0312-66a1-4827-b8d8-d351cfc62c34', '5', '/system/dept.html');
INSERT INTO `sys_menu` VALUES ('699198c1-f371-45e8-a44c-dbcf74e2d598', '开发菜单', '-1', '2', 'b');
INSERT INTO `sys_menu` VALUES ('8a2c0312-66a1-4827-b8d8-d351cfc62c34', '系统管理', '-1', '1', 'a');
INSERT INTO `sys_menu` VALUES ('da0bb998-6da2-4b06-9959-09942e087ec4', '用户管理', '8a2c0312-66a1-4827-b8d8-d351cfc62c34', '1', '/system/user.html');
INSERT INTO `sys_menu` VALUES ('f447f038-c75b-4e82-a30c-9a40bba8dec9', '菜单管理', '8a2c0312-66a1-4827-b8d8-d351cfc62c34', '3', '/system/menu.html');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `perm_id` varchar(36) NOT NULL,
  `perm_name` varchar(36) DEFAULT NULL,
  `perm_flag` varchar(36) DEFAULT NULL,
  `menu_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`perm_id`),
  KEY `menu_id` (`menu_id`),
  CONSTRAINT `menu_id` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`menu_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('0a08f7ec-f40c-4f90-8fff-86170ce7d7dd', '修改', 'update', 'da0bb998-6da2-4b06-9959-09942e087ec4');
INSERT INTO `sys_permission` VALUES ('146c7629-e51e-429d-a574-72670293b283', '获取列表-combotree', 'deptController/getDeptList_combotree', '3f81094d-e020-4a6e-8b89-14191c8ddcee');
INSERT INTO `sys_permission` VALUES ('c4a109b5-1cce-4473-8dce-ca5ee4baccbc', '保存操作', 'deptController/saveDept', '3f81094d-e020-4a6e-8b89-14191c8ddcee');
INSERT INTO `sys_permission` VALUES ('cf03f076-cc24-488a-80e4-b03923e27e58', '新增', 'add', 'da0bb998-6da2-4b06-9959-09942e087ec4');
INSERT INTO `sys_permission` VALUES ('eb89e5f7-1953-42d3-8619-875324b91dde', '获取列表', 'deptController/getDeptList_gridtree', '3f81094d-e020-4a6e-8b89-14191c8ddcee');
INSERT INTO `sys_permission` VALUES ('ff62dfe5-8a94-4055-b990-b5ccf8cb0658', '删除操作', 'deptController/delDept', '3f81094d-e020-4a6e-8b89-14191c8ddcee');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` varchar(36) NOT NULL,
  `role_name` varchar(36) DEFAULT NULL,
  `role_note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('123456', '开发管理员', '开发管理员（误删除）');
INSERT INTO `sys_role` VALUES ('db801cce-b31b-443c-9f22-bdac04abf6a5', '管理员', '额问问');
INSERT INTO `sys_role` VALUES ('ebe51314-ca77-4155-a3e9-eb66f6ded5ff', '部门经理', '熟练度空间乱收费');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` varchar(36) DEFAULT NULL,
  `menu_id` varchar(36) DEFAULT NULL,
  KEY `FK_Reference_1` (`menu_id`),
  KEY `FK_Reference_2` (`role_id`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`menu_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('db801cce-b31b-443c-9f22-bdac04abf6a5', '8a2c0312-66a1-4827-b8d8-d351cfc62c34');
INSERT INTO `sys_role_menu` VALUES ('db801cce-b31b-443c-9f22-bdac04abf6a5', 'da0bb998-6da2-4b06-9959-09942e087ec4');
INSERT INTO `sys_role_menu` VALUES ('db801cce-b31b-443c-9f22-bdac04abf6a5', 'f447f038-c75b-4e82-a30c-9a40bba8dec9');
INSERT INTO `sys_role_menu` VALUES ('db801cce-b31b-443c-9f22-bdac04abf6a5', '3f81094d-e020-4a6e-8b89-14191c8ddcee');
INSERT INTO `sys_role_menu` VALUES ('db801cce-b31b-443c-9f22-bdac04abf6a5', '699198c1-f371-45e8-a44c-dbcf74e2d598');
INSERT INTO `sys_role_menu` VALUES ('db801cce-b31b-443c-9f22-bdac04abf6a5', '3222ef47-065e-462e-a35e-cccef793b561');
INSERT INTO `sys_role_menu` VALUES ('123456', '8a2c0312-66a1-4827-b8d8-d351cfc62c34');
INSERT INTO `sys_role_menu` VALUES ('123456', 'da0bb998-6da2-4b06-9959-09942e087ec4');
INSERT INTO `sys_role_menu` VALUES ('123456', '08f53e64-c0bc-414a-ad96-1fe8cfdfe83c');
INSERT INTO `sys_role_menu` VALUES ('123456', 'f447f038-c75b-4e82-a30c-9a40bba8dec9');
INSERT INTO `sys_role_menu` VALUES ('123456', '3f81094d-e020-4a6e-8b89-14191c8ddcee');
INSERT INTO `sys_role_menu` VALUES ('123456', '699198c1-f371-45e8-a44c-dbcf74e2d598');
INSERT INTO `sys_role_menu` VALUES ('123456', '3222ef47-065e-462e-a35e-cccef793b561');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `perm_id` varchar(36) DEFAULT NULL,
  `role_id` varchar(36) DEFAULT NULL,
  KEY `FK_Reference_3` (`perm_id`),
  KEY `FK_Reference_4` (`role_id`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`perm_id`) REFERENCES `sys_permission` (`perm_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('0a08f7ec-f40c-4f90-8fff-86170ce7d7dd', 'db801cce-b31b-443c-9f22-bdac04abf6a5');
INSERT INTO `sys_role_permission` VALUES ('0a08f7ec-f40c-4f90-8fff-86170ce7d7dd', '123456');
INSERT INTO `sys_role_permission` VALUES ('cf03f076-cc24-488a-80e4-b03923e27e58', '123456');
INSERT INTO `sys_role_permission` VALUES ('146c7629-e51e-429d-a574-72670293b283', '123456');
INSERT INTO `sys_role_permission` VALUES ('c4a109b5-1cce-4473-8dce-ca5ee4baccbc', '123456');
INSERT INTO `sys_role_permission` VALUES ('eb89e5f7-1953-42d3-8619-875324b91dde', '123456');
INSERT INTO `sys_role_permission` VALUES ('ff62dfe5-8a94-4055-b990-b5ccf8cb0658', '123456');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` varchar(36) NOT NULL,
  `user_name` varchar(36) DEFAULT NULL,
  `user_pwd` varchar(36) DEFAULT NULL,
  `user_realname` varchar(36) DEFAULT NULL,
  `user_sex` varchar(1) DEFAULT NULL,
  `user_phone` varchar(10) DEFAULT NULL,
  `user_addr` varchar(255) DEFAULT NULL,
  `dept_id` varchar(36) DEFAULT NULL,
  `lastest_time` datetime DEFAULT NULL,
  `lastest_ip` varchar(36) DEFAULT NULL,
  `role_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FK_Reference_5` (`dept_id`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`dept_id`) REFERENCES `sys_dept` (`dept_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('16fe910f-4d58-46ff-8924-e9352adeec14', 'admin', 'admin', '开发人员', '男', '', '', '30a2b126-ae94-422e-a08b-ca31500d386d', null, null, '123456');
INSERT INTO `sys_user` VALUES ('29bd87ae-25f0-4414-bf92-30340e5238eb', 'abc', '123456', '张三', '男', '156564555', '的水电费是否', 'b5087d00-b4bf-4b5c-9592-602fe9586942', null, null, 'db801cce-b31b-443c-9f22-bdac04abf6a5');
INSERT INTO `sys_user` VALUES ('aaad2847-030f-404c-b96b-5f8f16c2864e', 'test', '123456', '李四', '女', '1566547475', '水电费水电费', 'b3c355f1-db23-4382-afc6-dc4d5e2a8d22', null, null, 'ebe51314-ca77-4155-a3e9-eb66f6ded5ff');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` varchar(36) NOT NULL,
  `role_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FK_Reference_7` (`role_id`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
