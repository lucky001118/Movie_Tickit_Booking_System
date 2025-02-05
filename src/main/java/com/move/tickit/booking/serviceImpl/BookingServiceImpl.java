package com.move.tickit.booking.serviceImpl;

import com.move.tickit.booking.dao.BookingDao;
import com.move.tickit.booking.dao.impl.BookingDaoImpl;
import com.move.tickit.booking.exception.BookingException;
import com.move.tickit.booking.model.User;
import com.move.tickit.booking.service.BookingService;

public class BookingServiceImpl implements BookingService{

	private BookingDao bookingDao = new BookingDaoImpl();
	
	@Override
	public void getBookingInformationByUserId(User user) throws BookingException {
		bookingDao.getBookingInformationByUserId(user);
		
	}
	
	@Override
	public void bookShowUsingUserId(User user,String movieTitle, String seatNumber) {
		// TODO Auto-generated method stub
		bookingDao.bookShowUsingUserId(user, movieTitle, seatNumber);
	}

}
