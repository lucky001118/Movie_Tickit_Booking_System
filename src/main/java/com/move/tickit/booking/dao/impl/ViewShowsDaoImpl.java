package com.move.tickit.booking.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import com.move.tickit.booking.config.HibernateConfig;
import com.move.tickit.booking.dao.ViewShowsDao;
import com.move.tickit.booking.exception.VeiwShowsException;

public class ViewShowsDaoImpl implements ViewShowsDao{

	@Override
	public void allShows() {
		try(Session session = HibernateConfig.getSessionFactory().openSession()){
			
			String sql = "SELECT \r\n"
	        		+ "    m.title AS movie_title, \r\n"
	        		+ "    m.genre, \r\n"
	        		+ "    m.duration, \r\n"
	        		+ "    m.language, \r\n"
	        		+ "    m.release_date,\r\n"
	        		+ "    t.name AS theater_name,\r\n"
	        		+ "    t.location AS theater_location,\r\n"
	        		+ "    s.screen_name,\r\n"
	        		+ "    s.seat_capacity,\r\n"
	        		+ "    sh.show_time,\r\n"
	        		+ "    sh.ticket_price\r\n"
	        		+ "FROM movies m\r\n"
	        		+ "JOIN shows sh ON m.id = sh.movie_id\r\n"
	        		+ "JOIN screens s ON sh.screen_id = s.id\r\n"
	        		+ "JOIN theaters t ON s.theater_id = t.id;";
			
	        NativeQuery<Object[]> query = session.createNativeQuery(sql);
	        List<Object[]> movies = query.list();
	      
	        session.close();
	        
	     // Print table header with extended column spaces
	        System.out.println("+----+----------------------+---------------+----------+----------+--------------+----------------------------+------------------------+-----------+--------------------------+----------+");
	        System.out.printf("| %-2s | %-20s | %-13s | %-8s | %-8s | %-12s | %-26s | %-22s | %-9s | %-24s | %-8s |%n",
	                "ID", "Movie Title", "Genre", "Duration", "Language", "Release Date",
	                "Theater Name", "Theater Location", "Screen", "Show Time", "Price");
	        System.out.println("+----+----------------------+---------------+----------+----------+--------------+----------------------------+------------------------+-----------+--------------------------+----------+");

	        // Print rows
	        int id = 1;
	        for (Object[] movie : movies) {
	            System.out.printf("| %-2d | %-20s | %-13s | %-8s | %-8s | %-12s | %-26s | %-22s | %-9s | %-24s | %-8s |%n",
	                    id++, movie[0], movie[1], movie[2], movie[3], movie[4], 
	                    movie[5], movie[6], movie[7], movie[9], movie[10]);
	        }

	        // Print footer
	        System.out.println("+----+----------------------+---------------+----------+----------+--------------+----------------------------+------------------------+-----------+--------------------------+----------+");
	 
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void getShowByShowName(String name) throws VeiwShowsException {
		try(Session session = HibernateConfig.getSessionFactory().openSession()){
			
			        
			        String sql = "SELECT " +
			                "    m.title AS movie_title, " +
			                "    m.genre, " +
			                "    m.duration, " +
			                "    m.language, " +
			                "    m.release_date, " +
			                "    t.name AS theater_name, " +
			                "    t.location AS theater_location, " +
			                "    s.screen_name, " +
			                "    s.seat_capacity, " +
			                "    sh.show_time, " +
			                "    sh.ticket_price " +
			                "FROM movies m " +
			                "JOIN shows sh ON m.id = sh.movie_id " +
			                "JOIN screens s ON sh.screen_id = s.id " +
			                "JOIN theaters t ON s.theater_id = t.id " +
			                "WHERE m.title = :movieTitle";  // Filter by movie title
			        
			        NativeQuery<Object[]> query = session.createNativeQuery(sql);
			        query.setParameter("movieTitle", name);  // Set the movie title dynamically
			        
			        List<Object[]> movies = query.list();
			        session.close();

			        if (movies.isEmpty()) {
			            System.out.println("No shows found for the movie: " + name);
			            throw new VeiwShowsException("No shows available for the movie: " + name);
			        }
			        
			        
			     // Print table header with extended column spaces
			        System.out.println("+----+----------------------+---------------+----------+----------+--------------+----------------------------+------------------------+-----------+--------------------------+----------+");
			        System.out.printf("| %-2s | %-20s | %-13s | %-8s | %-8s | %-12s | %-26s | %-22s | %-9s | %-24s | %-8s |%n",
			                "ID", "Movie Title", "Genre", "Duration", "Language", "Release Date",
			                "Theater Name", "Theater Location", "Screen", "Show Time", "Price");
			        System.out.println("+----+----------------------+---------------+----------+----------+--------------+----------------------------+------------------------+-----------+--------------------------+----------+");

			        // Print rows
			        int id = 1;
			        for (Object[] movie : movies) {
			            System.out.printf("| %-2d | %-20s | %-13s | %-8s | %-8s | %-12s | %-26s | %-22s | %-9s | %-24s | %-8s |%n",
			                    id++, movie[0], movie[1], movie[2], movie[3], movie[4], 
			                    movie[5], movie[6], movie[7], movie[9], movie[10]);
			        }

			        // Print footer
			        System.out.println("+----+----------------------+---------------+----------+----------+--------------+----------------------------+------------------------+-----------+--------------------------+----------+");
			 
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	
}
}
