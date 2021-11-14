package com.kristovski.gbapp.user;

import com.kristovski.gbapp.security.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final String DEFAULT_ROLE = "ROLE_USER";
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private PasswordEncoder passwordEncoder;
    private IAuthenticationFacade authenticationFacade;
    private Clock clock;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository,
                           PasswordEncoder passwordEncoder, IAuthenticationFacade authenticationFacade, Clock clock) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationFacade = authenticationFacade;
        this.clock = clock;
    }

    @Override
    public boolean addWithDefaultRole(User user) {
        try {
            UserRole defaultRole = userRoleRepository.findByRole(DEFAULT_ROLE);
            user.getRoles().add(defaultRole);
            String passwordHash = passwordEncoder.encode(user.getPassword());
            user.setPassword(passwordHash);
            user.setEnable(true);
            user.setCreateTime(LocalDateTime.now(clock));
            user.setUpdateTime(null);
            user.setLocked(false);
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User not found for id: %s", id)));
    }

    @Transactional
    @Override
    public void mergeWithExistingAndUpdate(User user) {
        User existingUser = getById(user.getId());
        existingUser.setLogin(user.getLogin());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setEnable(user.isEnable());
        existingUser.setUpdateTime(LocalDateTime.now(clock));
        existingUser.setLocked(user.isLocked());

    }

    @Transactional
    @Override
    public void updateName(User user) {
        User existingUser = getById(user.getId());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setUpdateTime(LocalDateTime.now());
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return userRepository.findAll(pageable);
    }

    @Override
    public User getAuthenticatedUser() {
        Authentication loggedInUser = authenticationFacade.getAuthentication();
        String userEmail = loggedInUser.getName();
        User authenticatedUser = findByEmail(userEmail);
        return authenticatedUser;
    }

    @Override
    public boolean isAdmin() {
        Authentication loggedInUser = authenticationFacade.getAuthentication();
        if (loggedInUser != null && loggedInUser.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return true;
        }
        return false;
    }

    public void disable(User user) {
        User existingUser = getById(user.getId());
        userRepository.setEnable(existingUser.getId(), false);

    }

    @Override
    public User patch(Long id, User user) {
        return userRepository.findById(id).map(u -> {
            if (user.getLogin() != null) {
                u.setLogin(user.getLogin());
            }
            if (user.getFirstName() != null) {
                u.setFirstName(user.getFirstName());
            }
            if (user.getLastName() != null) {
                u.setLastName(user.getLastName());
            }
            if (user.getEmail() != null) {
                u.setEmail(user.getEmail());
            }
            u.setEnable(user.isEnable());
            u.setUpdateTime(LocalDateTime.now());
            u.setLocked(user.isLocked());

            User savedUser = userRepository.save(u);

            return savedUser;

        }).orElseThrow(ResourceNotFoundException::new);
    }
}
