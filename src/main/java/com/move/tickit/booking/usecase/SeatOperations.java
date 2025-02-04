package com.move.tickit.booking.usecase;

import java.util.Scanner;

import com.move.tickit.booking.service.SeatService;
import com.move.tickit.booking.serviceImpl.SeatServiceImpl;

public class SeatOperations {
	private Scanner scanner = new Scanner(System.in);
	private SeatService seatService = new SeatServiceImpl();

	public void AllSeatOperations() {
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("1. See All Seats available with the movie title: ");

        // Prompt user for an option
        System.out.print("Please select an option (1): ");
        int chose = scanner.nextInt();
        
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        
        switch(chose) {
        case 1:
        	System.out.println();
        	scanner.nextLine();
        	System.out.print("Enter the movie name: ");
        	String movieName = scanner.nextLine();
        	seatService.getAvailableSeatsUsingMovieTitle(movieName);
        	break;
        default:
        	System.out.println("\nInvalid choice! Please try again.");
        	break;
        }
		
	}
}
