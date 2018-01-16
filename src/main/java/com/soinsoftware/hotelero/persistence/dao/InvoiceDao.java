package com.soinsoftware.hotelero.persistence.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.soinsoftware.hotelero.persistence.entity.Company;
import com.soinsoftware.hotelero.persistence.entity.Hotel;
import com.soinsoftware.hotelero.persistence.entity.Invoice;
import com.soinsoftware.hotelero.persistence.entity.InvoiceStatus;
import com.soinsoftware.hotelero.persistence.entity.RoomStatus;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
@Transactional
@SuppressWarnings("unchecked")
public class InvoiceDao extends AbstractDataAccessibleObject<Invoice, Integer> {

	public InvoiceDao() throws IOException {
		super();
	}

	@Override
	public List<Invoice> selectAll() {
		final Criteria criteria = buildCriteria();
		return criteria.list();
	}

	@Override
	public List<Invoice> selectAll(final boolean enabled) {
		final Criteria criteria = buildCriteriaWithEnabledRestriction(enabled);
		return criteria.list();
	}

	@Override
	public Invoice selectById(final Integer pk) {
		return manager.find(Invoice.class, pk);
	}

	@Override
	public Criteria buildCriteria() {
		final Session session = (Session) manager.getDelegate();
		return session.createCriteria(Invoice.class);
	}

	public List<Invoice> selectByStatus(final RoomStatus roomStatus) {
		final Criteria criteria = buildCriteria();
		final Criterion criterion = buildCriterion(roomStatus, 0, 0, null, null);
		criteria.add(criterion);
		return criteria.list();
	}

	public List<Invoice> selectByStatus(final RoomStatus roomStatus, final Date initialDate) {
		final Criteria criteria = buildCriteria();
		final Criterion criterion = buildCriterion(roomStatus, initialDate);
		criteria.add(criterion);
		return criteria.list();
	}

	public List<Invoice> selectByNonStatus(final RoomStatus roomStatus, final Date initialDate, final Date finalDate) {
		final Criteria criteria = buildCriteria();
		final Criterion criterion = buildCriterion(roomStatus, initialDate, finalDate);
		criteria.add(criterion);
		return criteria.list();
	}

	public List<Invoice> selectByStatus(final RoomStatus roomStatus, final int year, final int month) {
		final Criteria criteria = buildCriteria();
		final Criterion criterion = buildCriterion(roomStatus, year, month, null, null);
		criteria.add(criterion);
		return criteria.list();
	}

	public List<Invoice> selectByStatus(final RoomStatus roomStatus, final int year, final int month,
			final InvoiceStatus invoiceStatus) {
		final Criteria criteria = buildCriteria();
		final Criterion criterion = buildCriterion(roomStatus, year, month, invoiceStatus, null);
		criteria.add(criterion);
		return criteria.list();
	}

	public List<Invoice> selectByStatus(final RoomStatus roomStatus, final int year, final int month,
			final InvoiceStatus invoiceStatus, final Company company) {
		final Criteria criteria = buildCriteria();
		final Criterion criterion = buildCriterion(roomStatus, year, month, invoiceStatus, company);
		criteria.add(criterion);
		return criteria.list();
	}

	public List<Invoice> select(final Hotel hotel) {
		final Criteria criteria = buildCriteria();
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("enabled", true));
		predicates.add(Restrictions.eq("hotel", hotel));
		criteria.add(Restrictions.and(buildPredicates(predicates)));
		return criteria.list();
	}

	private Criterion buildCriterion(final RoomStatus roomStatus, final int year, final int month,
			final InvoiceStatus invoiceStatus, final Company company) {
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("enabled", true));
		predicates.add(Restrictions.eq("roomStatus", roomStatus));
		if (year > 0 && month > 0) {
			predicates.add(Restrictions.ge("initialDate", toStartOfMonth(year, month)));
			predicates.add(Restrictions.le("initialDate", toEndOfMonth(year, month)));
		}
		if (invoiceStatus != null) {
			predicates.add(Restrictions.eq("invoiceStatus", invoiceStatus));
		}
		if (company != null) {
			predicates.add(Restrictions.eq("company", company));
		}
		return Restrictions.and(buildPredicates(predicates));
	}

	private Criterion buildCriterion(final RoomStatus roomStatus, final Date initialDate) {
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("enabled", true));
		predicates.add(Restrictions.eq("roomStatus", roomStatus));
		predicates.add(Restrictions.ge("initialDate", initialDate));
		return Restrictions.and(buildPredicates(predicates));
	}

	private Criterion buildCriterion(final RoomStatus roomStatus, final Date initialDate, final Date finalDate) {
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("enabled", true));
		predicates.add(Restrictions.ne("roomStatus", roomStatus));
		final List<Criterion> datePredicates = new ArrayList<>();
		final List<Criterion> dateBetween1 = new ArrayList<>();
		dateBetween1.add(Restrictions.ge("initialDate", initialDate));
		dateBetween1.add(Restrictions.le("initialDate", finalDate));
		datePredicates.add(Restrictions.and(buildPredicates(dateBetween1)));
		final List<Criterion> dateBetween2 = new ArrayList<>();
		dateBetween2.add(Restrictions.ge("finalDate", initialDate));
		dateBetween2.add(Restrictions.le("finalDate", finalDate));
		datePredicates.add(Restrictions.and(buildPredicates(dateBetween2)));
		final List<Criterion> dateBetween3 = new ArrayList<>();
		dateBetween3.add(Restrictions.ge("initialDate", initialDate));
		dateBetween3.add(Restrictions.le("finalDate", initialDate));
		datePredicates.add(Restrictions.and(buildPredicates(dateBetween3)));
		predicates.add(Restrictions.or(buildPredicates(datePredicates)));
		return Restrictions.and(buildPredicates(predicates));
	}

	private Date toStartOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, --month);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	private Date toEndOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, -1);
		return calendar.getTime();
	}
}