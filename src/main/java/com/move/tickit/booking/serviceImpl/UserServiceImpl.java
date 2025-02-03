package com.move.tickit.booking.serviceImpl;

import java.util.List;

import com.move.tickit.booking.dao.UserDao;
import com.move.tickit.booking.dao.impl.UserDaoImpl;
import com.move.tickit.booking.exception.UserException;
import com.move.tickit.booking.model.User;
import com.move.tickit.booking.service.UserService;

public class UserServiceImpl implements UserService{
	
	private UserDao userDao = new UserDaoImpl();

	@Override
	public void saveUser(User user) {
		
		userDao.saveUser(user);
		
	}

	@Override
	public User getUserByid(int userId) throws UserException {
		
		return userDao.getUserByid(userId);
	}

	@Override
	public List<User> getAllUsers() throws UserException {
		
		return userDao.getAllUsers();
	}

	@Override
	public void updateUser(User user) {
		
		userDao.updateUser(user);
	}

	@Override
	public User login(User user) throws UserException {
		// TODO Auto-generated method stub
		return userDao.login(user);
	}

}
