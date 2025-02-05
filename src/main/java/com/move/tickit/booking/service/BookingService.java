package com.move.tickit.booking.service;

import com.move.tickit.booking.exception.BookingException;
import com.move.tickit.booking.model.User;

public interface BookingService {

	public void getBookingInformationByUserId(User user) throws BookingException;
	public void bookShowUsingUserId(User user,String movieTitle, String seatNumber);
}
