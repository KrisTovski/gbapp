package com.kristovski.gbapp.user;

import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    void addUserWithDefaultRole(User user);

    List<User> findAll();

    User getUserById(Long id);

    void mergeWithExistingAndUpdate(User user);

    void deleteUserById(Long id);

    Page<User> findPaginated(int pageNo, int pageSize);
}
