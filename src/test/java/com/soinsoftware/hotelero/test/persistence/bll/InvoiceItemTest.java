package com.soinsoftware.hotelero.test.persistence.bll;

import java.util.List;

import com.soinsoftware.hotelero.persistence.bll.AbstractBll;
import com.soinsoftware.hotelero.persistence.bll.InvoiceItemBll;
import com.soinsoftware.hotelero.persistence.entity.Invoice;
import com.soinsoftware.hotelero.persistence.entity.InvoiceItem;
import com.soinsoftware.hotelero.persistence.manager.HoteleroManagerFactory;

import junit.framework.TestCase;

@SuppressWarnings(value = { "rawtypes", "unchecked" })
public class InvoiceItemTest extends TestCase {

	private AbstractBll bll;

	protected void setUp() throws Exception {
		super.setUp();
		HoteleroManagerFactory.getInstance();
		bll = new InvoiceItemBll();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		bll.closeDbConnection();
	}

	public void testSelectAll() {
		final List<InvoiceItem> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<InvoiceItem> entities = bll.selectAll(true);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByInvoiceExists() {
		final Invoice invoice = new Invoice();
		invoice.setId(1);
		final List<InvoiceItem> entities = ((InvoiceItemBll) bll).select(invoice);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByInvoiceNotExists() {
		final Invoice invoice = new Invoice();
		invoice.setId(0);
		final List<InvoiceItem> entities = ((InvoiceItemBll) bll).select(invoice);
		assertNotNull(entities);
		assertSame(entities.size(), 0);
	}

}
