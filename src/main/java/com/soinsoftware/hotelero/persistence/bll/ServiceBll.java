package com.soinsoftware.hotelero.persistence.bll;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;

import com.soinsoftware.hotelero.persistence.dao.ServiceDao;
import com.soinsoftware.hotelero.persistence.entity.Service;
import com.soinsoftware.hotelero.persistence.entity.ServiceType;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
public class ServiceBll extends AbstractBll<Service, Integer> {

	public ServiceBll(final EntityManager manager) throws IOException {
		super(manager);
		dao = new ServiceDao(manager);
	}

	public Service select(final String name) {
		return ((ServiceDao) dao).select(name);
	}

	public List<Service> selectByServiceType(final ServiceType serviceType) {
		return ((ServiceDao) dao).selectByServiceType(serviceType);
	}
}
