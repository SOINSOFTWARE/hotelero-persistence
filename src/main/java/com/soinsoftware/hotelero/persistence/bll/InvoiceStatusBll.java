package com.soinsoftware.hotelero.persistence.bll;

import java.io.IOException;

import com.soinsoftware.hotelero.persistence.dao.InvoiceStatusDao;
import com.soinsoftware.hotelero.persistence.entity.InvoiceStatus;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
public class InvoiceStatusBll extends AbstractBll<InvoiceStatus, Integer> {

	public InvoiceStatusBll() throws IOException {
		super();
		dao = new InvoiceStatusDao();
	}

	public InvoiceStatus select(final String name) {
		return ((InvoiceStatusDao) dao).select(name);
	}
}
