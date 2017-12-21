package com.soinsoftware.hotelero.test.persistence.bll;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.soinsoftware.hotelero.persistence.bll.AbstractBll;
import com.soinsoftware.hotelero.persistence.bll.InvoiceBll;
import com.soinsoftware.hotelero.persistence.entity.Company;
import com.soinsoftware.hotelero.persistence.entity.Invoice;
import com.soinsoftware.hotelero.persistence.entity.InvoiceStatus;
import com.soinsoftware.hotelero.persistence.entity.RoomStatus;
import com.soinsoftware.hotelero.persistence.manager.HoteleroManagerFactory;

import junit.framework.TestCase;

@SuppressWarnings(value = { "rawtypes", "unchecked" })
public class InvoiceTest extends TestCase {

	private AbstractBll bll;

	protected void setUp() throws Exception {
		super.setUp();
		HoteleroManagerFactory.getInstance();
		bll = new InvoiceBll();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		bll.closeDbConnection();
	}

	public void testSelectAll() {
		final List<Invoice> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<Invoice> entities = bll.selectAll(true);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByStatusExists() {
		final RoomStatus roomStatus = new RoomStatus();
		roomStatus.setId(3);
		final List<Invoice> entities = ((InvoiceBll) bll).selectByStatus(roomStatus);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByStatusNonExists() {
		final RoomStatus roomStatus = new RoomStatus();
		roomStatus.setId(2);
		final List<Invoice> entities = ((InvoiceBll) bll).selectByStatus(roomStatus);
		assertNotNull(entities);
		assertSame(entities.size(), 0);
	}

	public void testSelectByStatusAndInitDateExists() {
		final RoomStatus roomStatus = new RoomStatus();
		roomStatus.setId(3);
		final Date initDate = buildDate(2017, 12, 20);
		final List<Invoice> entities = ((InvoiceBll) bll).selectByStatus(roomStatus, initDate);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByStatusAndInitDateNonExists() {
		final RoomStatus roomStatus = new RoomStatus();
		roomStatus.setId(3);
		final Date initDate = buildDate(2017, 12, 21);
		final List<Invoice> entities = ((InvoiceBll) bll).selectByStatus(roomStatus, initDate);
		assertNotNull(entities);
		assertSame(entities.size(), 0);
	}

	public void testSelectByStatusAndYearAndMonthExists() {
		final RoomStatus roomStatus = new RoomStatus();
		roomStatus.setId(3);
		final List<Invoice> entities = ((InvoiceBll) bll).selectByStatus(roomStatus, 2017, 12);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByStatusAndYearAndMonthNonExists() {
		final RoomStatus roomStatus = new RoomStatus();
		roomStatus.setId(3);
		final List<Invoice> entities = ((InvoiceBll) bll).selectByStatus(roomStatus, 2017, 11);
		assertNotNull(entities);
		assertSame(entities.size(), 0);
	}

	public void testSelectByStatusAndYearAndMonthAndInvoiceStatusExists() {
		final RoomStatus roomStatus = new RoomStatus();
		roomStatus.setId(3);
		final InvoiceStatus invoiceStatus = new InvoiceStatus();
		invoiceStatus.setId(1);
		final List<Invoice> entities = ((InvoiceBll) bll).selectByStatus(roomStatus, 2017, 12, invoiceStatus);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByStatusAndYearAndMonthAndInvoiceStatusNonExists() {
		final RoomStatus roomStatus = new RoomStatus();
		roomStatus.setId(3);
		final InvoiceStatus invoiceStatus = new InvoiceStatus();
		invoiceStatus.setId(2);
		final List<Invoice> entities = ((InvoiceBll) bll).selectByStatus(roomStatus, 2017, 12, invoiceStatus);
		assertNotNull(entities);
		assertSame(entities.size(), 0);
	}

	public void testSelectByStatusAndYearAndMonthAndInvoiceStatusAndCompanyExists() {
		final RoomStatus roomStatus = new RoomStatus();
		roomStatus.setId(3);
		final InvoiceStatus invoiceStatus = new InvoiceStatus();
		invoiceStatus.setId(1);
		final Company company = new Company();
		company.setId(1);
		final List<Invoice> entities = ((InvoiceBll) bll).selectByStatus(roomStatus, 2017, 12, invoiceStatus, company);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByStatusAndYearAndMonthAndInvoiceStatusAndCompanyNonExists() {
		final RoomStatus roomStatus = new RoomStatus();
		roomStatus.setId(3);
		final InvoiceStatus invoiceStatus = new InvoiceStatus();
		invoiceStatus.setId(1);
		final Company company = new Company();
		company.setId(2);
		final List<Invoice> entities = ((InvoiceBll) bll).selectByStatus(roomStatus, 2017, 12, invoiceStatus, company);
		assertNotNull(entities);
		assertSame(entities.size(), 0);
	}
	
	public void testSelectByNonStatusAndInitDateAndFinDateExists() {
		final RoomStatus roomStatus = new RoomStatus();
		roomStatus.setId(1);
		final Date initDate = buildDate(2017, 12, 20);
		final Date finDate = buildDate(2017, 12, 22);
		final List<Invoice> entities = ((InvoiceBll) bll).selectByNonStatus(roomStatus, initDate, finDate);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}
	
	public void testSelectByNonStatusAndInitDateAndFinDateOutsideRangeExists() {
		final RoomStatus roomStatus = new RoomStatus();
		roomStatus.setId(1);
		final Date initDate = buildDate(2017, 12, 23);
		final Date finDate = buildDate(2017, 12, 26);
		final List<Invoice> entities = ((InvoiceBll) bll).selectByNonStatus(roomStatus, initDate, finDate);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}
	
	public void testSelectByNonStatusAndInitDateAndFinDateNonExists() {
		final RoomStatus roomStatus = new RoomStatus();
		roomStatus.setId(1);
		final Date initDate = buildDate(2017, 12, 24);
		final Date finDate = buildDate(2017, 12, 26);
		final List<Invoice> entities = ((InvoiceBll) bll).selectByNonStatus(roomStatus, initDate, finDate);
		assertNotNull(entities);
		assertSame(entities.size(), 0);
	}

	private Date buildDate(final int year, final int month, final int date) {
		final Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, date, 0, 0);
		return cal.getTime();
	}
}
