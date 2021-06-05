package com.kristovski.gbapp.user;

import java.util.List;

public interface UserService {

    void addUserWithDefaultRole(User user);

    List<User> findAll();

    User getUserById(Long id);

    void updateUser(UserUpdateDto userUpdateDto);

    void mergeWithExistingAndUpdate(User user);
}
