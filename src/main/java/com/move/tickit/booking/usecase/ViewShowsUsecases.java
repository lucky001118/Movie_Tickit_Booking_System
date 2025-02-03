package com.move.tickit.booking.usecase;

import java.util.Scanner;

import com.move.tickit.booking.exception.VeiwShowsException;
import com.move.tickit.booking.service.VeiwShowService;
import com.move.tickit.booking.serviceImpl.ViewShowsServiceImpl;

public class ViewShowsUsecases {
	
	private Scanner scanner = new Scanner(System.in);
	private VeiwShowService veiwShowService = new ViewShowsServiceImpl();
	
	public void AllOperations() throws VeiwShowsException {
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("1. See All Shows");
        System.out.println("2. Search The Show using name");

        // Prompt user for an option
        System.out.print("Please select an option (1 or 2): ");
        int chose = scanner.nextInt();
        
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        
        switch(chose) {
        case 1:
        	System.out.println();
        	veiwShowService.allShows();
        	break;
        case 2:
        	System.out.println();
        	scanner.nextLine();
        	System.out.print("Enter the movie name: ");
        	String movieName = scanner.nextLine();
        	veiwShowService.getShowByShowName(movieName);
        	break;
        default:
        	System.out.println("\nInvalid choice! Please try again.");
        	break;
        }
		
	}

}
