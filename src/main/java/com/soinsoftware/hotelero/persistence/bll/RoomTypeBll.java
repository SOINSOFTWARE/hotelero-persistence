package com.soinsoftware.hotelero.persistence.bll;

import java.io.IOException;
import java.util.List;

import com.soinsoftware.hotelero.persistence.dao.RoomTypeDao;
import com.soinsoftware.hotelero.persistence.entity.Hotel;
import com.soinsoftware.hotelero.persistence.entity.RoomType;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
public class RoomTypeBll extends AbstractBll<RoomType, Integer> {

	public RoomTypeBll() throws IOException {
		super();
		dao = new RoomTypeDao();
	}

	public RoomType select(final String code) {
		return ((RoomTypeDao) dao).select(code);
	}

	public List<RoomType> select(final Hotel hotel) {
		return ((RoomTypeDao) dao).select(hotel);
	}
}