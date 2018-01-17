package com.soinsoftware.hotelero.persistence.bll;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import com.soinsoftware.hotelero.persistence.dao.RoleAccessDao;
import com.soinsoftware.hotelero.persistence.entity.Role;
import com.soinsoftware.hotelero.persistence.entity.RoleAccess;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
public class RoleAccessBll extends AbstractBll<RoleAccess, BigInteger> {

	public RoleAccessBll() throws IOException {
		super();
		dao = new RoleAccessDao();
	}

	public RoleAccess select(final String name) {
		return ((RoleAccessDao) dao).select(name);
	}

	public List<RoleAccess> select(final Role role) {
		return ((RoleAccessDao) dao).select(role);
	}
}