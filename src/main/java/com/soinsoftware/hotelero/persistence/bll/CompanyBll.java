package com.soinsoftware.hotelero.persistence.bll;

import java.io.IOException;

import javax.persistence.EntityManager;

import com.soinsoftware.hotelero.persistence.dao.CompanyDao;
import com.soinsoftware.hotelero.persistence.entity.Company;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
public class CompanyBll extends AbstractBll<Company, Integer> {

	public CompanyBll(final EntityManager manager) throws IOException {
		super(manager);
		dao = new CompanyDao(manager);
	}

	public Company select(final String name) {
		return ((CompanyDao) dao).select(name);
	}
}