package com.soinsoftware.hotelero.test.persistence.bll;

import java.io.IOException;
import java.util.List;

import com.soinsoftware.hotelero.persistence.bll.AbstractBll;
import com.soinsoftware.hotelero.persistence.bll.HotelBll;
import com.soinsoftware.hotelero.persistence.bll.UserBll;
import com.soinsoftware.hotelero.persistence.entity.Hotel;
import com.soinsoftware.hotelero.persistence.entity.User;
import com.soinsoftware.hotelero.persistence.manager.HoteleroManagerFactory;

import junit.framework.TestCase;

@SuppressWarnings(value = { "rawtypes", "unchecked" })
public class UserTest extends TestCase {

	private AbstractBll bll;

	protected void setUp() throws Exception {
		super.setUp();
		HoteleroManagerFactory.getInstance();
		bll = new UserBll();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		bll.closeDbConnection();
	}

	public void testSelectAll() {
		final List<User> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<User> entities = bll.selectAll(true);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByIdentificationExists() {
		final User entity = ((UserBll) bll).select(987456321);
		assertNotNull(entity);
	}

	public void testSelectByIdentificationNotExists() {
		final User entity = ((UserBll) bll).select(0);
		assertNull(entity);
	}

	public void testSelectByLoginExists() {
		final User entity = ((UserBll) bll).select("admin", "abc123");
		assertNotNull(entity);
	}

	public void testSelectByLoginNotExists() {
		final User entity = ((UserBll) bll).select("admin", "xxxxxx");
		assertNull(entity);
	}

	public void testSelectByHotel() throws IOException {
		final Hotel hotel = selectHotel();
		final List<User> entities = ((UserBll) bll).select(hotel);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	private Hotel selectHotel() throws IOException {
		final HotelBll bll = new HotelBll();
		return ((HotelBll) bll).select("123456789-1");
	}
}
