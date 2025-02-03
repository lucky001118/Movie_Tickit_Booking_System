package com.move.tickit.booking.dao;

import com.move.tickit.booking.exception.VeiwShowsException;

public interface ViewShowsDao {
	
	public void allShows();
	public void getShowByShowName(String name) throws VeiwShowsException ;
}
