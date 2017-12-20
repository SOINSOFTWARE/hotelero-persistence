package com.soinsoftware.hotelero.persistence.dao;

import java.io.IOException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import com.soinsoftware.hotelero.persistence.entity.InvoiceStatus;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
@Transactional
@SuppressWarnings("unchecked")
public class InvoiceStatusDao extends AbstractDataAccessibleObject<InvoiceStatus, Integer> {

	public InvoiceStatusDao() throws IOException {
		super();
	}

	@Override
	public List<InvoiceStatus> selectAll() {
		final Criteria criteria = buildCriteria();
		return (List<InvoiceStatus>) criteria.list();
	}

	@Override
	public List<InvoiceStatus> selectAll(final boolean enabled) {
		final Criteria criteria = buildCriteriaWithEnabledRestriction(enabled);
		return (List<InvoiceStatus>) criteria.list();
	}

	@Override
	public InvoiceStatus selectById(final Integer pk) {
		return manager.find(InvoiceStatus.class, pk);
	}

	@Override
	public Criteria buildCriteria() {
		final Session session = (Session) manager.getDelegate();
		return session.createCriteria(InvoiceStatus.class);
	}

	public InvoiceStatus select(final String name) {
		final Session session = (Session) manager.getDelegate();
		return session.bySimpleNaturalId(InvoiceStatus.class).load(name);
	}
}