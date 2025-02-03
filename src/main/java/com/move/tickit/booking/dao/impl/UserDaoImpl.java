package com.move.tickit.booking.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.move.tickit.booking.config.HibernateConfig;
import com.move.tickit.booking.dao.UserDao;
import com.move.tickit.booking.exception.UserException;
import com.move.tickit.booking.model.User;

public class UserDaoImpl implements UserDao{

	@Override
	public void saveUser(User user) {
		try(Session session = HibernateConfig.getSessionFactory().openSession()){
			session.beginTransaction();
	        session.save(user);
	        System.out.println("User Saved successfully...");
	        session.getTransaction().commit();
	        session.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public User getUserByid(int userId) throws UserException{
		// TODO Auto-generated method stub
		
		User newUser = new User();
		
		try(Session session = HibernateConfig.getSessionFactory().openSession()){
			
			session.beginTransaction();
			
	        newUser = session.get(User.class,userId);
	        
	        if(newUser==null) {
	        	throw new UserException("User not found by provided userId: "+userId);
	        }
	        
	        
	        System.out.format("+----+----------------------+------------------------------+------------------+----------+----------------------+--------------+%n");
	        System.out.format("| ID | Name                 | Email                        | Password         | Role     | Address              | Mobile No.   |%n");
	        System.out.format("+----+----------------------+------------------------------+------------------+----------+----------------------+--------------+%n");

	            System.out.format("| %-2d | %-20s | %-28s | %-16s | %-8s | %-20s | %-12s |%n", 
	                              newUser.getId(), newUser.getName(), newUser.getEmail(), newUser.getPassword(), 
	                              newUser.getRole(), newUser.getAddress(), newUser.getMobileNo());


	        System.out.format("+----+----------------------+------------------------------+------------------+----------+----------------------+--------------+%n");
	        
	        //commit the transaction
	        session.getTransaction().commit();
	        
	        //close the transaction
	        session.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return newUser;
	}

	@Override
	public List<User> getAllUsers() throws UserException{
		List<User> newAllUser = new ArrayList<>();
		
		try(Session session = HibernateConfig.getSessionFactory().openSession()){
			
			session.beginTransaction();
			
	        newAllUser = session.createQuery("from User").list();
	        
	        if(newAllUser==null) {
	        	throw new UserException("User not found in the User Table, Table is Empty");
	        }
	        
	        System.out.format("+----+----------------------+------------------------------+------------------+----------+----------------------+--------------+%n");
	        System.out.format("| ID | Name                 | Email                        | Password         | Role     | Address              | Mobile No.   |%n");
	        System.out.format("+----+----------------------+------------------------------+------------------+----------+----------------------+--------------+%n");

	        for (User user1 : newAllUser) {
	            System.out.format("| %-2d | %-20s | %-28s | %-16s | %-8s | %-20s | %-12s |%n", 
	                              user1.getId(), user1.getName(), user1.getEmail(), user1.getPassword(), 
	                              user1.getRole(), user1.getAddress(), user1.getMobileNo());
	        }

	        System.out.format("+----+----------------------+------------------------------+------------------+----------+----------------------+--------------+%n");

	        
	        //commit the transaction
	        session.getTransaction().commit();
	        
	        //close the transaction
	        session.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
		try(Session session = HibernateConfig.getSessionFactory().openSession()){
			
			session.beginTransaction();
			
	        session.saveOrUpdate(user);   //update query
	        
	      //commit the transaction
	        session.getTransaction().commit();
	        
	        //close the transaction
	        session.close();

	        
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public User login(User user) throws UserException {  
		
		User newUser = new User();
		
		try(Session session = HibernateConfig.getSessionFactory().openSession()){
			
			session.beginTransaction();
			
			 // HQL Query to get a single record (using the Hibernate Query Language)
	        String hql = "FROM User WHERE Email = :emailParam and Password = :passParam";
	        Query<User> query = session.createQuery(hql, User.class);
	        
	        query.setParameter("emailParam", user.getEmail()); // Replace email with the desired record email
	        query.setParameter("passParam", user.getPassword());
	        
	        
	        // Fetch single record
	        newUser = query.uniqueResult();
	        
	        
	        if (newUser != null) {
	        	System.out.println();
	            System.out.println("Login Success..!");
	        } else {
	            System.out.println("No record found!");
	            throw new UserException("User not exist with this email: "+user.getEmail()+" and password: "+user.getPassword());
	        }
			
	        
	        //commit the transaction
	        session.getTransaction().commit();
	        
	        //close the transaction
	        session.close();

	        
		}catch(Exception e) {
			e.printStackTrace();
		}
		return newUser;
	}

}
