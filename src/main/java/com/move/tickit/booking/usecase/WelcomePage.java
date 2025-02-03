package com.move.tickit.booking.usecase;

import java.util.Scanner;

import com.move.tickit.booking.exception.UserException;
import com.move.tickit.booking.exception.VeiwShowsException;
import com.move.tickit.booking.model.Role;
import com.move.tickit.booking.model.User;

public class WelcomePage {
	
	private Scanner scanner = new Scanner(System.in);
	private SignupAndLogin signupAndLogin = new SignupAndLogin();
	private AdminAndUserOperations adminAndUser = new AdminAndUserOperations();
	
	public void welocme() {
		
		 // Create a decorative welcome banner
        System.out.println("************************************************");
        System.out.println("*                                              *");
        System.out.println("*          WELCOME TO MOVIE TICKET             *");
        System.out.println("*             BOOKING SYSTEM                   *");
        System.out.println("*                                              *");
        System.out.println("************************************************");

        // Add some spacing for readability
        System.out.println();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("   Your one-stop destination for booking tickets");
        System.out.println("   to your favorite movies in just a few clicks!");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        // Add a friendly closing message
        System.out.println();
        System.out.println("************************************************");
        System.out.println("* Sit back, relax, and enjoy the show!         *");
        System.out.println("*                                              *");
        System.out.println("************************************************");

	}
	
	public void lists() throws UserException, VeiwShowsException {
		
		System.out.println();
        System.out.println("1. Login");
        System.out.println("2. Sign Up");
        System.out.println("3. Exit\n");

        // Prompt user for an option
        System.out.print("Please select an option (1-3): ");
        int choice = scanner.nextInt();

        // Perform action based on user choice
        switch (choice) {
            case 1:
                System.out.println("\nRedirecting to the login section...");
                System.out.println();
                User user = new User();
                user = signupAndLogin.doLogin();
                
                while(true) {
                	if(user.getRole()==Role.ADMIN) {
                    	adminAndUser.AdminOperations(user);
                    }else if(user.getRole()==Role.USER) {
                    	adminAndUser.UserOperation(user);
                    }
                    
                    System.out.println();
                    scanner.nextLine();
                    System.out.print("Enter exit if wants to exit or continue: ");
                    String userChoise = scanner.nextLine();
                    
                    //exit condition of the loop
                    if(userChoise.equalsIgnoreCase("exit")) {
                    	break;
                    }
                }
                break;
            case 2:
                System.out.println("\nRedirecting to the sign-up section...");
                System.out.println();
                signupAndLogin.DoSignup();
                break;
            case 3:
                System.out.println("\nThank you for visiting! Goodbye.");
                System.exit(0);
                break;
            default:
                System.out.println("\nInvalid choice! Please try again.");
                break;
        }

        scanner.close();
	}

}
