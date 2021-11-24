package com.kristovski.gbapp.booking;

import com.kristovski.gbapp.room.Room;
import com.kristovski.gbapp.room.RoomRepository;
import com.kristovski.gbapp.user.User;
import liquibase.pro.packaged.id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private BookingRepository bookingRepository;
    private RoomRepository roomRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
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

    public void add(Booking booking) {

        LocalTime start = booking.getStart();
        booking.setEnd(start.plusHours(1));
        bookingRepository.save(booking);

    }

    public void deleteById(Long id) {
        bookingRepository.deleteById(id);
    }

    public List<Booking> findByDate(LocalDate date, Room room) {

        List<Booking> bookingList = bookingRepository.findBookingsByDateAndRoom(date, room);

        if (bookingList == null) {
            bookingList = new ArrayList<>();
        }

        for (int i = 0; i <= 23; i++) {
            String time = (i) + ":00";
            if (time.length() != 5) {
                time = "0" + time;
            }

            if (!timeIsBooked(bookingList, time) || (timeIsBooked(bookingList, time) && isBookingAvailable(date, LocalTime.parse(time), room))) {
                bookingList.add(i, new Booking());
                bookingList.get(i).setId(0L);
                bookingList.get(i).setDate(date);
                bookingList.get(i).setStart(LocalTime.parse(time));

            }
        }

        return bookingList.stream()
                .limit(24)
                .collect(Collectors.toList());
    }

    public List<Integer> availablePlacesInRoom(LocalDate date, Room room) {

        List<Integer> availablePlaces = new ArrayList<>();


        for (int i = 0; i <= 23; i++) {
            String time = (i) + ":00";
            if (time.length() != 5) {
                time = "0" + time;
            }
            List<User> userList = findUsersWithTheSameReservation(date, LocalTime.parse(time), room);

            availablePlaces.add(room.getCapacity() - userList.size());
        }

        return availablePlaces;
    }

    public List<List<User>> usersInRoom(LocalDate date, Room room) {
        List<List<User>> usersInRoom = new ArrayList<>();

        for (int i = 0; i <= 23; i++) {
            String time = (i) + ":00";
            if (time.length() != 5) {
                time = "0" + time;
            }
            List<User> userList = findUsersWithTheSameReservation(date, LocalTime.parse(time), room);

            usersInRoom.add(userList);
        }
        return usersInRoom;
    }


    public boolean isExists(LocalDate date, LocalTime time, Room room) {
        return bookingRepository.existsBookingByDateAndStartAndRoom(date, time, room);
    }

    public List<Booking> findByDateAndStartAndRoom(LocalDate date, LocalTime time, Room room) {
        return bookingRepository.findBookingByDateAndStartAndRoom(date, time, room);
    }

    @Transactional
    public void mergeWithExistingAndUpdate(Booking booking) {
        Booking existingBooking = getById(booking.getId());
        LocalDate date = booking.getDate();
        LocalTime start = booking.getStart();
        Room room = booking.getRoom();
        if (!isBookingAvailable(date, start, room) || isAlreadyBookedAnyRoomAtSameTime(booking.getUser(), date, start)) {

            existingBooking.setDate(date);
            existingBooking.setStart(start);
            existingBooking.setEnd(start.plusHours(1));
        }

    }

    public void addNextHourAsNewBooking(Booking booking) {
        Booking nextHourBooking = new Booking();
        nextHourBooking.setUser(booking.getUser());
        // 23:00 to 00:00 change day
        if (booking.getStart().toString().equals("23:00")) {
            nextHourBooking.setDate(booking.getDate().plusDays(1));
        } else {
            nextHourBooking.setDate(booking.getDate());
        }
        nextHourBooking.setStart(booking.getStart().plusHours(1));
        nextHourBooking.setEnd(booking.getEnd().plusHours(1));
        nextHourBooking.setRoom(booking.getRoom());

        if (isBookingAvailable(nextHourBooking.getDate(), nextHourBooking.getStart(), nextHourBooking.getRoom()))
            bookingRepository.save(nextHourBooking);

    }

    public Booking getById(Long id) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        Booking booking = null;
        if (bookingOptional.isPresent()) {
            booking = bookingOptional.get();
        } else {
            throw new RuntimeException("Booking not found for id :: " + id);
        }
        return booking;
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

    private List<User> findUsersWithTheSameReservation(LocalDate date, LocalTime time, Room room) {
        List<Booking> bookingByDateAndStartAndRoom = bookingRepository.findBookingByDateAndStartAndRoom(date, time, room);
        List<User> userList = new ArrayList<>();
        for (Booking booking : bookingByDateAndStartAndRoom) {
            userList.add(booking.getUser());
        }
        return userList;
    }


    private boolean isBookingAvailable(LocalDate date, LocalTime time, Room room) {

        List<User> userList = new ArrayList<>();
        List<Booking> bookings = bookingRepository.findBookingByDateAndStartAndRoom(date, time, room);
        for (Booking booking : bookings) {
            User user = booking.getUser();
            userList.add(user);
        }

        if (userList.size() < room.getCapacity()) {
            return true;
        }

        return false;
    }


    public boolean isAlreadyBookedAnyRoomAtSameTime(User user, LocalDate date, LocalTime start) {

        if (bookingRepository.existsBookingByDateAndStartAndUser(date, start, user)) {
            return true;
        }
        return false;

    }
}
