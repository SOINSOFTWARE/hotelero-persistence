package com.soinsoftware.hotelero.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.soinsoftware.hotelero.persistence.entity.RoomType;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class RoomTypeDao extends AbstractDataAccessibleObject<RoomType, Integer> {

	public RoomTypeDao(final EntityManager manager) {
		super(manager);
	}

	@Override
	public List<RoomType> selectAll() {
		final Criteria criteria = buildCriteria();
		return (List<RoomType>) criteria.list();
	}

	@Override
	public List<RoomType> selectAll(final boolean enabled) {
		final Criteria criteria = buildCriteriaWithEnabledRestriction(enabled);
		return (List<RoomType>) criteria.list();
	}

	@Override
	public RoomType selectById(final Integer pk) {
		return manager.find(RoomType.class, pk);
	}

	@Override
	public Criteria buildCriteria() {
		final Session session = (Session) manager.getDelegate();
		return session.createCriteria(RoomType.class);
	}

	public RoomType select(final String code) {
		final Session session = (Session) manager.getDelegate();
		return session.bySimpleNaturalId(RoomType.class).load(code);
	}
}
