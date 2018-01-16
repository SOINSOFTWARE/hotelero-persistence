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
import com.soinsoftware.hotelero.persistence.entity.RoomType;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
@Transactional
@SuppressWarnings("unchecked")
public class RoomTypeDao extends AbstractDataAccessibleObject<RoomType, Integer> {

	public RoomTypeDao() throws IOException {
		super();
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

	public List<RoomType> select(final Hotel hotel) {
		final Criteria criteria = buildCriteria();
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("enabled", true));
		predicates.add(Restrictions.eq("hotel", hotel));
		criteria.add(Restrictions.and(buildPredicates(predicates)));
		return criteria.list();
	}
}
