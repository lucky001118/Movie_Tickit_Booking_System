package com.move.tickit.booking.usecase;

import java.util.Scanner;

import com.move.tickit.booking.model.User;
import com.move.tickit.booking.service.BookingService;
import com.move.tickit.booking.serviceImpl.BookingServiceImpl;

public class BookShowUseCase {
	private Scanner scanner = new Scanner(System.in);
	private BookingService bookingService = new BookingServiceImpl();

	public void BookMyShowOperation(User user) {
		
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("1. See Book your show. ");

        // Prompt user for an option
        System.out.print("Please select an option (1): ");
        int chose = scanner.nextInt();
        
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        
        switch(chose) {
        case 1:
        	scanner.nextLine();
        	System.out.print("Enter your movie title: ");
        	String movieTitle = scanner.nextLine();
        	
        	System.out.print("Enter seat number that you wants to book: ");
        	String seatNumber = scanner.nextLine();
        	
        	bookingService.bookShowUsingUserId(user, movieTitle, seatNumber);
        	System.out.println();
        	break;
        default:
        	System.out.println("\nInvalid choice! Please try again.");
        	break;
        }
		
	}
}
