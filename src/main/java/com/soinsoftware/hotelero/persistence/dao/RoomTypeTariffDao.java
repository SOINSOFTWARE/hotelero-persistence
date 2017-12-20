package com.soinsoftware.hotelero.persistence.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.soinsoftware.hotelero.persistence.entity.RoomType;
import com.soinsoftware.hotelero.persistence.entity.RoomTypeTariff;
import com.soinsoftware.hotelero.persistence.entity.Tariff;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
@Transactional
@SuppressWarnings("unchecked")
public class RoomTypeTariffDao extends AbstractDataAccessibleObject<RoomTypeTariff, Integer> {

	public RoomTypeTariffDao() throws IOException {
		super();
	}

	@Override
	public List<RoomTypeTariff> selectAll() {
		final Criteria criteria = buildCriteria();
		return (List<RoomTypeTariff>) criteria.list();
	}

	@Override
	public List<RoomTypeTariff> selectAll(boolean enabled) {
		final Criteria criteria = buildCriteriaWithEnabledRestriction(enabled);
		return (List<RoomTypeTariff>) criteria.list();
	}

	@Override
	public RoomTypeTariff selectById(Integer pk) {
		return manager.find(RoomTypeTariff.class, pk);
	}

	@Override
	public Criteria buildCriteria() {
		final Session session = (Session) manager.getDelegate();
		return session.createCriteria(RoomTypeTariff.class);
	}

	public List<RoomTypeTariff> selectDateRange(final Date fromDate, final Date toDate) {
		final Criteria criteria = buildCriteria();
		final Criterion greaterCr = Restrictions.ge("fromDate", fromDate);
		final Criterion lessCr = Restrictions.le("toDate", toDate);
		final Criterion enabledCr = Restrictions.eq("enabled", true);
		final Criterion criterion = Restrictions.and(greaterCr, lessCr, enabledCr);
		criteria.add(criterion);
		return (List<RoomTypeTariff>) criteria.list();
	}

	public List<RoomTypeTariff> selectDefaultTariff(final boolean defaultTariff) {
		final Criteria criteria = buildCriteria();
		final Criterion defaultTariffCr = Restrictions.eq("defaultTariff", defaultTariff);
		final Criterion enabledCr = Restrictions.eq("enabled", true);
		final Criterion criterion = Restrictions.and(defaultTariffCr, enabledCr);
		criteria.add(criterion);
		return (List<RoomTypeTariff>) criteria.list();
	}

	public List<RoomTypeTariff> selectRoomType(final int idRoomType) {
		final Criteria criteria = buildCriteria();
		final Criterion roomTypeCr = Restrictions.eq("roomType.id", idRoomType);
		final Criterion enabledCr = Restrictions.eq("enabled", true);
		final Criterion criterion = Restrictions.and(roomTypeCr, enabledCr);
		criteria.add(criterion);
		return (List<RoomTypeTariff>) criteria.list();
	}

	public List<RoomTypeTariff> selectTariff(final int idTariff) {
		final Criteria criteria = buildCriteria();
		final Criterion tariffCr = Restrictions.eq("tariff.id", idTariff);
		final Criterion enabledCr = Restrictions.eq("enabled", true);
		final Criterion criterion = Restrictions.and(tariffCr, enabledCr);
		criteria.add(criterion);
		return (List<RoomTypeTariff>) criteria.list();
	}

	public List<RoomTypeTariff> select(final Date fromDate, final Date toDate, final Boolean defaultTariff,
			final RoomType roomType, final Tariff tariff) {
		final Criteria criteria = buildCriteria();
		final List<Criterion> predicates = new ArrayList<Criterion>();
		predicates.add(Restrictions.ge("fromDate", fromDate));
		predicates.add(Restrictions.le("toDate", toDate));
		if (defaultTariff != null) {
			predicates.add(Restrictions.eq("defaultTariff", defaultTariff));
		}
		if (roomType != null) {
			predicates.add(Restrictions.eq("roomType", roomType));
		}
		if (tariff != null) {
			predicates.add(Restrictions.eq("tariff", tariff));
		}
		predicates.add(Restrictions.eq("enabled", true));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return (List<RoomTypeTariff>) criteria.list();
	}
}
