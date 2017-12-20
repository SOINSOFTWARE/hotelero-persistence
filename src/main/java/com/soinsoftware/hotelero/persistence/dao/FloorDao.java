package com.soinsoftware.hotelero.persistence.dao;

import java.io.IOException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import com.soinsoftware.hotelero.persistence.entity.Floor;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
@Transactional
@SuppressWarnings("unchecked")
public class FloorDao extends AbstractDataAccessibleObject<Floor, Integer> {

	public FloorDao() throws IOException {
		super();
	}

	@Override
	public List<Floor> selectAll() {
		final Criteria criteria = buildCriteria();
		return (List<Floor>) criteria.list();
	}

	@Override
	public List<Floor> selectAll(final boolean enabled) {
		final Criteria criteria = buildCriteriaWithEnabledRestriction(enabled);
		return (List<Floor>) criteria.list();
	}

	@Override
	public Floor selectById(final Integer pk) {
		return manager.find(Floor.class, pk);
	}

	@Override
	public Criteria buildCriteria() {
		final Session session = (Session) manager.getDelegate();
		return session.createCriteria(Floor.class);
	}

	public Floor select(final String code) {
		final Session session = (Session) manager.getDelegate();
		return session.bySimpleNaturalId(Floor.class).load(code);
	}
}
