package com.soinsoftware.hotelero.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 * This class helps to DAO objects to connect to correct database using the
 * {@link EntityManager} object that is required by the constructor.
 * 
 * @author Carlos Rodriguez
 * @since 1.0.0
 *
 * @param <T>
 *            Class that represents the table model.
 * @param <P>
 *            Class that represents the primary key.
 */
public abstract class AbstractDataAccessibleObject<T, P> implements DataAccessibleObject<T, P> {

	protected final EntityManager manager;

	/**
	 * Default constructor that must be used for all DAO implementations.
	 * 
	 * @param manager
	 *            {@link EntityManager} with correct database information.
	 */
	public AbstractDataAccessibleObject(final EntityManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public void persist(T record) {
		try {
			manager.getTransaction().begin();
			manager.persist(record);
		} finally {
			manager.getTransaction().commit();
		}
	}

	@Override
	public void persist(final EntityTransaction transaction, final T record) {
		if (!transaction.isActive()) {
			transaction.begin();
		}
		manager.persist(record);
	}

	@Override
	public void delete(final T record) {
		manager.remove(record);
	}

	@Override
	public void rollbackTransaction(final EntityTransaction transaction) {
		if (transaction != null) {
			transaction.rollback();
		}
	}

	@Override
	public void close() {
		manager.close();
	}

	/**
	 * Builds a {@link Criteria} object with a restriction.
	 * 
	 * @param enabled
	 *            filter list of data using the enabled column.
	 * @return {@link Criteria} object.
	 */
	public Criteria buildCriteriaWithEnabledRestriction(final boolean enabled) {
		final Criteria criteria = buildCriteria();
		final Criterion criterion = Restrictions.eq("enabled", enabled);
		criteria.add(criterion);
		return criteria;
	}
}
