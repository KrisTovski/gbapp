package com.kristovski.gbapp.user;

import com.kristovski.gbapp.Room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);




}
