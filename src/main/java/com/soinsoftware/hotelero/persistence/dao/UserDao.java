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
import com.soinsoftware.hotelero.persistence.entity.User;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
@Transactional
@SuppressWarnings("unchecked")
public class UserDao extends AbstractDataAccessibleObject<User, Integer> {

	public UserDao() throws IOException {
		super();
	}

	@Override
	public List<User> selectAll() {
		final Criteria criteria = buildCriteria();
		return (List<User>) criteria.list();
	}

	@Override
	public List<User> selectAll(boolean enabled) {
		final Criteria criteria = buildCriteriaWithEnabledRestriction(enabled);
		return (List<User>) criteria.list();
	}

	@Override
	public User selectById(final Integer pk) {
		return manager.find(User.class, pk);
	}

	@Override
	public Criteria buildCriteria() {
		final Session session = (Session) manager.getDelegate();
		return session.createCriteria(User.class);
	}

	public User select(final long identification) {
		final Session session = (Session) manager.getDelegate();
		return session.bySimpleNaturalId(User.class).load(identification);
	}

	public User select(final String login, final String password) {
		final Criteria criteria = buildCriteria();
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("login", login));
		predicates.add(Restrictions.eq("password", password));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return (User) criteria.uniqueResult();
	}

	public List<User> select(final Hotel hotel) {
		final Criteria criteria = buildCriteria();
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("enabled", true));
		predicates.add(Restrictions.eq("hotel", hotel));
		criteria.add(Restrictions.and(buildPredicates(predicates)));
		return criteria.list();
	}
}