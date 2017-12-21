package com.soinsoftware.hotelero.persistence.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.soinsoftware.hotelero.persistence.entity.Invoice;
import com.soinsoftware.hotelero.persistence.entity.InvoiceItem;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
@Transactional
@SuppressWarnings("unchecked")
public class InvoiceItemDao extends AbstractDataAccessibleObject<InvoiceItem, Integer> {

	public InvoiceItemDao() throws IOException {
		super();
	}

	@Override
	public List<InvoiceItem> selectAll() {
		final Criteria criteria = buildCriteria();
		return criteria.list();
	}

	@Override
	public List<InvoiceItem> selectAll(final boolean enabled) {
		final Criteria criteria = buildCriteriaWithEnabledRestriction(enabled);
		return criteria.list();
	}

	@Override
	public InvoiceItem selectById(final Integer pk) {
		return manager.find(InvoiceItem.class, pk);
	}

	@Override
	public Criteria buildCriteria() {
		final Session session = (Session) manager.getDelegate();
		return session.createCriteria(InvoiceItem.class);
	}

	public List<InvoiceItem> selectByInvoice(final Invoice invoice) {
		final Criteria criteria = buildCriteria();
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("invoice", invoice));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}
}