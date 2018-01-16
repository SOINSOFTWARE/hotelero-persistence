package com.soinsoftware.hotelero.persistence.bll;

import java.io.IOException;
import java.util.List;

import com.soinsoftware.hotelero.persistence.dao.ServiceDao;
import com.soinsoftware.hotelero.persistence.entity.Hotel;
import com.soinsoftware.hotelero.persistence.entity.Service;
import com.soinsoftware.hotelero.persistence.entity.ServiceType;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
public class ServiceBll extends AbstractBll<Service, Integer> {

	public ServiceBll() throws IOException {
		super();
		dao = new ServiceDao();
	}

	public Service select(final String name) {
		return ((ServiceDao) dao).select(name);
	}

	public List<Service> selectByServiceType(final ServiceType serviceType) {
		return ((ServiceDao) dao).selectByServiceType(serviceType);
	}

	public List<Service> select(final Hotel hotel) {
		return ((ServiceDao) dao).select(hotel);
	}
}