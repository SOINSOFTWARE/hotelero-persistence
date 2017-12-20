package com.soinsoftware.hotelero.test.persistence.bll;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.soinsoftware.hotelero.persistence.bll.AbstractBll;
import com.soinsoftware.hotelero.persistence.bll.RoomTypeTariffBll;
import com.soinsoftware.hotelero.persistence.entity.RoomType;
import com.soinsoftware.hotelero.persistence.entity.RoomTypeTariff;
import com.soinsoftware.hotelero.persistence.entity.Tariff;
import com.soinsoftware.hotelero.persistence.manager.HoteleroManagerFactory;

import junit.framework.TestCase;

@SuppressWarnings(value = { "rawtypes", "unchecked" })
public class RoomTypeTariffTest extends TestCase {

	private AbstractBll bll;

	protected void setUp() throws Exception {
		super.setUp();
		HoteleroManagerFactory.getInstance();
		bll = new RoomTypeTariffBll();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		bll.closeDbConnection();
	}

	public void testSelectAll() {
		final List<RoomTypeTariff> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<RoomTypeTariff> entities = bll.selectAll(true);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);

	}

	public void testSelectDateRange() {
		final Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2017);
		calendar.set(Calendar.MONTH, 4);
		calendar.set(Calendar.DATE, 1);
		final Date fromDate = calendar.getTime();
		calendar.set(Calendar.DATE, 31);
		final Date toDate = calendar.getTime();
		final List<RoomTypeTariff> entities = ((RoomTypeTariffBll) bll).selectDateRange(fromDate, toDate);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);

	}

	public void selectDefaultTariff() {
		final boolean defaultTariff = false;
		final List<RoomTypeTariff> entities = ((RoomTypeTariffBll) bll).selectDefaultTariff(defaultTariff);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectRoomType() {
		final int idRoomType = 1;
		final List<RoomTypeTariff> entities = ((RoomTypeTariffBll) bll).selectRoomType(idRoomType);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectTariff() {
		final int idTariff = 1;
		final List<RoomTypeTariff> entities = ((RoomTypeTariffBll) bll).selectTariff(idTariff);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelect() {
		final Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2017);
		calendar.set(Calendar.MONTH, 4);
		calendar.set(Calendar.DATE, 1);
		final Date fromDate = calendar.getTime();
		calendar.set(Calendar.DATE, 31);
		final Date toDate = calendar.getTime();
		final Boolean defaultTariff = false;
		final RoomType roomType = new RoomType();
		roomType.setId(1);
		final Tariff tariff = new Tariff();
		tariff.setId(1);
		final List<RoomTypeTariff> entities = ((RoomTypeTariffBll) bll).select(fromDate, toDate, defaultTariff,
				roomType, tariff);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}
}
