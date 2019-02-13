package SpringRESTfulTest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SpringRESTfulTest.dao.UserDao;
import SpringRESTfulTest.model.User;

@Service
public class UserServiceImpl implements UserService{
	
	UserDao userDao;
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public List<User> listAllUser() {
		return userDao.listAllUser();
	}

	public void addUser(User user) {
		userDao.addUser(user);
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	public void delete(User user) {
		userDao.delete(user);
	}

	public User findUserById(User user) {
		return userDao.findUserById(user);
	}

}
