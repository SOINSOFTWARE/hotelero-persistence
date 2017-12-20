package com.soinsoftware.hotelero.persistence.bll;

import java.io.IOException;

import com.soinsoftware.hotelero.persistence.dao.UserDao;
import com.soinsoftware.hotelero.persistence.entity.User;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
public class UserBll extends AbstractBll<User, Integer> {

	public UserBll() throws IOException {
		super();
		dao = new UserDao();
	}

	public User select(final long identification) {
		return ((UserDao) dao).select(identification);
	}

	public User select(final String login, final String password) {
		return ((UserDao) dao).select(login, password);
	}
}
