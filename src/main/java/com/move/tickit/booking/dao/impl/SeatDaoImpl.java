package com.move.tickit.booking.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import com.move.tickit.booking.config.HibernateConfig;
import com.move.tickit.booking.dao.SeatDao;

public class SeatDaoImpl implements SeatDao{

	@Override
	public void getAvailableSeatsUsingMovieTitle(String movieTitle) {
		
		try(Session session = HibernateConfig.getSessionFactory().openSession()){
			
			String sql = "SELECT \r\n"
					+ "    s.seat_number,\r\n"
					+ "    s.seat_type,\r\n"
					+ "    sc.screen_name,\r\n"
					+ "    m.title AS movie_name,\r\n"
					+ "    sh.show_time,\r\n"
					+ "    sc.screen_name as screen\r\n"
					+ "FROM \r\n"
					+ "    seats s\r\n"
					+ "INNER JOIN \r\n"
					+ "    screens sc ON s.screen_id = sc.id\r\n"
					+ "INNER JOIN \r\n"
					+ "    shows sh ON sc.id = sh.screen_id\r\n"
					+ "INNER JOIN \r\n"
					+ "    movies m ON sh.movie_id = m.id\r\n"
					+ "WHERE \r\n"
					+ "    m.title = :movieTitle \r\n"
					+ "    AND s.id NOT IN (\r\n"
					+ "        SELECT seat_id \r\n"
					+ "        FROM bookings \r\n"
					+ "        WHERE show_id = sh.id\r\n"
					+ "    )\r\n"
					+ "ORDER BY \r\n"
					+ "    sh.show_time, s.seat_number";  // Filter by movie title
	        
	        NativeQuery<Object[]> query = session.createNativeQuery(sql);
	        query.setParameter("movieTitle", movieTitle);  // Set the movie title dynamically
	        
	        List<Object[]> seats = query.list();
	        session.close();
	        
	     // Print table header
	        System.out.printf("+------------+-----------+--------------+----------------------+-----------------------+-----------+\n");
	        System.out.printf("| Seat No.   | Type      | Screen       | Movie Title          | Show Time             | Screen    |\n");
	        System.out.printf("+------------+-----------+--------------+----------------------+-----------------------+-----------+\n");

	        // Print table rows
	        for (Object[] row : seats) {
	            System.out.printf("| %-10s | %-9s | %-12s | %-20s | %-20s | %-9s |\n",
	                    row[0], row[1], row[2], row[3], row[4], row[5]);
	        }

	        // Print table footer
	        System.out.printf("+------------+-----------+--------------+----------------------+-----------------------+-----------+\n");
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
