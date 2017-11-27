package com.soinsoftware.hotelero.test.persistence.bll;

import java.util.List;

import javax.persistence.EntityManager;

import com.soinsoftware.hotelero.persistence.bll.AbstractBll;
import com.soinsoftware.hotelero.persistence.bll.RoomStatusBll;
import com.soinsoftware.hotelero.persistence.entity.RoomStatus;
import com.soinsoftware.hotelero.persistence.manager.HoteleroManagerFactory;
import com.soinsoftware.hotelero.persistence.manager.IEntityManagerFactory;

import junit.framework.Assert;
import junit.framework.TestCase;

@SuppressWarnings(value = { "rawtypes", "unchecked" })
public class RoomStatusTest extends TestCase {

	private AbstractBll bll;

	protected void setUp() throws Exception {
		super.setUp();
		final IEntityManagerFactory emf = HoteleroManagerFactory.getInstance();
		final EntityManager manager = emf.createEntityManager();
		bll = new RoomStatusBll(manager);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		bll.closeDbConnection();
	}

	public void testSelectAll() {
		final List<RoomStatus> entities = bll.selectAll();
		Assert.assertNotNull(entities);
		Assert.assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<RoomStatus> entities = bll.selectAll(true);
		Assert.assertNotNull(entities);
		Assert.assertNotSame(entities.size(), 0);
	}

	public void testSelectByCodeNotExists() {
		final RoomStatus entity = ((RoomStatusBll) bll).select("No Disponible");
		Assert.assertNull(entity);
	}

	public void testSelectByCodeExists() {
		final RoomStatus entity = ((RoomStatusBll) bll).select("Disponible");
		Assert.assertNotNull(entity);
	}
}
