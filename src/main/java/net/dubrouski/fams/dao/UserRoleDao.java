package net.dubrouski.fams.dao;

import net.dubrouski.fams.model.UserRole;
import net.dubrouski.fams.model.enums.UserRoles;

/**
 *
 * @author Martin Jelínek (xjeline5)
 */
public interface UserRoleDao extends BaseDao<UserRole, Long>{

	UserRole getByType(UserRoles type);

}
