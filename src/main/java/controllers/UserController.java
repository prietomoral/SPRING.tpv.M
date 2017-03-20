package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import api.exceptions.InvalidFieldModifyUserException;
import daos.users.AuthorizationDao;
import daos.users.UserDao;
import entities.users.Authorization;
import entities.users.Role;
import entities.users.User;
import wrappers.UserModifyWrapper;
import wrappers.UserWrapper;

@Controller
public class UserController {

    private UserDao userDao;

    private AuthorizationDao authorizationDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setAuthorizationDao(AuthorizationDao authorizationDao) {
        this.authorizationDao = authorizationDao;
    }

    public boolean registration(UserWrapper userWrapper, Role role) {
        if (null == userDao.findByMobile(userWrapper.getMobile())) {
            User user = new User(userWrapper.getMobile(), userWrapper.getUsername(), userWrapper.getPassword());
            userDao.save(user);
            authorizationDao.save(new Authorization(user, role));
            return true;
        } else {
            return false;
        }
    }

	public List<User> getAll() {
		return userDao.findAll();
	}
	
	public User getUserById (int id){
		return userDao.findOne(id);
	}
	
	public void userModify (UserModifyWrapper userModifyWrapper) throws InvalidFieldModifyUserException{
		User user = userDao.findOne(userModifyWrapper.getId());
		
		if ((userDao.findByUsername(userModifyWrapper.getUsername()) != null) && (user.getId() != userDao.findByUsername(userModifyWrapper.getUsername()).getId())){
			throw new InvalidFieldModifyUserException("\nUsername is already in use");
		}else if((userDao.findByDni(userModifyWrapper.getDni()) != null) && (user.getId() != userDao.findByDni(userModifyWrapper.getDni()).getId())){ 
			throw new InvalidFieldModifyUserException("\nDNI is already in use");
		}else if ((userDao.findByEmail(userModifyWrapper.getEmail()) != null) && (user.getId() != userDao.findByEmail(userModifyWrapper.getEmail()).getId())){
			throw new InvalidFieldModifyUserException("\nEmail is already in use");
		}else if ((userDao.findByMobile(userModifyWrapper.getMobile()) != null) && (user.getId() != userDao.findByMobile(userModifyWrapper.getMobile()).getId())){
			throw new InvalidFieldModifyUserException("\nMobile is already in use");
		}else{
			user.setAddress(userModifyWrapper.getAddress());
			user.setPassword(userModifyWrapper.getPassword());
			user.setDni(userModifyWrapper.getDni());
			user.setEmail(userModifyWrapper.getEmail());
			user.setMobile(userModifyWrapper.getMobile());
			user.setUsername(userModifyWrapper.getUsername());
			
			this.userDao.save(user);
		}
	}

}
