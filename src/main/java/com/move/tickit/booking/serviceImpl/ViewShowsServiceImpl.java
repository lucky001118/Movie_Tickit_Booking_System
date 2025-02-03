package com.move.tickit.booking.serviceImpl;

import com.move.tickit.booking.dao.ViewShowsDao;
import com.move.tickit.booking.dao.impl.ViewShowsDaoImpl;
import com.move.tickit.booking.exception.VeiwShowsException;
import com.move.tickit.booking.service.VeiwShowService;

public class ViewShowsServiceImpl implements VeiwShowService{
	private ViewShowsDao viewShowsDao = new ViewShowsDaoImpl();

	@Override
	public void allShows() {
		
		viewShowsDao.allShows();
	}

	@Override
	public void getShowByShowName(String name) throws VeiwShowsException {
		
		viewShowsDao.getShowByShowName(name);
		
	}

}
