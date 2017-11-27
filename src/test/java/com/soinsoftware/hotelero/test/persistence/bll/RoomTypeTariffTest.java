package com.soinsoftware.hotelero.test.persistence.bll;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.soinsoftware.hotelero.persistence.bll.AbstractBll;
import com.soinsoftware.hotelero.persistence.bll.RoomTypeTariffBll;
import com.soinsoftware.hotelero.persistence.entity.RoomType;
import com.soinsoftware.hotelero.persistence.entity.RoomTypeTariff;
import com.soinsoftware.hotelero.persistence.entity.Tariff;
import com.soinsoftware.hotelero.persistence.manager.HoteleroManagerFactory;
import com.soinsoftware.hotelero.persistence.manager.IEntityManagerFactory;

import junit.framework.Assert;
import junit.framework.TestCase;

@SuppressWarnings(value = { "rawtypes", "unchecked" })
public class RoomTypeTariffTest extends TestCase {

	private AbstractBll bll;

	protected void setUp() throws Exception {
		super.setUp();
		final IEntityManagerFactory emf = HoteleroManagerFactory.getInstance();
		final EntityManager manager = emf.createEntityManager();
		bll = new RoomTypeTariffBll(manager);
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		bll.closeDbConnection();
	}

	public void testSelectAll() {
		final List<RoomTypeTariff> entities = bll.selectAll();
		Assert.assertNotNull(entities);
		Assert.assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<RoomTypeTariff> entities = bll.selectAll(true);
		Assert.assertNotNull(entities);
		Assert.assertNotSame(entities.size(), 0);

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
		Assert.assertNotNull(entities);
		Assert.assertNotSame(entities.size(), 0);

	}

	public void selectDefaultTariff() {
		final boolean defaultTariff = true;
		final List<RoomTypeTariff> entities = ((RoomTypeTariffBll) bll).selectDefaultTariff(defaultTariff);
		Assert.assertNotNull(entities);
		Assert.assertNotSame(entities.size(), 0);
	}

	public void testSelectRoomType() {
		final int idRoomType = 1;
		final List<RoomTypeTariff> entities = ((RoomTypeTariffBll) bll).selectRoomType(idRoomType);
		Assert.assertNotNull(entities);
		Assert.assertNotSame(entities.size(), 0);
	}

	public void testSelectTariff() {
		final int idTariff = 1;
		final List<RoomTypeTariff> entities = ((RoomTypeTariffBll) bll).selectTariff(idTariff);
		Assert.assertNotNull(entities);
		Assert.assertNotSame(entities.size(), 0);
	}

	public void testSelect() {
		final Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2017);
		calendar.set(Calendar.MONTH, 4);
		calendar.set(Calendar.DATE, 1);
		final Date fromDate = calendar.getTime();
		calendar.set(Calendar.DATE, 31);
		final Date toDate = calendar.getTime();
		final Boolean defaultTariff = true;
		final RoomType roomType = new RoomType();
		roomType.setId(1);
		final Tariff tariff = new Tariff();
		tariff.setId(1);
		final List<RoomTypeTariff> entities = ((RoomTypeTariffBll) bll).select(fromDate, toDate, defaultTariff,
				roomType, tariff);
		Assert.assertNotNull(entities);
		Assert.assertNotSame(entities.size(), 0);
	}
	
	private void createNewOne() {
		final Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2017);
		calendar.set(Calendar.MONTH, 4);
		calendar.set(Calendar.DATE, 2);
		final RoomTypeTariff newOne = new RoomTypeTariff();
		newOne.setFromDate(calendar.getTime());
		calendar.set(Calendar.DATE, 30);
		newOne.setToDate(calendar.getTime());
		newOne.setDefaultTariff(false);
		final RoomType roomType = new RoomType();
		roomType.setId(1);
		newOne.setRoomType(roomType);
	}
}
