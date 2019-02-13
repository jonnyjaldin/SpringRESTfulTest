package SpringRESTfulTest.dao;

import java.util.List;

import SpringRESTfulTest.model.User;

public interface UserDao {
	
	public List<User> listAllUser();
	
	public void addUser(User user);
	
	public void updateUser(User user);
	
	public void delete(User user);
	
	public User findUserById(User user);

}
