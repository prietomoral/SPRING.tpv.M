package daos;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import daos.AuthorizationDao;
import daos.TokenDao;
import daos.UserDao;
import entities.users.Authorization;
import entities.users.Role;
import entities.users.Token;
import entities.users.User;
import services.DataService;

@Service
public class DaosService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private TokenDao tokenDao;

	@Autowired
	private AuthorizationDao authorizationDao;

	@Autowired
	private DataService dataService;

	@PostConstruct
	public void populate() {
		this.createUsers(0, 4, Role.CUSTOMER);
		this.createToken(666000000);
	}

	public void createUsers(int initial, int size, Role role) {
		User user;
		for (int i = 0; i < size; i++) {
			user = new User(666000000 + initial + i, role.toString().toLowerCase() + (i + initial), "pass");
			userDao.save(user);
			authorizationDao.save(new Authorization(user, role));
		}
	}

	public Token createToken(long mobile) {
		Token token;
		User user = userDao.findByMobile(mobile);
		token = new Token(user);
		tokenDao.save(token);
		return token;
	}

	public void deleteAll() {
		dataService.deleteAllExceptAdmin();
	}
}
