package net.dubrouski.fams.dao.impl;

import javax.ejb.Stateless;
import javax.inject.Named;

import net.dubrouski.fams.dao.UserRightDao;
import net.dubrouski.fams.model.UserRight;
import net.dubrouski.fams.model.enums.UserRightIds;

/**
 *
 * @author Martin Jelínek (xjeline5)
 */
@Stateless
@Named(value = "userRightDao")
public class UserRightDaoImpl extends BaseDaoImpl<UserRight, UserRightIds> implements UserRightDao {

}
