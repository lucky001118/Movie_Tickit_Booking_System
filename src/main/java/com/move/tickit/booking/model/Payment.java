package com.move.tickit.booking.model;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Payment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private PaymentStatus payment_status; // SUCCESS, FAILED

    @Column(nullable = false)
    private LocalDate payment_date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public PaymentStatus getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(PaymentStatus payment_status) {
		this.payment_status = payment_status;
	}

	public LocalDate getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(LocalDate payment_date) {
		this.payment_date = payment_date;
	}

	public Payment(int id, Booking booking, double amount, PaymentStatus payment_status, LocalDate payment_date) {
		super();
		this.id = id;
		this.booking = booking;
		this.amount = amount;
		this.payment_status = payment_status;
		this.payment_date = payment_date;
	}

    // Getters and Setters
    
	public Payment() {};
    
}

