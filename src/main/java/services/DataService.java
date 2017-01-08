package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import daos.users.AuthorizationDao;
import daos.users.TokenDao;
import daos.users.UserDao;

@Service
public class DataService {

	@Autowired
	private Populate populate;

	@Autowired
	private AuthorizationDao authorizationDao;

	@Autowired
	private TokenDao tokenDao;

	@Autowired
	private UserDao userDao;

	public void deleteAllExceptAdmin() {
		authorizationDao.deleteAll();
		tokenDao.deleteAll();
		userDao.deleteAll();
		populate.createDefaultAdmin();
	}

}
