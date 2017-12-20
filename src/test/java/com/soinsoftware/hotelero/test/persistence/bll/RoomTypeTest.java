package com.soinsoftware.hotelero.test.persistence.bll;

import java.util.List;

import com.soinsoftware.hotelero.persistence.bll.AbstractBll;
import com.soinsoftware.hotelero.persistence.bll.RoomTypeBll;
import com.soinsoftware.hotelero.persistence.entity.RoomType;
import com.soinsoftware.hotelero.persistence.manager.HoteleroManagerFactory;

import junit.framework.TestCase;

@SuppressWarnings(value = { "rawtypes", "unchecked" })
public class RoomTypeTest extends TestCase {

	private AbstractBll bll;

	protected void setUp() throws Exception {
		super.setUp();
		HoteleroManagerFactory.getInstance();
		bll = new RoomTypeBll();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		bll.closeDbConnection();
	}

	public void testSelectAll() {
		final List<RoomType> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<RoomType> entities = bll.selectAll(true);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByCodeNotExists() {
		final RoomType entity = ((RoomTypeBll) bll).select("0");
		assertNull(entity);
	}

	public void testSelectByCodeExists() {
		final RoomType entity = ((RoomTypeBll) bll).select("1");
		assertNotNull(entity);
	}
}
