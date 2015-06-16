package net.dubrouski.fams.dao.impl;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import net.dubrouski.fams.dao.UserRoleDao;
import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.model.UserRole;
import net.dubrouski.fams.model.enums.UserRoles;

/**
 *
 * @author Martin Jelínek (xjeline5)
 */
@Stateless
@Named(value = "userRoleDao")
public class UserRoleDaoImpl extends BaseDaoImpl<UserRole, Long> implements
		UserRoleDao {

	@Override
	public UserRole getByType(UserRoles type) {
		if (type == null) {
			throw new IllegalArgumentException("type is null.");
		}
		TypedQuery<UserRole> query = this.entityManager.createQuery(
				"select ur from UserRole ur where ur.roleName = :type",
				UserRole.class);
		try {
			return query.setParameter("type", type).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
