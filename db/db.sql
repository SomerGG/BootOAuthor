-- 角色表初始化数据
INSERT INTO "sys_role" ("id", "type", "role_name") VALUES (1, '1', '超级管理员');
INSERT INTO "sys_role" ("id", "type", "role_name") VALUES (2, '2', '节点');
INSERT INTO "sys_role" ("id", "type", "role_name") VALUES (3, '3', '学校');
INSERT INTO "sys_role" ("id", "type", "role_name") VALUES (4, '4', '教师');
INSERT INTO "sys_role" ("id", "type", "role_name") VALUES (5, '5', '学生');

-- 用户表初始化数据
INSERT INTO "sys_user" ("id", "role_id", "username", "password", "status", "create_time", "person_id") VALUES (1, '1', 'admin', '123456', '1', '2018-3-20 12:16:25', NULL);
INSERT INTO "sys_user" ("id", "role_id", "username", "password", "status", "create_time", "person_id") VALUES (2, '3', 'common', '123456', '1', '2018-3-20 13:57:01', NULL);

-- 菜单表初始数据
INSERT INTO "sys_menu" ("id", "parent_id", "type", "name", "url", "icon", "order_num") VALUES (1, '0', '0', '系统管理', 'haha', 'haha', '1');
INSERT INTO "sys_menu" ("id", "parent_id", "type", "name", "url", "icon", "order_num") VALUES (4, '0', '0', '信息管理', 'haha', 'haha', '1');
INSERT INTO "sys_menu" ("id", "parent_id", "type", "name", "url", "icon", "order_num") VALUES (5, '4', '1', '学校管理', 'haha', 'haha', '2');
INSERT INTO "sys_menu" ("id", "parent_id", "type", "name", "url", "icon", "order_num") VALUES (7, '0', '0', '机构管理', 'haha', 'haha', '1');
INSERT INTO "sys_menu" ("id", "parent_id", "type", "name", "url", "icon", "order_num") VALUES (8, '7', '1', '组织机构', 'haha', 'haha', '2');
INSERT INTO "sys_menu" ("id", "parent_id", "type", "name", "url", "icon", "order_num") VALUES (9, '0', '0', '应用管理', NULL, NULL, '1');
INSERT INTO "sys_menu" ("id", "parent_id", "type", "name", "url", "icon", "order_num") VALUES (10, '9', '1', 'APP管理', '/AppManage', 'app', '2');
INSERT INTO "sys_menu" ("id", "parent_id", "type", "name", "url", "icon", "order_num") VALUES (12, '0', '0', '人员信息', NULL, NULL, '1');
INSERT INTO "sys_menu" ("id", "parent_id", "type", "name", "url", "icon", "order_num") VALUES (13, '12', '1', '人员管理', NULL, NULL, '2');
INSERT INTO "sys_menu" ("id", "parent_id", "type", "name", "url", "icon", "order_num") VALUES (14, '1', '1', '哈哈', 'haha', 'haha', '1');
INSERT INTO "sys_menu" ("id", "parent_id", "type", "name", "url", "icon", "order_num") VALUES (16, '1', '1', '菜单管理', '/MenuManagement', '321', '1');

-- 角色-菜单表初始数据
INSERT INTO "sys_role_menu" ("role_id", "menu_id") VALUES ('1', '1');
INSERT INTO "sys_role_menu" ("role_id", "menu_id") VALUES ('1', '2');
INSERT INTO "sys_role_menu" ("role_id", "menu_id") VALUES ('1', '3');
INSERT INTO "sys_role_menu" ("role_id", "menu_id") VALUES ('1', '4');
INSERT INTO "sys_role_menu" ("role_id", "menu_id") VALUES ('1', '5');
INSERT INTO "sys_role_menu" ("role_id", "menu_id") VALUES ('1', '6');
INSERT INTO "sys_role_menu" ("role_id", "menu_id") VALUES ('1', '7');
INSERT INTO "sys_role_menu" ("role_id", "menu_id") VALUES ('1', '8');
INSERT INTO "sys_role_menu" ("role_id", "menu_id") VALUES ('1', '9');
INSERT INTO "sys_role_menu" ("role_id", "menu_id") VALUES ('1', '11');
INSERT INTO "sys_role_menu" ("role_id", "menu_id") VALUES ('1', '12');
INSERT INTO "sys_role_menu" ("role_id", "menu_id") VALUES ('1', '10');
INSERT INTO "sys_role_menu" ("role_id", "menu_id") VALUES ('1', '13');

-- 权限表初始化数据
INSERT INTO "sys_permission" ("id", "name", "perms", "description") VALUES (1, '添加菜单', 'sys:menu:add', '添加菜单信息');
INSERT INTO "sys_permission" ("id", "name", "perms", "description") VALUES (2, '更新菜单', 'sys:menu:update', '更新菜单信息');
INSERT INTO "sys_permission" ("id", "name", "perms", "description") VALUES (3, '查看菜单', 'sys:menu:list,sys:menu:info', '查看菜单信息');
INSERT INTO "sys_permission" ("id", "name", "perms", "description") VALUES (4, '删除菜单', 'sys:menu:delete', '删除菜单信息');
INSERT INTO "sys_permission" ("id", "name", "perms", "description") VALUES (5, '添加角色', 'sys:role:add', '添加角色信息');
INSERT INTO "sys_permission" ("id", "name", "perms", "description") VALUES (6, '更新角色', 'sys:role:update', '更新角色信息');
INSERT INTO "sys_permission" ("id", "name", "perms", "description") VALUES (8, '设置菜单', 'sys:role:set', '设置角色菜单信息');
INSERT INTO "sys_permission" ("id", "name", "perms", "description") VALUES (9, '设置权限', 'sys:perms:set', '设置角色权限信息');
INSERT INTO "sys_permission" ("id", "name", "perms", "description") VALUES (10, '添加权限', 'sys:perms:add', '添加权限信息');
INSERT INTO "sys_permission" ("id", "name", "perms", "description") VALUES (11, '查看权限', 'sys:perms:list', '查看全部权限信息');
INSERT INTO "sys_permission" ("id", "name", "perms", "description") VALUES (13, '查看角色菜单', 'sys:role:menu', '查看角色对应的菜单信息');
INSERT INTO "sys_permission" ("id", "name", "perms", "description") VALUES (12, '查看角色权限', 'sys:role:perms', '查看角色对应的权限信息');
INSERT INTO "sys_permission" ("id", "name", "perms", "description") VALUES (14, '添加组织机构', 'sys:org:add', '添加组织机构信息');
INSERT INTO "sys_permission" ("id", "name", "perms", "description") VALUES (7, '查看角色', 'sys:role:list,sys:role:info', '查看全部角色信息');
INSERT INTO "sys_permission" ("id", "name", "perms", "description") VALUES (15, '更新组织机构', 'sys:org:update', '更新组织机构信息');
INSERT INTO "sys_permission" ("id", "name", "perms", "description") VALUES (17, '删除组织机构', 'sys:org:delete', '删除组织机构信息');
INSERT INTO "sys_permission" ("id", "name", "perms", "description") VALUES (18, '添加学校', 'sys:school:add', '添加学校信息');
INSERT INTO "sys_permission" ("id", "name", "perms", "description") VALUES (19, '更新学校', 'sys:school:update', '更新学校信息');
INSERT INTO "sys_permission" ("id", "name", "perms", "description") VALUES (22, '添加班级', 'sys:clazz:add', '添加学校信息');
INSERT INTO "sys_permission" ("id", "name", "perms", "description") VALUES (21, '删除学校', 'sys:school:delete', '删除学校信息');
INSERT INTO "sys_permission" ("id", "name", "perms", "description") VALUES (20, '查看学校', 'sys:school:list,sys:school:info', '查看学校信息');
INSERT INTO "sys_permission" ("id", "name", "perms", "description") VALUES (16, '查看组织机构', 'sys:org:list,sys:org:info', '查看组织机构信息');
INSERT INTO "sys_permission" ("id", "name", "perms", "description") VALUES (23, '更新班级', 'sys:clazz:update', '更新班级信息');
INSERT INTO "sys_permission" ("id", "name", "perms", "description") VALUES (24, '查看班级', 'sys:clazz:list,sys:clazz:info', '查看班级信息');
INSERT INTO "sys_permission" ("id", "name", "perms", "description") VALUES (25, '删除班级', 'sys:clazz:delete', '删除班级信息');
INSERT INTO "sys_permission" ("id", "name", "perms", "description") VALUES (26, '添加人员', 'sys:person:add', '添加人员信息');
INSERT INTO "sys_permission" ("id", "name", "perms", "description") VALUES (27, '更新人员', 'sys:person:update', '更新人员信息');
INSERT INTO "sys_permission" ("id", "name", "perms", "description") VALUES (28, '查看人员', 'sys:person:list,sys:person:info', '查看人员信息');
INSERT INTO "sys_permission" ("id", "name", "perms", "description") VALUES (29, '删除人员', 'sys:person:delete', '删除人员信息');

-- 组织机构表初始化数据
INSERT INTO "organization" ("id", "name", "code", "org_property", "org_type", "address") VALUES ('5ab0812534f7be2604fddb4d', '贵阳市政府', 'FSDFGFD23232', '政府机构', '政府部门', '贵州省贵阳市');
INSERT INTO "organization" ("id", "name", "code", "org_property", "org_type", "address") VALUES ('5ab0934234f7be2c941de4bd', '贵州省教育厅', 'GZSJYT1512000', '教育机构', '教育部门', '贵州省贵阳市');

-- 学校表初始化数据
INSERT INTO "school" ("id", "belong_id", "name", "mark_code", "address", "school_type", "organizer_type", "principal_name", "contact_number") VALUES ('5ab0939734f7be2f08bca4b3', '5ab0934234f7be2c941de4bd', '贵州大学', 'GZDX15412312', '贵州省贵阳市花溪区', '大学', '教育部', '陈坚', '15185214569');
INSERT INTO "school" ("id", "belong_id", "name", "mark_code", "address", "school_type", "organizer_type", "principal_name", "contact_number") VALUES ('5ab093d434f7be2f8c226547', '5ab0934234f7be2c941de4bd', '贵州名族大学', 'GZMZDX184123', '贵州省贵阳市花溪区', '大学', '其他部门', 'XXX', '18785124569');

-- 班级表初始化数据
INSERT INTO "clazz" ("id", "school_id", "grade", "clazz", "head_teacher") VALUES (1, '5ab0939734f7be2f08bca4b3', '1', '计科121', '王勇');
INSERT INTO "clazz" ("id", "school_id", "grade", "clazz", "head_teacher") VALUES (2, '5ab0939734f7be2f08bca4b3', '1', '机自121', 'XXX');
INSERT INTO "clazz" ("id", "school_id", "grade", "clazz", "head_teacher") VALUES (3, '5ab0939734f7be2f08bca4b3', '2', '计科112', '张三');

-- 学籍信息表初始化数据
INSERT INTO "school_roll" ("id", "belong_id", "student_number", "school", "school_name", "clazz", "clazz_name", "study_way", "is_study", "study_time") VALUES ('5ab0983434f7be14e4a75197', '1', '1208100178', '5ab0939734f7be2f08bca4b3', '贵州大学', '1', '计科121', '统招', '1', '2012');

-- 人员信息表初始化数据
INSERT INTO "person" ("id", "person_id", "type", "name", "gender", "ethnic", "birthday", "native_place", "id_card", "political_status", "contact_number", "identity_number", "school_id", "position", "department", "guardian", "guardian_phone", "is_only_child", "username") VALUES (1, '5ab0941434f7be18a47bc73c', '5', '徐洋', '0', '仡佬族', '1994-02-08', '贵州石阡', '522224199402083651', '团员', '18785145231', '1208100178', '5ab0939734f7be2f08bca4b3', '学生', NULL, NULL, NULL, '1', 'Somer');
INSERT INTO "person" ("id", "person_id", "type", "name", "gender", "ethnic", "birthday", "native_place", "id_card", "political_status", "contact_number", "identity_number", "school_id", "position", "department", "guardian", "guardian_phone", "is_only_child", "username") VALUES (2, '5ab1d7b434f7be106cbc9eed', '5', '徐守来', '0', '汉族', '1992-02-03', '湖北黄冈', '322215199202033654', '团员', '18785124569', '1208100110', '5ab0939734f7be2f08bca4b3', '学生', NULL, NULL, NULL, '1', 'Somous');
INSERT INTO "person" ("id", "person_id", "type", "name", "gender", "ethnic", "birthday", "native_place", "id_card", "political_status", "contact_number", "identity_number", "school_id", "position", "department", "guardian", "guardian_phone", "is_only_child", "username") VALUES (3, '5ab1ea5234f7be05180f5229', '3', '贵州大学', NULL, NULL, NULL, NULL, NULL, NULL, '7868254', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'gzdx');
INSERT INTO "person" ("id", "person_id", "type", "name", "gender", "ethnic", "birthday", "native_place", "id_card", "political_status", "contact_number", "identity_number", "school_id", "position", "department", "guardian", "guardian_phone", "is_only_child", "username") VALUES (4, '5ab1ea9a34f7be29fcab202f', '4', '王勇', '0', '汉族', '1978-03-21', '贵州贵阳', '522223197803213654', '党员', '18585412365', '1102030121', '5ab0939734f7be2f08bca4b3', '教师', NULL, NULL, NULL, NULL, 'wangyon');
