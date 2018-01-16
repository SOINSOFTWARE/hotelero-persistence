package com.soinsoftware.hotelero.persistence.bll;

import java.io.IOException;
import java.util.List;

import com.soinsoftware.hotelero.persistence.dao.FloorDao;
import com.soinsoftware.hotelero.persistence.entity.Floor;
import com.soinsoftware.hotelero.persistence.entity.Hotel;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
public class FloorBll extends AbstractBll<Floor, Integer> {

	public FloorBll() throws IOException {
		super();
		dao = new FloorDao();
	}

	public Floor select(final String code) {
		return ((FloorDao) dao).select(code);
	}

	public List<Floor> select(final Hotel hotel) {
		return ((FloorDao) dao).select(hotel);
	}
}