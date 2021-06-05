package com.kristovski.gbapp.user;

import java.util.List;

public interface UserService {

    void addUserWithDefaultRole(User user);

    List<User> findAll();

    User getUserById(long id);

    User update(User user);
}
