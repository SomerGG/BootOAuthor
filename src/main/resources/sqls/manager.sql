/* allUserMenuId.get*/
SELECT sm.* FROM sys_menu sm LEFT JOIN sys_role_menu srm ON srm.menu_id = sm.id
LEFT JOIN sys_user su ON su.role_id = srm.role_id $condition

/* userPermissions.all */
SELECT sp.name permName, sp.perms perms FROM sys_permission sp
LEFT JOIN sys_role_permission srp ON sp.id = srp.perm_id
LEFT JOIN sys_role sr ON sr.id = srp.role_id
LEFT JOIN sys_user su ON su.role_id = sr.id $condition

/* permissions.all */
SELECT sp.id id, sp.name permName, sp.perms perms FROM sys_permission sp $condition

/* rolePermission.all */
SELECT sp.id id, sp.name permsName, sp.perms perms
FROM sys_role_permission srp LEFT JOIN sys_permission sp ON srp.perm_id = sp.id $condition

/* currentUser.get */
SELECT su.username username, sr.role_name roleName
FROM sys_user su LEFT JOIN sys_role sr ON su.role_id = sr.id $condition

/* app.all */
SELECT ap.id id, su.true_name applyName, ap.app_name appName, ap.create_time createTime, ap.update_time updateTime, ap.check_state checkState, ap.app_type appType
FROM app_info ap LEFT JOIN sys_user su ON ap.user_id = su.id $condition limit @pageSize offset @pageNumber;

/* app.totalSize */
SELECT COUNT(*) totalNumber FROM app_info ap LEFT JOIN sys_user su ON ap.user_id = su.id $condition;