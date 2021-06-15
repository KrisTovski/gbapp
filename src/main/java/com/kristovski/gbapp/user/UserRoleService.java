package com.kristovski.gbapp.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {

    UserRoleRepository userRoleRepository;

    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }
}
