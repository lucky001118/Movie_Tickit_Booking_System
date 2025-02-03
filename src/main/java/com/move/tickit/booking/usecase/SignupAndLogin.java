package com.move.tickit.booking.usecase;

import java.util.Scanner;

import com.move.tickit.booking.exception.UserException;
import com.move.tickit.booking.model.Role;
import com.move.tickit.booking.model.User;
import com.move.tickit.booking.service.UserService;
import com.move.tickit.booking.serviceImpl.UserServiceImpl;

public class SignupAndLogin {
	private Scanner scanner = new Scanner(System.in);
	private UserService userService = new UserServiceImpl();

	public void DoSignup() {
		
		System.out.println("*************************************************");
		System.out.println("        Signup / Creating new account ");
		System.out.println("*************************************************");
		
		System.out.println();
		
		User user = new User();
		
		System.out.print("Enter your name: ");
		String name = scanner.nextLine();
		user.setName(name.trim());
		
		System.out.print("Enter your email address: ");
		String email = scanner.next();
		user.setEmail(email.trim());
		
		scanner.nextLine();
		
		System.out.print("Enter create new password: ");
		String password = scanner.nextLine();
		user.setPassword(password.trim());
		
		//setting the role to the user
		user.setRole(Role.USER);
		
		
		System.out.print("Enter your current address: ");
		String address = scanner.nextLine();
		user.setAddress(address.trim());
		
		System.out.print("Enter your Mobile number: ");
		String mobile = scanner.nextLine();
		user.setMobileNo(mobile.trim());
		
		System.out.println();
		
		userService.saveUser(user);
		
		System.out.println();
		System.out.println("*************************************************");
		
	}
	
	public User doLogin() throws UserException {
		System.out.println("*************************************************");
		System.out.println("                Login Process ");
		System.out.println("*************************************************");
		
		System.out.println();
		
		User user = new User();
		
		System.out.print("Enter your registered email: ");
		String email = scanner.nextLine();
		user.setEmail(email.trim());
		System.out.print("Enter your password: ");
		String password = scanner.nextLine();
		user.setPassword(password.trim());
		
		System.out.println();
		user = userService.login(user);
		
		System.out.println("*************************************************");
		
		return user;
		
	}

}
