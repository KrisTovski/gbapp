package com.kristovski.gbapp.booking;

import com.kristovski.gbapp.room.Room;
import com.kristovski.gbapp.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
@Transactional
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("from Booking b where not(b.end < :from or b.start > :to)")
    public List<Booking> findBetween(@Param("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalTime start,
                                     @Param("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalTime end);

    Page<Booking> findAllByUserId(Long id, Pageable pageable);

    @Query("from Booking b where (b.date = :date) and (b.room = :room)")
    public List<Booking> findBookingsByDateAndRoom(@Param("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                                   @Param("room") Room room);

    public boolean existsBookingByDateAndStartAndUser(LocalDate date, LocalTime time, User user);

    public boolean existsBookingByDateAndStartAndRoom(LocalDate date, LocalTime time, Room room);

    public List<Booking> findBookingByDateAndStartAndRoom(LocalDate date, LocalTime time, Room room);


}
