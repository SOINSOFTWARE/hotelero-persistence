package com.soinsoftware.hotelero.persistence.bll;

import java.io.IOException;
import java.util.List;

import com.soinsoftware.hotelero.persistence.dao.RoleDao;
import com.soinsoftware.hotelero.persistence.entity.Hotel;
import com.soinsoftware.hotelero.persistence.entity.Role;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
public class RoleBll extends AbstractBll<Role, Integer> {

	public RoleBll() throws IOException {
		super();
		dao = new RoleDao();
	}

	public Role select(final String name) {
		return ((RoleDao) dao).select(name);
	}

	public List<Role> select(final Hotel hotel) {
		return ((RoleDao) dao).select(hotel);
	}
}