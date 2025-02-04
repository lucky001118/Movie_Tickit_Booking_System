package com.move.tickit.booking.usecase;

import java.util.Scanner;

import com.move.tickit.booking.exception.UserException;
import com.move.tickit.booking.exception.VeiwShowsException;
import com.move.tickit.booking.model.User;
import com.move.tickit.booking.service.UserService;
import com.move.tickit.booking.serviceImpl.UserServiceImpl;

public class AdminAndUserOperations {
	private Scanner scanner = new Scanner(System.in);
	private UserService userService = new UserServiceImpl();
	private ViewShowsUsecases viewShowsUsecases = new ViewShowsUsecases();
	private SeatOperations seatOperations = new SeatOperations();
	public void AdminOperations(User user) {
		System.out.println();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("            Welcome "+user.getName());
        System.out.println("         Your All "+user.getRole()+" operations are here");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
	}
	
	public void UserOperation(User user) throws UserException, VeiwShowsException{
		System.out.println();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("            Welcome "+user.getName());
        System.out.println("         Your All "+user.getRole()+" operations are here");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
        
        System.out.print("Enter 1 for see your profile \n"+"Enter 2 for the get Available moveis lists \n"+"Enter 3 for Seat related informations ");
        int choise = scanner.nextInt();
        
        switch (choise) {
		case 1:
			userService.getUserByid(user.getId());
			break;
			
		case 2:
			viewShowsUsecases.AllOperations();
			break;
		case 3:
			seatOperations.AllSeatOperations();
			break;
		default:
			System.out.println("Invailid entry..");
			break;
		}
        
	}
}
