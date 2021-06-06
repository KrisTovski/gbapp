package com.kristovski.gbapp.user;

import java.util.List;

public interface UserService {

    void addUserWithDefaultRole(User user);

    List<User> findAll();

    User getUserById(Long id);

    void mergeWithExistingAndUpdate(User user);

    void deleteUserById(Long id);
}
