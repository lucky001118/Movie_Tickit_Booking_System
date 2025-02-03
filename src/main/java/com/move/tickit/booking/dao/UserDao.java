package com.move.tickit.booking.dao;

import java.util.List;

import com.move.tickit.booking.exception.UserException;
import com.move.tickit.booking.model.User;

public interface UserDao {
	public void saveUser(User user);
	public User getUserByid(int userId) throws UserException;
	public List<User> getAllUsers() throws UserException;
	public void updateUser(User user);
	
	public User login(User user) throws UserException;
}
