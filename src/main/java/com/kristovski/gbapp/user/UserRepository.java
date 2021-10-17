package com.kristovski.gbapp.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Modifying
    @Query("update User u set u.enable = :enable where u.id = :userId")
    void setEnable(Long userId, boolean enable);
}
