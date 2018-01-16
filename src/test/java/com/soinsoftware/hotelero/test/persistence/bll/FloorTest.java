package com.soinsoftware.hotelero.test.persistence.bll;

import java.io.IOException;
import java.util.List;

import com.soinsoftware.hotelero.persistence.bll.AbstractBll;
import com.soinsoftware.hotelero.persistence.bll.FloorBll;
import com.soinsoftware.hotelero.persistence.bll.HotelBll;
import com.soinsoftware.hotelero.persistence.entity.Floor;
import com.soinsoftware.hotelero.persistence.entity.Hotel;
import com.soinsoftware.hotelero.persistence.manager.HoteleroManagerFactory;

import junit.framework.TestCase;

@SuppressWarnings(value = { "rawtypes", "unchecked" })
public class FloorTest extends TestCase {

	private AbstractBll bll;

	protected void setUp() throws Exception {
		super.setUp();
		HoteleroManagerFactory.getInstance();
		bll = new FloorBll();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		bll.closeDbConnection();
	}

	public void testSelectAll() {
		final List<Floor> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<Floor> entities = bll.selectAll(true);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByCodeNotExists() {
		final Floor entity = ((FloorBll) bll).select("0");
		assertNull(entity);
	}

	public void testSelectByCodeExists() {
		final Floor entity = ((FloorBll) bll).select("1");
		assertNotNull(entity);
	}

	public void testSelectByHotel() throws IOException {
		final Hotel hotel = selectHotel();
		final List<Floor> entities = ((FloorBll) bll).select(hotel);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	private Hotel selectHotel() throws IOException {
		final HotelBll bll = new HotelBll();
		return ((HotelBll) bll).select("123456789-1");
	}
}
