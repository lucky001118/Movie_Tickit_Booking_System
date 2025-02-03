package com.move.tickit.booking.service;

import com.move.tickit.booking.exception.VeiwShowsException;

public interface VeiwShowService {
	
	public void allShows();
	public void getShowByShowName(String name) throws VeiwShowsException ;

}
