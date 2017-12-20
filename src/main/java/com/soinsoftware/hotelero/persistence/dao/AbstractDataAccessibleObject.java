package com.soinsoftware.hotelero.persistence.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.soinsoftware.hotelero.persistence.manager.AbstractManagerFactory;
import com.soinsoftware.hotelero.persistence.manager.HoteleroManagerFactory;

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
	 * @throws IOException
	 */
	public AbstractDataAccessibleObject() throws IOException {
		super();
		final AbstractManagerFactory factory = HoteleroManagerFactory.getInstance();
		this.manager = factory.createEntityManager();
	}

	@Override
	public void persist(T record) {
		try {
			if (!manager.getTransaction().isActive()) {
				manager.getTransaction().begin();
			}
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
	
	protected Criterion[] buildPredicates(final List<Criterion> predicates) {
		final Criterion[] predicateArray = new Criterion[predicates.size()];
		for (int i = 0; i < predicates.size(); i++) {
			predicateArray[i] = predicates.get(i);
		}
		return predicateArray;
	}
}
