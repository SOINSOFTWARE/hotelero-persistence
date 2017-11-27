package com.soinsoftware.hotelero.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.soinsoftware.hotelero.persistence.entity.Room;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class RoomDao extends AbstractDataAccessibleObject<Room, Integer> {

	public RoomDao(final EntityManager manager) {
		super(manager);
	}

	@Override
	public List<Room> selectAll() {
		final Criteria criteria = buildCriteria();
		return (List<Room>) criteria.list();
	}

	@Override
	public List<Room> selectAll(boolean enabled) {
		final Criteria criteria = buildCriteriaWithEnabledRestriction(enabled);
		return (List<Room>) criteria.list();
	}

	@Override
	public Room selectById(final Integer pk) {
		return manager.find(Room.class, pk);
	}

	@Override
	public Criteria buildCriteria() {
		final Session session = (Session) manager.getDelegate();
		return session.createCriteria(Room.class);
	}

	public Room select(final String name) {
		final Session session = (Session) manager.getDelegate();
		return session.bySimpleNaturalId(Room.class).load(name);
	}
}