package com.move.tickit.booking.serviceImpl;

import com.move.tickit.booking.dao.SeatDao;
import com.move.tickit.booking.dao.impl.SeatDaoImpl;
import com.move.tickit.booking.service.SeatService;

public class SeatServiceImpl implements SeatService{
	private SeatDao seatDao = new SeatDaoImpl();

	@Override
	public void getAvailableSeatsUsingMovieTitle(String movieTitle) {
		seatDao.getAvailableSeatsUsingMovieTitle(movieTitle);	
	}

}
