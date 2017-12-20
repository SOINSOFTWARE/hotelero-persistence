package com.soinsoftware.hotelero.persistence.dao;

import java.io.IOException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

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
}