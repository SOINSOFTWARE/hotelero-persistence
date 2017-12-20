package com.soinsoftware.hotelero.persistence.dao;

import java.io.IOException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import com.soinsoftware.hotelero.persistence.entity.Company;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
@Transactional
@SuppressWarnings("unchecked")
public class CompanyDao extends AbstractDataAccessibleObject<Company, Integer> {

	public CompanyDao() throws IOException {
		super();
	}

	@Override
	public List<Company> selectAll() {
		final Criteria criteria = buildCriteria();
		return (List<Company>) criteria.list();
	}

	@Override
	public List<Company> selectAll(final boolean enabled) {
		final Criteria criteria = buildCriteriaWithEnabledRestriction(enabled);
		return (List<Company>) criteria.list();
	}

	@Override
	public Company selectById(final Integer pk) {
		return manager.find(Company.class, pk);
	}

	@Override
	public Criteria buildCriteria() {
		final Session session = (Session) manager.getDelegate();
		return session.createCriteria(Company.class);
	}
	
	public Company select(final String name) {
		final Session session = (Session) manager.getDelegate();
		return session.bySimpleNaturalId(Company.class).load(name);
	}
}