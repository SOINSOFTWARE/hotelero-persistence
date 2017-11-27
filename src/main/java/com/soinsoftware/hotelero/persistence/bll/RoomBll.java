package com.soinsoftware.hotelero.persistence.bll;

import java.io.IOException;

import javax.persistence.EntityManager;

import com.soinsoftware.hotelero.persistence.dao.RoomDao;
import com.soinsoftware.hotelero.persistence.entity.Room;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
public class RoomBll extends AbstractBll<Room, Integer> {

	public RoomBll(final EntityManager manager) throws IOException {
		super(manager);
		dao = new RoomDao(manager);
	}

	public Room select(final String code) {
		return ((RoomDao) dao).select(code);
	}
}
