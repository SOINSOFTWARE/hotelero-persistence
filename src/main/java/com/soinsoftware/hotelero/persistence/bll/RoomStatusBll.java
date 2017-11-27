package com.soinsoftware.hotelero.persistence.bll;

import java.io.IOException;

import javax.persistence.EntityManager;

import com.soinsoftware.hotelero.persistence.dao.RoomStatusDao;
import com.soinsoftware.hotelero.persistence.entity.RoomStatus;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
public class RoomStatusBll extends AbstractBll<RoomStatus, Integer> {

	public RoomStatusBll(final EntityManager manager) throws IOException {
		super(manager);
		dao = new RoomStatusDao(manager);
	}

	public RoomStatus select(final String name) {
		return ((RoomStatusDao) dao).select(name);
	}
}
