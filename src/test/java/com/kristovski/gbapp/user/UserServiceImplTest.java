package com.kristovski.gbapp.user;

import liquibase.pro.packaged.B;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityNotFoundException;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private static final Long ID = 1L;
    private static final String LOGIN = "Login";
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Smith";
    private static final String PASSWORD = "pass";
    private static final String EMAIL = "test@email.com";
    private static final String DEFAULT_ROLE = "ROLE_USER";
    static final Clock fixed = Clock.fixed(Instant.parse("2021-10-22T10:00:00.000Z"), ZoneId.systemDefault());
    static final LocalDateTime DATE_TIME = LocalDateTime.now(fixed);

    @Mock
    private UserRepository userRepository;
    @Mock
    private Clock clock;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    UserRoleRepository userRoleRepository;

    @InjectMocks
    private UserServiceImpl userService;

    User user;

    @BeforeEach
    void setUp() {
        Set<UserRole> roles = new HashSet<>();
        for (UserRole role : roles) {
            role.setId(ID);
            role.setRole(DEFAULT_ROLE);
            role.setDescription(null);
        }
        user = User.builder()
                .id(ID)
                .login(LOGIN)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(EMAIL)
                .password(PASSWORD)
                .enable(true)
                .createTime(DATE_TIME)
                .updateTime(null)
                .locked(false)
                .roles(roles)
                .build();
    }

    @Test
    void shouldReturnUserWithGivenEmail() {
        // given
        when(userRepository.findByEmail(EMAIL)).thenReturn(user);
        // when
        User result = userService.findByEmail(EMAIL);
        // then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(user);
    }

    @Test
    void shouldReturnUserWithGivenId() {
        // given
        when(userRepository.findById(ID)).thenReturn(Optional.of(user));
        // when
        User result = userService.getById(ID);
        // then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(user);
    }

    @Test
    void shouldThrowExceptionIfUserWithGivenIdNotFound() {
        // when
        assertThatThrownBy(() -> userService.getById(ID))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("User not found for id: " + ID);
    }

    @Test
    void shouldDeleteUserById() {
        // given
        Long idToDelete = 2L;
        // when
        userService.deleteById(idToDelete);
        // then
        verify(userRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void shouldReturnAllUsers() {
        // given
        List<User> users = new ArrayList<>();
        users.add(User.builder().id(1L).build());
        users.add(User.builder().id(2L).build());
        users.add(User.builder().id(3L).build());
        when(userRepository.findAll()).thenReturn(users);
        // when
        List<User> result = userService.findAll();
        // then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(3);
    }

    @Test
    void shouldCreateUserWithDefaultRole() {
        //given
        when(clock.instant()).thenReturn(fixed.instant());
        when(clock.getZone()).thenReturn(fixed.getZone());
        when(passwordEncoder.encode(PASSWORD)).thenReturn(PASSWORD);
        // when
        userService.addWithDefaultRole(user);
        // then
        assertThat(user.getId()).isEqualTo(ID);
        assertThat(user.getLogin()).isEqualTo(LOGIN);
        assertThat(user.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(user.getLastName()).isEqualTo(LAST_NAME);
        assertThat(user.getEmail()).isEqualTo(EMAIL);
        assertThat(user.getPassword()).isEqualTo(PASSWORD);
        assertThat(user.isEnable()).isFalse();
        assertThat(user.getCreateTime()).isEqualTo(DATE_TIME);
        assertThat(user.getUpdateTime()).isNull();
        assertThat(user.isLocked()).isFalse();
    }

}