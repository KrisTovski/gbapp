package com.kristovski.gbapp.user;

import java.util.List;

public interface UserService {

   public void addUserWithDefaultRole(User user);

    public List<User> findAll();

}
