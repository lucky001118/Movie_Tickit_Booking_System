package com.move.tickit.booking.usecase;

import java.util.Scanner;

import com.move.tickit.booking.exception.BookingException;
import com.move.tickit.booking.model.User;
import com.move.tickit.booking.service.BookingService;
import com.move.tickit.booking.serviceImpl.BookingServiceImpl;

public class BookingUsecases {

	private Scanner scanner = new Scanner(System.in);
	private BookingService bookingService = new BookingServiceImpl();
	
	public void AllBookingUserOperations(User user) throws BookingException {
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("1. See All booking done by you. ");

        // Prompt user for an option
        System.out.print("Please select an option (1): ");
        int chose = scanner.nextInt();
        
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        
        switch(chose) {
        case 1:
        	System.out.println();
        	bookingService.getBookingInformationByUserId(user);
        	break;
        default:
        	System.out.println("\nInvalid choice! Please try again.");
        	break;
        }
	}
}
