package com.soinsoftware.hotelero.test.persistence.bll;

import java.io.IOException;
import java.util.List;

import com.soinsoftware.hotelero.persistence.bll.AbstractBll;
import com.soinsoftware.hotelero.persistence.bll.RoleAccessBll;
import com.soinsoftware.hotelero.persistence.bll.RoleBll;
import com.soinsoftware.hotelero.persistence.entity.Role;
import com.soinsoftware.hotelero.persistence.entity.RoleAccess;
import com.soinsoftware.hotelero.persistence.manager.HoteleroManagerFactory;

import junit.framework.TestCase;

@SuppressWarnings(value = { "rawtypes", "unchecked" })
public class RoleAccessTest extends TestCase {

	private AbstractBll bll;

	protected void setUp() throws Exception {
		super.setUp();
		HoteleroManagerFactory.getInstance();
		bll = new RoleAccessBll();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		bll.closeDbConnection();
	}

	public void testSelectAll() {
		final List<RoleAccess> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<RoleAccess> entities = bll.selectAll(true);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectAccessByRole() throws IOException {
		final Role role = selectRole();
		final List<RoleAccess> entities = ((RoleAccessBll) bll).select(role);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	private Role selectRole() throws IOException {
		final RoleBll bll = new RoleBll();
		return ((RoleBll) bll).select("Soin");
	}
}
