package com.soinsoftware.hotelero.persistence.bll;

import java.io.IOException;
import java.util.List;

import com.soinsoftware.hotelero.persistence.dao.ServiceTypeDao;
import com.soinsoftware.hotelero.persistence.entity.Hotel;
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

	public List<ServiceType> select(final Hotel hotel) {
		return ((ServiceTypeDao) dao).select(hotel);
	}
}