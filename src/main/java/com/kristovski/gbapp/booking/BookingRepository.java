package com.kristovski.gbapp.booking;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("from Booking b where not(b.end < :from or b.start > :to)")
    public List<Booking> findBetween(@Param("from") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                     @Param("to") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) LocalDateTime end);

    Page<Booking> findAllByUserId(Long id, Pageable pageable);

}
