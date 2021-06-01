package com.kristovski.gbapp.booking;

import com.kristovski.gbapp.room.RoomRepository;
import com.kristovski.gbapp.user.UserRepository;
import com.kristovski.gbapp.user.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {

    private BookingRepository bookingRepository;

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }


}
