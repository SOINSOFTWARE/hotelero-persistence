package com.soinsoftware.hotelero.persistence.bll;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;

import com.soinsoftware.hotelero.persistence.dao.DataAccessibleObject;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
@SuppressWarnings(value = { "unchecked" })
public abstract class AbstractBll<T, P> {

	@SuppressWarnings("rawtypes")
	protected DataAccessibleObject dao;

	public AbstractBll(final EntityManager manager) throws IOException {
		super();
	}

	public List<T> selectAll() {
		return dao.selectAll();
	}

	public List<T> selectAll(boolean enabled) {
		return dao.selectAll(enabled);
	}

	public T selectById(P pk) {
		return (T) dao.selectById(pk);
	}

	public void save(final T entity) {
		dao.persist(entity);
	}

	public void closeDbConnection() {
		dao.close();
	}
}
