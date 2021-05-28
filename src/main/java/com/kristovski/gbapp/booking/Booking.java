package com.kristovski.gbapp.booking;

import com.kristovski.gbapp.room.Room;
import com.kristovski.gbapp.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@Getter
@Setter
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private BookingAvailability bookingAvailability;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    private LocalTime startTime;
    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;


}
