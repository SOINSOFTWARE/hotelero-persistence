package com.soinsoftware.hotelero.persistence.bll;

import java.io.IOException;
import java.util.List;

import com.soinsoftware.hotelero.persistence.dao.CompanyDao;
import com.soinsoftware.hotelero.persistence.entity.Company;
import com.soinsoftware.hotelero.persistence.entity.Hotel;

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

	public List<Company> select(final Hotel hotel) {
		return ((CompanyDao) dao).select(hotel);
	}
}