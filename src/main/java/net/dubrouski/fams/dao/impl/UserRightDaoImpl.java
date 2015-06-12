package net.dubrouski.fams.dao.impl;

import javax.ejb.Stateless;
import javax.inject.Named;

import net.dubrouski.fams.dao.UserRightDao;
import net.dubrouski.fams.model.UserRole;
import net.dubrouski.fams.model.enums.UserRoles;

/**
 *
 * @author Martin Jelínek (xjeline5)
 */
@Stateless
@Named(value = "userRightDao")
public class UserRightDaoImpl extends BaseDaoImpl<UserRole, UserRoles> implements UserRightDao {

}
