package com.kristovski.gbapp.booking;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
public class BookingAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private LocalDate bookingDate;
    @OneToMany
    private Set<TimeSlot> timeSlots;

    @Override
    public String toString() {
        return "BookingAvailability{" +
                "Id=" + Id +
                ", bookingDate=" + bookingDate +
                '}';
    }
}
