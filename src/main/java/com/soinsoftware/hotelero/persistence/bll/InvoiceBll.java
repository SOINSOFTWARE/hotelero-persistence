package com.soinsoftware.hotelero.persistence.bll;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.soinsoftware.hotelero.persistence.dao.InvoiceDao;
import com.soinsoftware.hotelero.persistence.entity.Company;
import com.soinsoftware.hotelero.persistence.entity.Invoice;
import com.soinsoftware.hotelero.persistence.entity.InvoiceStatus;
import com.soinsoftware.hotelero.persistence.entity.RoomStatus;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
public class InvoiceBll extends AbstractBll<Invoice, Integer> {

	public InvoiceBll() throws IOException {
		super();
		dao = new InvoiceDao();
	}
	
	public List<Invoice> selectByNonStatus(final RoomStatus roomStatus, final Date initialDate, final Date finalDate) {
		return ((InvoiceDao) dao).selectByNonStatus(roomStatus, initialDate, finalDate);
	}

	public List<Invoice> selectByStatus(final RoomStatus roomStatus) {
		return ((InvoiceDao) dao).selectByStatus(roomStatus);
	}
	
	public List<Invoice> selectByStatus(final RoomStatus roomStatus, final Date initialDate) {
		return ((InvoiceDao) dao).selectByStatus(roomStatus, initialDate);
	}

	public List<Invoice> selectByStatus(final RoomStatus roomStatus, final int year, final int month) {
		return ((InvoiceDao) dao).selectByStatus(roomStatus, year, month);
	}

	public List<Invoice> selectByStatus(final RoomStatus roomStatus, final int year, final int month,
			final InvoiceStatus invoiceStatus) {
		return ((InvoiceDao) dao).selectByStatus(roomStatus, year, month, invoiceStatus);
	}

	public List<Invoice> selectByStatus(final RoomStatus roomStatus, final int year, final int month,
			final InvoiceStatus invoiceStatus, final Company company) {
		return ((InvoiceDao) dao).selectByStatus(roomStatus, year, month, invoiceStatus, company);
	}
}