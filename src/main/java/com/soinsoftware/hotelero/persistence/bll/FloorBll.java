package com.soinsoftware.hotelero.persistence.bll;

import java.io.IOException;

import javax.persistence.EntityManager;

import com.soinsoftware.hotelero.persistence.dao.FloorDao;
import com.soinsoftware.hotelero.persistence.entity.Floor;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
public class FloorBll extends AbstractBll<Floor, Integer> {

	public FloorBll(final EntityManager manager) throws IOException {
		super(manager);
		dao = new FloorDao(manager);
	}

	public Floor select(final String code) {
		return ((FloorDao) dao).select(code);
	}
}
