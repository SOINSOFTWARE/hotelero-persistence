package com.soinsoftware.hotelero.persistence.bll;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.soinsoftware.hotelero.persistence.dao.RoomTypeTariffDao;
import com.soinsoftware.hotelero.persistence.entity.RoomType;
import com.soinsoftware.hotelero.persistence.entity.RoomTypeTariff;
import com.soinsoftware.hotelero.persistence.entity.Tariff;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
public class RoomTypeTariffBll extends AbstractBll<RoomType, Integer> {

	public RoomTypeTariffBll(final EntityManager manager) throws IOException {
		super(manager);
		dao = new RoomTypeTariffDao(manager);
	}

	public List<RoomTypeTariff> selectDateRange(final Date fromDate, final Date toDate) {
		return ((RoomTypeTariffDao) dao).selectDateRange(fromDate, toDate);
	}

	public List<RoomTypeTariff> selectDefaultTariff(final boolean defaultTariff) {
		return ((RoomTypeTariffDao) dao).selectDefaultTariff(defaultTariff);
	}

	public List<RoomTypeTariff> selectRoomType(final int idRoomType) {
		return ((RoomTypeTariffDao) dao).selectRoomType(idRoomType);
	}

	public List<RoomTypeTariff> selectTariff(final int idTariff) {
		return ((RoomTypeTariffDao) dao).selectTariff(idTariff);
	}

	public List<RoomTypeTariff> select(final Date fromDate, final Date toDate, final Boolean defaultTariff,
			final RoomType roomType, final Tariff tariff) {
		return ((RoomTypeTariffDao) dao).select(fromDate, toDate, defaultTariff, roomType, tariff);
	}
}
