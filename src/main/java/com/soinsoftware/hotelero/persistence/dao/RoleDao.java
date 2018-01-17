package com.soinsoftware.hotelero.persistence.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.soinsoftware.hotelero.persistence.entity.Hotel;
import com.soinsoftware.hotelero.persistence.entity.Role;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
@Transactional
@SuppressWarnings("unchecked")
public class RoleDao extends AbstractDataAccessibleObject<Role, Integer> {

	public RoleDao() throws IOException {
		super();
	}

	@Override
	public List<Role> selectAll() {
		final Criteria criteria = buildCriteria();
		return (List<Role>) criteria.list();
	}

	@Override
	public List<Role> selectAll(final boolean enabled) {
		final Criteria criteria = buildCriteriaWithEnabledRestriction(enabled);
		return (List<Role>) criteria.list();
	}

	@Override
	public Role selectById(final Integer pk) {
		return manager.find(Role.class, pk);
	}

	@Override
	public Criteria buildCriteria() {
		final Session session = (Session) manager.getDelegate();
		return session.createCriteria(Role.class);
	}

	public Role select(final String name) {
		final Session session = (Session) manager.getDelegate();
		return session.bySimpleNaturalId(Role.class).load(name);
	}

	public List<Role> select(final Hotel hotel) {
		final Criteria criteria = buildCriteria();
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("enabled", true));
		predicates.add(Restrictions.eq("hotel", hotel));
		criteria.add(Restrictions.and(buildPredicates(predicates)));
		return criteria.list();
	}
}