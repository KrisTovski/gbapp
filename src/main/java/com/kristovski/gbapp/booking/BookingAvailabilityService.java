package com.kristovski.gbapp.booking;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookingAvailabilityService {

    private BookingAvailabilityRepository bookingAvailabilityRepository;

    public List<BookingAvailability> findAll() {
        return bookingAvailabilityRepository.findAll();
    }
}
