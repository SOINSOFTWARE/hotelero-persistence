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
import com.soinsoftware.hotelero.persistence.entity.ServiceType;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
@Transactional
@SuppressWarnings("unchecked")
public class ServiceTypeDao extends AbstractDataAccessibleObject<ServiceType, Integer> {

	public ServiceTypeDao() throws IOException {
		super();
	}

	@Override
	public List<ServiceType> selectAll() {
		final Criteria criteria = buildCriteria();
		return (List<ServiceType>) criteria.list();
	}

	@Override
	public List<ServiceType> selectAll(final boolean enabled) {
		final Criteria criteria = buildCriteriaWithEnabledRestriction(enabled);
		return (List<ServiceType>) criteria.list();
	}

	@Override
	public ServiceType selectById(final Integer pk) {
		return manager.find(ServiceType.class, pk);
	}

	@Override
	public Criteria buildCriteria() {
		final Session session = (Session) manager.getDelegate();
		return session.createCriteria(ServiceType.class);
	}

	public ServiceType select(final String name) {
		final Session session = (Session) manager.getDelegate();
		return session.bySimpleNaturalId(ServiceType.class).load(name);
	}

	public List<ServiceType> select(final Hotel hotel) {
		final Criteria criteria = buildCriteria();
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("enabled", true));
		predicates.add(Restrictions.eq("hotel", hotel));
		criteria.add(Restrictions.and(buildPredicates(predicates)));
		return criteria.list();
	}
}