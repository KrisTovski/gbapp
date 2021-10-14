package com.kristovski.gbapp.user;

import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    void addWithDefaultRole(User user);

    List<User> findAll();

    User findByEmail(String email);

    User getById(Long id);

    void mergeWithExistingAndUpdate(User user);

    void deleteById(Long id);

    Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

    User getAuthenticatedUser();

    boolean isAdmin();


}
