package com.soinsoftware.hotelero.test.persistence.bll;

import java.util.List;

import com.soinsoftware.hotelero.persistence.bll.AbstractBll;
import com.soinsoftware.hotelero.persistence.bll.InvoiceStatusBll;
import com.soinsoftware.hotelero.persistence.entity.InvoiceStatus;
import com.soinsoftware.hotelero.persistence.manager.HoteleroManagerFactory;

import junit.framework.TestCase;

@SuppressWarnings(value = { "rawtypes", "unchecked" })
public class InvoiceStatusTest extends TestCase {

	private AbstractBll bll;

	protected void setUp() throws Exception {
		super.setUp();
		HoteleroManagerFactory.getInstance();
		bll = new InvoiceStatusBll();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		bll.closeDbConnection();
	}

	public void testSelectAll() {
		final List<InvoiceStatus> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<InvoiceStatus> entities = bll.selectAll(true);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByNameExists() {
		final InvoiceStatus entity = ((InvoiceStatusBll) bll).select("Pagado");
		assertNotNull(entity);
	}

	public void testSelectByNameNotExists() {
		final InvoiceStatus entity = ((InvoiceStatusBll) bll).select("not existing");
		assertNull(entity);
	}
}
