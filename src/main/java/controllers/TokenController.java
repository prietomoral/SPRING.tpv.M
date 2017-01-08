package controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.users.TokenDao;
import daos.users.UserDao;
import entities.users.Token;
import entities.users.User;

@Controller
@Transactional
public class TokenController {

	private TokenDao tokenDao;

	private UserDao userDao;

	@Autowired
	public void setTokenDao(TokenDao tokenDao) {
		this.tokenDao = tokenDao;
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public String login(long mobile) {
		User user = userDao.findByMobile(mobile);
		assert user != null;
		Token token = new Token(user);
		tokenDao.save(token);
		return token.getValue();
	}
}
