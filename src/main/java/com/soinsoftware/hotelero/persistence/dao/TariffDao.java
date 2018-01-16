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
import com.soinsoftware.hotelero.persistence.entity.Tariff;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
@Transactional
@SuppressWarnings("unchecked")
public class TariffDao extends AbstractDataAccessibleObject<Tariff, Integer> {

	public TariffDao() throws IOException {
		super();
	}

	@Override
	public List<Tariff> selectAll() {
		final Criteria criteria = buildCriteria();
		return (List<Tariff>) criteria.list();
	}

	@Override
	public List<Tariff> selectAll(final boolean enabled) {
		final Criteria criteria = buildCriteriaWithEnabledRestriction(enabled);
		return (List<Tariff>) criteria.list();
	}

	@Override
	public Tariff selectById(final Integer pk) {
		return manager.find(Tariff.class, pk);
	}

	@Override
	public Criteria buildCriteria() {
		final Session session = (Session) manager.getDelegate();
		return session.createCriteria(Tariff.class);
	}

	public Tariff select(final String code) {
		final Session session = (Session) manager.getDelegate();
		return session.bySimpleNaturalId(Tariff.class).load(code);
	}

	public List<Tariff> select(final Hotel hotel) {
		final Criteria criteria = buildCriteria();
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("enabled", true));
		predicates.add(Restrictions.eq("hotel", hotel));
		criteria.add(Restrictions.and(buildPredicates(predicates)));
		return criteria.list();
	}
}
