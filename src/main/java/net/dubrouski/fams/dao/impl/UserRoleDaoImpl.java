package net.dubrouski.fams.dao.impl;

import javax.ejb.Stateless;
import javax.inject.Named;

import net.dubrouski.fams.dao.UserRoleDao;
import net.dubrouski.fams.model.UserRole;
import net.dubrouski.fams.model.enums.UserRoles;

/**
 *
 * @author Martin Jelínek (xjeline5)
 */
@Stateless
@Named(value = "userRightDao")
public class UserRoleDaoImpl extends BaseDaoImpl<UserRole, Long> implements UserRoleDao {

}
