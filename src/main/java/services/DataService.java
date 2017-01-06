package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import daos.AuthorizationDao;
import daos.TokenDao;
import daos.UserDao;

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
