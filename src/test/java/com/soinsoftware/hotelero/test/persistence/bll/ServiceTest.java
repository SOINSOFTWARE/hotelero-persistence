package com.soinsoftware.hotelero.test.persistence.bll;

import java.io.IOException;
import java.util.List;

import com.soinsoftware.hotelero.persistence.bll.AbstractBll;
import com.soinsoftware.hotelero.persistence.bll.HotelBll;
import com.soinsoftware.hotelero.persistence.bll.ServiceBll;
import com.soinsoftware.hotelero.persistence.entity.Hotel;
import com.soinsoftware.hotelero.persistence.entity.Service;
import com.soinsoftware.hotelero.persistence.manager.HoteleroManagerFactory;

import junit.framework.TestCase;

@SuppressWarnings(value = { "rawtypes", "unchecked" })
public class ServiceTest extends TestCase {

	private AbstractBll bll;

	protected void setUp() throws Exception {
		super.setUp();
		HoteleroManagerFactory.getInstance();
		bll = new ServiceBll();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		bll.closeDbConnection();
	}

	public void testSelectAll() {
		final List<Service> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<Service> entities = bll.selectAll(true);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByNameExists() {
		final Service entity = ((ServiceBll) bll).select("TEST");
		assertNotNull(entity);
	}

	public void testSelectByNameNotExists() {
		final Service entity = ((ServiceBll) bll).select("not existing");
		assertNull(entity);
	}

	public void testSelectByHotel() throws IOException {
		final Hotel hotel = selectHotel();
		final List<Service> entities = ((ServiceBll) bll).select(hotel);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	private Hotel selectHotel() throws IOException {
		final HotelBll bll = new HotelBll();
		return ((HotelBll) bll).select("123456789-1");
	}
}
