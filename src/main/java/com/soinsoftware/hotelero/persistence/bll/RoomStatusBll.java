package com.soinsoftware.hotelero.persistence.bll;

import java.io.IOException;

import com.soinsoftware.hotelero.persistence.dao.RoomStatusDao;
import com.soinsoftware.hotelero.persistence.entity.RoomStatus;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
public class RoomStatusBll extends AbstractBll<RoomStatus, Integer> {

	private static final String ROOM_STATUS_ENABLED = "Disponible";

	private static final String ROOM_STATUS_DISABLED = "Ocupado";

	private static final String ROOM_STATUS_BOOKED = "Reservado";

	public RoomStatusBll() throws IOException {
		super();
		dao = new RoomStatusDao();
	}

	public RoomStatus select(final String name) {
		return ((RoomStatusDao) dao).select(name);
	}

	public RoomStatus selectRoomStatusEnabled() {
		return select(ROOM_STATUS_ENABLED);
	}

	public RoomStatus selectRoomStatusDisabled() {
		return select(ROOM_STATUS_DISABLED);
	}

	public RoomStatus selectRoomStatusBooked() {
		return select(ROOM_STATUS_BOOKED);
	}
}
