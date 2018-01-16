package com.soinsoftware.hotelero.test.persistence.bll;

import java.util.List;

import com.soinsoftware.hotelero.persistence.bll.AbstractBll;
import com.soinsoftware.hotelero.persistence.bll.HotelBll;
import com.soinsoftware.hotelero.persistence.entity.Hotel;
import com.soinsoftware.hotelero.persistence.manager.HoteleroManagerFactory;

import junit.framework.TestCase;

@SuppressWarnings(value = { "rawtypes", "unchecked" })
public class HotelTest extends TestCase {

	private AbstractBll bll;

	protected void setUp() throws Exception {
		super.setUp();
		HoteleroManagerFactory.getInstance();
		bll = new HotelBll();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		bll.closeDbConnection();
	}

	public void testSelectAll() {
		final List<Hotel> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<Hotel> entities = bll.selectAll(true);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByNitNotExists() {
		final Hotel entity = ((HotelBll) bll).select("123456789-0");
		assertNull(entity);
	}

	public void testSelectByNitExists() {
		final Hotel entity = ((HotelBll) bll).select("123456789-1");
		assertNotNull(entity);
	}
}
