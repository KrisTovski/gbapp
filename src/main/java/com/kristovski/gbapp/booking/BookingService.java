package com.kristovski.gbapp.booking;

import com.kristovski.gbapp.Room.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }


    public Page<Booking> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return bookingRepository.findAll(pageable);
    }

    public Page<Booking> findPaginatedByUser(Long id, int pageNo, int pageSize, String sortField, String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return bookingRepository.findAllByUserId(id, pageable);
    }

    public void addBooking(Booking booking) {

        LocalTime start = booking.getStart();
        booking.setEnd(start.plusHours(1));
        bookingRepository.save(booking);


    }

    public void deleteBookingById(Long id) {
        bookingRepository.deleteById(id);
    }

    public List<Booking> findBookingsByDate(LocalDate date, Room room) {

        List<Booking> bookingList = bookingRepository.findBookingsByDateAndRoom(date, room);

        if (bookingList == null) {
            bookingList = new ArrayList<>();
        }

        for (int i = 0; i <= 23; i++) {
            String time = (i + 0) + ":00";
            if (time.length() != 5) {
                time = "0" + time;
            }


            if (!timeIsBooked(bookingList, time)) {
                bookingList.add(i, new Booking());
                bookingList.get(i).setId(0L);
                bookingList.get(i).setDate(date);
                bookingList.get(i).setStart(LocalTime.parse(time));
                // TODO: add endTime
            }
        }

        return bookingList;
    }

    private boolean timeIsBooked(List<Booking> bookingList, String time) {
        if (bookingList == null) {
            return false;
        }
        for (Booking b : bookingList) {
            if (b.getStart() != null && b.getStart().equals(LocalTime.parse(time))) {
                return true;
            }
        }
        return false;
    }

    public boolean bookingExists(LocalDate date, LocalTime time, Room room) {
        return bookingRepository.existsBookingByDateAndStartAndRoom(date, time, room);
    }

    @Transactional
    public void mergeWithExistingAndUpdate(Booking booking) {
        Booking existingBooking = getBookingById(booking.getId());
        existingBooking.setDate(booking.getDate());
        LocalTime start = booking.getStart();
        existingBooking.setStart(start);
        existingBooking.setEnd(start.plusHours(1));
        existingBooking.setRoom(booking.getRoom());

    }

    public Booking getBookingById(Long id) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        Booking booking = null;
        if (bookingOptional.isPresent()) {
            booking = bookingOptional.get();
        } else {
            throw new RuntimeException("Booking not found for id :: " + id);
        }
        return booking;
    }
}
