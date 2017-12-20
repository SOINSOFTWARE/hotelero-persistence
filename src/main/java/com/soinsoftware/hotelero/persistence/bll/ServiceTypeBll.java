package com.soinsoftware.hotelero.persistence.bll;

import java.io.IOException;

import com.soinsoftware.hotelero.persistence.dao.ServiceTypeDao;
import com.soinsoftware.hotelero.persistence.entity.ServiceType;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
public class ServiceTypeBll extends AbstractBll<ServiceType, Integer> {

	public ServiceTypeBll() throws IOException {
		super();
		dao = new ServiceTypeDao();
	}

	public ServiceType select(final String name) {
		return ((ServiceTypeDao) dao).select(name);
	}
}
