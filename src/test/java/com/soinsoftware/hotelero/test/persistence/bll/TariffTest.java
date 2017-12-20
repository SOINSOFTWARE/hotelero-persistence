package com.soinsoftware.hotelero.test.persistence.bll;

import java.util.List;

import com.soinsoftware.hotelero.persistence.bll.AbstractBll;
import com.soinsoftware.hotelero.persistence.bll.TariffBll;
import com.soinsoftware.hotelero.persistence.entity.Tariff;
import com.soinsoftware.hotelero.persistence.manager.HoteleroManagerFactory;

import junit.framework.TestCase;

@SuppressWarnings(value = { "rawtypes", "unchecked" })
public class TariffTest extends TestCase {

	private AbstractBll bll;

	protected void setUp() throws Exception {
		super.setUp();
		HoteleroManagerFactory.getInstance();
		bll = new TariffBll();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		bll.closeDbConnection();
	}

	public void testSelectAll() {
		final List<Tariff> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<Tariff> entities = bll.selectAll(true);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByNameExists() {
		final Tariff entity = ((TariffBll) bll).select("TEST");
		assertNotNull(entity);
	}

	public void testSelectByNameNotExists() {
		final Tariff entity = ((TariffBll) bll).select("not existing");
		assertNull(entity);
	}
}
