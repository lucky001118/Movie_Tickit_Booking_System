package com.move.tickit.booking.main;

import com.move.tickit.booking.exception.BookingException;
import com.move.tickit.booking.exception.UserException;
import com.move.tickit.booking.exception.VeiwShowsException;
import com.move.tickit.booking.usecase.WelcomePage;


public class MainApp {

	public static void main(String[] args) throws UserException, VeiwShowsException, BookingException {
		
		WelcomePage welcome = new WelcomePage();
		welcome.welocme();
		welcome.lists();
		
		

    }
}
