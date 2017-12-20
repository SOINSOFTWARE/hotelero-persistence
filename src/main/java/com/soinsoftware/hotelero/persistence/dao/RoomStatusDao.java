package com.soinsoftware.hotelero.persistence.dao;

import java.io.IOException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import com.soinsoftware.hotelero.persistence.entity.RoomStatus;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
@Transactional
@SuppressWarnings("unchecked")
public class RoomStatusDao extends AbstractDataAccessibleObject<RoomStatus, Integer> {

	public RoomStatusDao() throws IOException {
		super();
	}

	@Override
	public List<RoomStatus> selectAll() {
		final Criteria criteria = buildCriteria();
		return (List<RoomStatus>) criteria.list();
	}

	@Override
	public List<RoomStatus> selectAll(boolean enabled) {
		final Criteria criteria = buildCriteriaWithEnabledRestriction(enabled);
		return (List<RoomStatus>) criteria.list();
	}

	@Override
	public RoomStatus selectById(final Integer pk) {
		return manager.find(RoomStatus.class, pk);
	}

	@Override
	public Criteria buildCriteria() {
		final Session session = (Session) manager.getDelegate();
		return session.createCriteria(RoomStatus.class);
	}

	public RoomStatus select(final String name) {
		final Session session = (Session) manager.getDelegate();
		return session.bySimpleNaturalId(RoomStatus.class).load(name);
	}
}