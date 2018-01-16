package com.soinsoftware.hotelero.persistence.dao;

import java.io.IOException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import com.soinsoftware.hotelero.persistence.entity.Hotel;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
@Transactional
@SuppressWarnings("unchecked")
public class HotelDao extends AbstractDataAccessibleObject<Hotel, Integer> {

	public HotelDao() throws IOException {
		super();
	}

	@Override
	public List<Hotel> selectAll() {
		final Criteria criteria = buildCriteria();
		return (List<Hotel>) criteria.list();
	}

	@Override
	public List<Hotel> selectAll(final boolean enabled) {
		final Criteria criteria = buildCriteriaWithEnabledRestriction(enabled);
		return (List<Hotel>) criteria.list();
	}

	@Override
	public Hotel selectById(final Integer pk) {
		return manager.find(Hotel.class, pk);
	}

	@Override
	public Criteria buildCriteria() {
		final Session session = (Session) manager.getDelegate();
		return session.createCriteria(Hotel.class);
	}

	public Hotel select(final String nit) {
		final Session session = (Session) manager.getDelegate();
		return session.bySimpleNaturalId(Hotel.class).load(nit);
	}
}