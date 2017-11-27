package com.soinsoftware.hotelero.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.soinsoftware.hotelero.persistence.entity.Service;
import com.soinsoftware.hotelero.persistence.entity.ServiceType;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class ServiceDao extends AbstractDataAccessibleObject<Service, Integer> {

	public ServiceDao(final EntityManager manager) {
		super(manager);
	}

	@Override
	public List<Service> selectAll() {
		final Criteria criteria = buildCriteria();
		return (List<Service>) criteria.list();
	}

	@Override
	public List<Service> selectAll(boolean enabled) {
		final Criteria criteria = buildCriteriaWithEnabledRestriction(enabled);
		return (List<Service>) criteria.list();
	}

	@Override
	public Service selectById(Integer pk) {
		return manager.find(Service.class, pk);
	}

	@Override
	public Criteria buildCriteria() {
		final Session session = (Session) manager.getDelegate();
		return session.createCriteria(Service.class);
	}
	
	public Service select(final String name) {
		final Session session = (Session) manager.getDelegate();
		return session.bySimpleNaturalId(Service.class).load(name);
	}

	public List<Service> selectByServiceType(final ServiceType serviceType) {
		final Criteria criteria = buildCriteria();
		final Criterion lessCr = Restrictions.le("serviceType", serviceType);
		final Criterion enabledCr = Restrictions.eq("enabled", true);
		final Criterion criterion = Restrictions.and(lessCr, enabledCr);
		criteria.add(criterion);
		return (List<Service>) criteria.list();
	}
}