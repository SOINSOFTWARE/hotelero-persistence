package com.soinsoftware.hotelero.persistence.bll;

import java.io.IOException;
import java.util.List;

import com.soinsoftware.hotelero.persistence.dao.TariffDao;
import com.soinsoftware.hotelero.persistence.entity.Hotel;
import com.soinsoftware.hotelero.persistence.entity.Tariff;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
public class TariffBll extends AbstractBll<Tariff, Integer> {

	public TariffBll() throws IOException {
		super();
		dao = new TariffDao();
	}

	public Tariff select(final String code) {
		return ((TariffDao) dao).select(code);
	}

	public List<Tariff> select(final Hotel hotel) {
		return ((TariffDao) dao).select(hotel);
	}
}