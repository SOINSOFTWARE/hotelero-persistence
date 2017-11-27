package com.soinsoftware.hotelero.test.persistence.bll;

import java.util.List;

import javax.persistence.EntityManager;

import com.soinsoftware.hotelero.persistence.bll.AbstractBll;
import com.soinsoftware.hotelero.persistence.bll.RoomBll;
import com.soinsoftware.hotelero.persistence.entity.Room;
import com.soinsoftware.hotelero.persistence.manager.HoteleroManagerFactory;
import com.soinsoftware.hotelero.persistence.manager.IEntityManagerFactory;

import junit.framework.Assert;
import junit.framework.TestCase;

@SuppressWarnings(value = { "rawtypes", "unchecked" })
public class RoomTest extends TestCase {

	private AbstractBll bll;

	protected void setUp() throws Exception {
		super.setUp();
		final IEntityManagerFactory emf = HoteleroManagerFactory.getInstance();
		final EntityManager manager = emf.createEntityManager();
		bll = new RoomBll(manager);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		bll.closeDbConnection();
	}

	public void testSelectAll() {
		final List<Room> entities = bll.selectAll();
		Assert.assertNotNull(entities);
		Assert.assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<Room> entities = bll.selectAll(true);
		Assert.assertNotNull(entities);
		Assert.assertNotSame(entities.size(), 0);
	}

	public void testSelectByCodeNotExists() {
		final Room entity = ((RoomBll) bll).select("100");
		Assert.assertNull(entity);
	}

	public void testSelectByCodeExists() {
		final Room entity = ((RoomBll) bll).select("101");
		Assert.assertNotNull(entity);
	}
}
