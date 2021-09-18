package com.kristovski.gbapp.booking;

import com.kristovski.gbapp.Room.Room;
import com.kristovski.gbapp.user.User;
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
import java.util.stream.Collectors;

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

            List<User> userList = new ArrayList<>();
            List<Booking> bookings = bookingRepository.findBookingByDateAndStartAndRoom(date, LocalTime.parse(time), room);
            for (Booking booking : bookings) {
                User user = booking.getUser();
                userList.add(user);
            }

            if (!timeIsBooked(bookingList, time) || (timeIsBooked(bookingList, time) && userList.size() < room.getCapacity())) {
                bookingList.add(i, new Booking());
                bookingList.get(i).setId(0L);
                bookingList.get(i).setDate(date);
                bookingList.get(i).setStart(LocalTime.parse(time));

            }
        }

        for (Booking booking : bookingList) {
            System.out.println(booking.getStart());
        }
        List<Booking> bookingList1 = bookingList.stream().limit(24).collect(Collectors.toList());


        return bookingList1;
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

    public List<Booking> findBookingByDateAndStartAndRoom(LocalDate date, LocalTime time, Room room) {
        return bookingRepository.findBookingByDateAndStartAndRoom(date, time, room);
    }

    public List<User> findUsersWithTheSameReservation(LocalDate date, LocalTime time, Room room){
        List<Booking> bookingByDateAndStartAndRoom = bookingRepository.findBookingByDateAndStartAndRoom(date, time, room);
        List<User> userList = new ArrayList<>();
        for (Booking booking : bookingByDateAndStartAndRoom) {
            userList.add(booking.getUser());
        }
        return userList;
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
