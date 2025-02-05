package com.move.tickit.booking.dao.impl;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import com.move.tickit.booking.config.HibernateConfig;
import com.move.tickit.booking.dao.BookingDao;
import com.move.tickit.booking.exception.BookingException;
import com.move.tickit.booking.model.Booking;
import com.move.tickit.booking.model.Seat;
import com.move.tickit.booking.model.Show;
import com.move.tickit.booking.model.Status;
import com.move.tickit.booking.model.User;

public class BookingDaoImpl implements BookingDao{

	@Override
	public void getBookingInformationByUserId(User user) throws BookingException {
		try(Session session = HibernateConfig.getSessionFactory().openSession()){
			
			String sql = "SELECT \r\n"
					+ "    b.id AS booking_id,\r\n"
					+ "    u.name AS user_name,\r\n"
					+ "    u.email AS user_email,\r\n"
					+ "    m.title AS movie_title,\r\n"
					+ "    m.genre AS movie_genre,\r\n"
					+ "    m.language AS movie_language,\r\n"
					+ "    s.show_time,\r\n"
					+ "    s.ticket_price,\r\n"
					+ "    seats.seat_number,\r\n"
					+ "    seats.seat_type,\r\n"
					+ "    b.booking_date,\r\n"
					+ "    b.status\r\n"
					+ "FROM \r\n"
					+ "    bookings b\r\n"
					+ "JOIN \r\n"
					+ "    users u ON b.user_id = u.id\r\n"
					+ "JOIN \r\n"
					+ "    shows s ON b.show_id = s.id\r\n"
					+ "JOIN \r\n"
					+ "    movies m ON s.movie_id = m.id\r\n"
					+ "JOIN \r\n"
					+ "    seats ON b.seat_id = seats.id\r\n"
					+ "WHERE \r\n"
					+ "    b.user_id = :userId\r\n"
					+ "    \r\n"
					+ "";  // Filter by movie title
	        
	        NativeQuery<Object[]> query = session.createNativeQuery(sql);
	        query.setParameter("userId", user.getId());  // Set the movie title dynamically
	        
	        List<Object[]> bookingsInfo = query.list();
	        session.close();
	        
	     // Print table header
	        System.out.printf("+--------------+------------------+-----------------------+--------------------+-------------+----------------+---------------------------+--------------+-------------+--------------+---------------------------+------------+\n");
	        System.out.printf("| booking_id   | user_name        | user_email            | movie_title        | movie_genre | movie_language | show_time                 | ticket_price | seat_number | seat_type    | booking_date              | status     |\n");
	        System.out.printf("+--------------+------------------+-----------------------+--------------------+-------------+----------------+---------------------------+--------------+-------------+--------------+---------------------------+------------+\n");

	        // Print table rows
	        for (Object[] row : bookingsInfo) {
	            System.out.printf("| %-12s | %-16s | %-21s | %-18s | %-11s | %-14s | %-25s | %-12s | %-11s | %-12s | %-25s | %-10s |\n",
	                    row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7], row[8], row[9], row[10], row[11]);
	        }

	        // Print table footer
	        System.out.printf("+--------------+------------------+-----------------------+--------------------+-------------+----------------+---------------------------+--------------+-------------+--------------+---------------------------+------------+\n");


			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void bookShowUsingUserId(User user,String movieTitle, String seatNumber) {
		
		try(Session session = HibernateConfig.getSessionFactory().openSession()){
			
			String showIdQuery = "SELECT \r\n"
					+ "    sh.id AS show_id\r\n"
					+ "FROM \r\n"
					+ "    shows sh\r\n"
					+ "INNER JOIN \r\n"
					+ "    movies m ON sh.movie_id = m.id\r\n"
					+ "INNER JOIN \r\n"
					+ "    screens sc ON sh.screen_id = sc.id\r\n"
					+ "WHERE \r\n"
					+ "    m.title = :movieTitle\r\n"
					+ "ORDER BY \r\n"
					+ "    sh.show_time;" ;
			
			NativeQuery<Integer> queryShowId = session.createNativeQuery(showIdQuery);
			queryShowId.setParameter("movieTitle", movieTitle);  // Set the movie title dynamically
	        
	        List<Integer> showIds = queryShowId.list();
	        int showId = showIds.get(0);
	        Show show = new Show();
	        show.setId(showId);
	        
//	        getting the seat id by seat number and movie title
	        String seatIdQuery = "    SELECT \r\n"
	        		+ "    s.id AS seat_id\r\n"
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
	        		+ "    AND s.seat_number = :seatNumber \r\n"
	        		+ "    AND sh.id NOT IN (\r\n"
	        		+ "        SELECT b.show_id \r\n"
	        		+ "        FROM bookings b \r\n"
	        		+ "        WHERE b.seat_id = s.id\r\n"
	        		+ "    )\r\n"
	        		+ "ORDER BY \r\n"
	        		+ "    sh.show_time, s.seat_number;";
	        
	        NativeQuery<Integer> querySeatId = session.createNativeQuery(seatIdQuery);
			querySeatId.setParameter("movieTitle", movieTitle );  // Set the movie title dynamically
			querySeatId.setParameter("seatNumber",seatNumber);
	        
	        List<Integer> seatIds = queryShowId.list();
	        int seatId = showIds.get(0);
	        Seat seat = new Seat();
	        seat.setId(seatId);
	        
	        session.close();
	        
	        Session session1 = HibernateConfig.getSessionFactory().openSession();
	        
	        Booking booking = new Booking();
	        booking.setUser(user);
	        booking.setShow(show);
	        booking.setSeat(seat);
	        booking.setBooking_date(LocalDate.now());
	        booking.setStatus(Status.CONFIRMED);
	        
	        session1.beginTransaction();	        
	        session1.save(booking);
	        session1.getTransaction().commit();
	        
	        System.out.println("Booking is done...");
	        
	        //booking is done now make payment
	        
	        session1.close();
		}
		
	}

}
