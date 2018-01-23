package com.soinsoftware.hotelero.persistence.dao;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.soinsoftware.hotelero.persistence.entity.Role;
import com.soinsoftware.hotelero.persistence.entity.RoleAccess;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
@Transactional
@SuppressWarnings("unchecked")
public class RoleAccessDao extends AbstractDataAccessibleObject<RoleAccess, BigInteger> {

	public RoleAccessDao() throws IOException {
		super();
	}

	@Override
	public List<RoleAccess> selectAll() {
		final Criteria criteria = buildCriteria();
		return (List<RoleAccess>) criteria.list();
	}

	@Override
	public List<RoleAccess> selectAll(final boolean enabled) {
		final Criteria criteria = buildCriteriaWithEnabledRestriction(enabled);
		return (List<RoleAccess>) criteria.list();
	}

	@Override
	public RoleAccess selectById(final BigInteger pk) {
		return manager.find(RoleAccess.class, pk);
	}

	@Override
	public Criteria buildCriteria() {
		final Session session = (Session) manager.getDelegate();
		return session.createCriteria(RoleAccess.class);
	}

	public List<RoleAccess> select(final Role role) {
		final Criteria criteria = buildCriteria();
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("enabled", true));
		predicates.add(Restrictions.eq("role", role));
		criteria.add(Restrictions.and(buildPredicates(predicates)));
		return criteria.list();
	}
}