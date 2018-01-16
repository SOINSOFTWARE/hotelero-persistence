package com.soinsoftware.hotelero.persistence.bll;

import java.io.IOException;

import com.soinsoftware.hotelero.persistence.dao.HotelDao;
import com.soinsoftware.hotelero.persistence.entity.Hotel;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
public class HotelBll extends AbstractBll<Hotel, Integer> {

	public HotelBll() throws IOException {
		super();
		dao = new HotelDao();
	}

	public Hotel select(final String name) {
		return ((HotelDao) dao).select(name);
	}
}