package com.kristovski.gbapp.booking;

import com.kristovski.gbapp.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalTime;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BookingAvailability bookingAvailability;
    private TimeSlot timeSlot;
    private User user;
    private LocalTime startTime;

}
