package com.soinsoftware.hotelero.persistence.bll;

import java.io.IOException;

import com.soinsoftware.hotelero.persistence.dao.CompanyDao;
import com.soinsoftware.hotelero.persistence.entity.Company;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
public class CompanyBll extends AbstractBll<Company, Integer> {

	public CompanyBll() throws IOException {
		super();
		dao = new CompanyDao();
	}

	public Company select(final String name) {
		return ((CompanyDao) dao).select(name);
	}
}