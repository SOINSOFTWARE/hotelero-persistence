package com.soinsoftware.hotelero.persistence.bll;

import java.io.IOException;
import java.util.List;

import com.soinsoftware.hotelero.persistence.dao.InvoiceItemDao;
import com.soinsoftware.hotelero.persistence.entity.Invoice;
import com.soinsoftware.hotelero.persistence.entity.InvoiceItem;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
public class InvoiceItemBll extends AbstractBll<InvoiceItem, Integer> {

	public InvoiceItemBll() throws IOException {
		super();
		dao = new InvoiceItemDao();
	}

	public List<InvoiceItem> select(final Invoice invoice) {
		return ((InvoiceItemDao) dao).selectByInvoice(invoice);
	}
}
