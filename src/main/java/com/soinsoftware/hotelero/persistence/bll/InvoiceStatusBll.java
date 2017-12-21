package com.soinsoftware.hotelero.persistence.bll;

import java.io.IOException;

import com.soinsoftware.hotelero.persistence.dao.InvoiceStatusDao;
import com.soinsoftware.hotelero.persistence.entity.InvoiceStatus;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
public class InvoiceStatusBll extends AbstractBll<InvoiceStatus, Integer> {

	private static final String INVOICE_STATUS_NO_PAID = "Sin pago";

	private static final String INVOICE_STATUS_PAID = "Pagado";

	private static final String INVOICE_STATUS_BILL_TO_COMPANY = "Facturado a empresa";

	private static final String INVOICE_STATUS_DELETED = "Cancelado";

	public InvoiceStatusBll() throws IOException {
		super();
		dao = new InvoiceStatusDao();
	}

	public InvoiceStatus select(final String name) {
		return ((InvoiceStatusDao) dao).select(name);
	}

	public InvoiceStatus selectInvoiceStatusNoPaid() {
		return select(INVOICE_STATUS_NO_PAID);
	}

	public InvoiceStatus selectInvoiceStatusPaid() {
		return select(INVOICE_STATUS_PAID);
	}

	public InvoiceStatus selectInvoiceStatusBillToCompany() {
		return select(INVOICE_STATUS_BILL_TO_COMPANY);
	}

	public InvoiceStatus selectInvoiceStatusDeleted() {
		return select(INVOICE_STATUS_DELETED);
	}
}
