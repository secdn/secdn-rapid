-- ----------------------------
-- Table structure for sys_captcha
-- ----------------------------
DROP TABLE IF EXISTS `sys_captcha`;
CREATE TABLE `sys_captcha`
(
    `uuid`        char(36)   NOT NULL COMMENT 'uuid',
    `code`        varchar(6) NOT NULL COMMENT '验证码',
    `expire_time` bigint(15) NULL COMMENT '过期时间',
    PRIMARY KEY (`uuid`) USING BTREE
) COMMENT = '系统验证码';


-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `param_key`   varchar(50) NULL DEFAULT NULL COMMENT 'key',
    `param_value` varchar(2000) NULL DEFAULT NULL COMMENT 'value',
    `status`      tinyint(4) NULL DEFAULT 1 COMMENT '状态   0：隐藏   1：显示',
    `remark`      varchar(500) NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `param_key`(`param_key`) USING BTREE
) AUTO_INCREMENT = 2  COMMENT = '系统配置信息表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config`
VALUES (1, 'CLOUD_STORAGE_CONFIG_KEY',
        '{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}',
        0, '云存储配置信息');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `username`    varchar(50) NULL DEFAULT NULL COMMENT '用户名',
    `operation`   varchar(50) NULL DEFAULT NULL COMMENT '用户操作',
    `method`      varchar(200) NULL DEFAULT NULL COMMENT '请求方法',
    `params`      varchar(5000) NULL DEFAULT NULL COMMENT '请求参数',
    `time`        bigint(15) NOT NULL COMMENT '执行时长(毫秒)',
    `ip`          varchar(64) NULL DEFAULT NULL COMMENT 'IP地址',
    `create_time` bigint(15) NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) AUTO_INCREMENT = 24  COMMENT = '系统日志';



-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `menu_id`   bigint(20) NOT NULL AUTO_INCREMENT,
    `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
    `name`      varchar(50) NULL DEFAULT NULL COMMENT '菜单名称',
    `url`       varchar(200) NULL DEFAULT NULL COMMENT '菜单URL',
    `perms`     varchar(500) NULL DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
    `type`      int(11) NULL DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
    `icon`      varchar(50) NULL DEFAULT NULL COMMENT '菜单图标',
    `order_num` int(11) NULL DEFAULT NULL COMMENT '排序',
    PRIMARY KEY (`menu_id`) USING BTREE
) AUTO_INCREMENT = 54  COMMENT = '菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu`
VALUES (1, 0, '系统管理', NULL, NULL, 0, 'system', 0);
INSERT INTO `sys_menu`
VALUES (2, 1, '管理员列表', 'sys/user', NULL, 1, 'admin', 1);
INSERT INTO `sys_menu`
VALUES (3, 1, '角色管理', 'sys/role', NULL, 1, 'role', 2);
INSERT INTO `sys_menu`
VALUES (4, 1, '菜单管理', 'sys/menu', NULL, 1, 'menu', 3);
INSERT INTO `sys_menu`
VALUES (6, 1, '定时任务', 'job/schedule', NULL, 1, 'job', 5);
INSERT INTO `sys_menu`
VALUES (7, 6, '查看', NULL, 'sys:schedule:list,sys:schedule:info', 2, NULL, 0);
INSERT INTO `sys_menu`
VALUES (8, 6, '新增', NULL, 'sys:schedule:save', 2, NULL, 0);
INSERT INTO `sys_menu`
VALUES (9, 6, '修改', NULL, 'sys:schedule:update', 2, NULL, 0);
INSERT INTO `sys_menu`
VALUES (10, 6, '删除', NULL, 'sys:schedule:delete', 2, NULL, 0);
INSERT INTO `sys_menu`
VALUES (11, 6, '暂停', NULL, 'sys:schedule:pause', 2, NULL, 0);
INSERT INTO `sys_menu`
VALUES (12, 6, '恢复', NULL, 'sys:schedule:resume', 2, NULL, 0);
INSERT INTO `sys_menu`
VALUES (13, 6, '立即执行', NULL, 'sys:schedule:run', 2, NULL, 0);
INSERT INTO `sys_menu`
VALUES (14, 6, '日志列表', NULL, 'sys:schedule:log', 2, NULL, 0);
INSERT INTO `sys_menu`
VALUES (15, 2, '查看', NULL, 'sys:user:list,sys:user:info', 2, NULL, 0);
INSERT INTO `sys_menu`
VALUES (16, 2, '新增', NULL, 'sys:user:save,sys:role:select', 2, NULL, 0);
INSERT INTO `sys_menu`
VALUES (17, 2, '修改', NULL, 'sys:user:update,sys:role:select', 2, NULL, 0);
INSERT INTO `sys_menu`
VALUES (18, 2, '删除', NULL, 'sys:user:delete', 2, NULL, 0);
INSERT INTO `sys_menu`
VALUES (19, 3, '查看', NULL, 'sys:role:list,sys:role:info', 2, NULL, 0);
INSERT INTO `sys_menu`
VALUES (20, 3, '新增', NULL, 'sys:role:save,sys:menu:list', 2, NULL, 0);
INSERT INTO `sys_menu`
VALUES (21, 3, '修改', NULL, 'sys:role:update,sys:menu:list', 2, NULL, 0);
INSERT INTO `sys_menu`
VALUES (22, 3, '删除', NULL, 'sys:role:delete', 2, NULL, 0);
INSERT INTO `sys_menu`
VALUES (23, 4, '查看', NULL, 'sys:menu:list,sys:menu:info', 2, NULL, 0);
INSERT INTO `sys_menu`
VALUES (24, 4, '新增', NULL, 'sys:menu:save,sys:menu:select', 2, NULL, 0);
INSERT INTO `sys_menu`
VALUES (25, 4, '修改', NULL, 'sys:menu:update,sys:menu:select', 2, NULL, 0);
INSERT INTO `sys_menu`
VALUES (26, 4, '删除', NULL, 'sys:menu:delete', 2, NULL, 0);
INSERT INTO `sys_menu`
VALUES (27, 1, '参数管理', 'sys/config',
        'sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete', 1, 'config', 6);
INSERT INTO `sys_menu`
VALUES (29, 1, '系统日志', 'sys/log', 'sys:log:list', 1, 'log', 7);
INSERT INTO `sys_menu`
VALUES (30, 1, '文件上传', 'oss/oss', 'sys:oss:all', 1, 'oss', 6);

-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `url`         varchar(200) NULL DEFAULT NULL COMMENT 'URL地址',
    `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) AUTO_INCREMENT = 1  COMMENT = '文件上传';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `role_id`        bigint(20) NOT NULL AUTO_INCREMENT,
    `role_name`      varchar(100) NULL DEFAULT NULL COMMENT '角色名称',
    `remark`         varchar(100) NULL DEFAULT NULL COMMENT '备注',
    `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
    `create_time`    bigint(15) NULL COMMENT '创建时间',
    PRIMARY KEY (`role_id`) USING BTREE
) AUTO_INCREMENT = 1  COMMENT = '角色';

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `id`      bigint(20) NOT NULL AUTO_INCREMENT,
    `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
    `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单ID',
    PRIMARY KEY (`id`) USING BTREE
) AUTO_INCREMENT = 1  COMMENT = '角色与菜单对应关系';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `user_id`        bigint(20) NOT NULL AUTO_INCREMENT,
    `username`       varchar(50) NOT NULL COMMENT '用户名',
    `password`       varchar(100) NULL DEFAULT NULL COMMENT '密码',
    `salt`           varchar(20) NULL DEFAULT NULL COMMENT '盐',
    `email`          varchar(100) NULL DEFAULT NULL COMMENT '邮箱',
    `mobile`         varchar(100) NULL DEFAULT NULL COMMENT '手机号',
    `status`         tinyint(4) NULL DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
    `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
    `create_time`    bigint(15) NULL COMMENT '创建时间',
    PRIMARY KEY (`user_id`) USING BTREE,
    UNIQUE INDEX `username`(`username`) USING BTREE
) AUTO_INCREMENT = 3  COMMENT = '系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user`
VALUES (1, 'admin', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e',
        'root@secdn.com', '13612345678', 1, 1, null);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `id`      bigint(20) NOT NULL AUTO_INCREMENT,
    `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
    `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
    PRIMARY KEY (`id`) USING BTREE
) AUTO_INCREMENT = 1  COMMENT = '用户与角色对应关系';

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token`
(
    `user_id`     bigint(20) NOT NULL,
    `token`       varchar(100) NOT NULL COMMENT 'token',
    `expire_time` bigint(15) NULL COMMENT '过期时间',
    `update_time` bigint(15) NULL COMMENT '更新时间',
    PRIMARY KEY (`user_id`) USING BTREE,
    UNIQUE INDEX `token`(`token`) USING BTREE
) COMMENT = '系统用户Token';
