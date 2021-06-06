package com.kristovski.gbapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final String DEFAULT_ROLE = "ROLE_USER";
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserRoleRepository userRoleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addUserWithDefaultRole(User user) {
        UserRole defaultRole = userRoleRepository.findByRole(DEFAULT_ROLE);
        user.getRoles().add(defaultRole);
        String passwordHash = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordHash);
        user.setEnable(false);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(null);
        user.setLocked(false);
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        User user = null;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            throw new RuntimeException("User not found for id :: " + id);
        }
        return user;
    }

    @Transactional
    @Override
    public void mergeWithExistingAndUpdate(User user) {
        User existingUser = getUserById(user.getId());
        existingUser.setLogin(user.getLogin());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setEnable(user.isEnable());
        existingUser.setUpdateTime(LocalDateTime.now());
        existingUser.setLocked(user.isLocked());

    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }


}
