package com.move.tickit.booking.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "screens")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Screen {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    @ManyToOne
	    @JoinColumn(name = "theater_id", nullable = false)
	    private Theater theater;

	    @Column(nullable = false)
	    private String screen_name;

	    @Column(nullable = false)
	    private int seat_capacity;

	    @OneToMany(mappedBy = "screen", cascade = CascadeType.ALL)
	    private List<Seat> seats;

	    // Getters and Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Theater getTheater() {
		return theater;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}
	
	

	public String getScreen_name() {
		return screen_name;
	}

	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}

	public int getSeat_capacity() {
		return seat_capacity;
	}

	public void setSeat_capacity(int seat_capacity) {
		this.seat_capacity = seat_capacity;
	}

	public Screen(int id, Theater theater, String screen_name, int seat_capacity, List<Seat> seats) {
		super();
		this.id = id;
		this.theater = theater;
		this.screen_name = screen_name;
		this.seat_capacity = seat_capacity;
		this.seats = seats;
	}

	public Screen() {};
    
    
}
