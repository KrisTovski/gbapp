package com.kristovski.gbapp.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityNotFoundException;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private static final long ID = 1L;
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

    @Test
    void shouldReturnUserWithGivenEmail() {
        // given
        User user = User.builder().email(EMAIL).build();
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
        User user = User.builder().id(ID).build();
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

    /*@Test
    void shouldCreateUserWithDefaultRole() {
        // given
        Set<UserRole> defaultRole = new HashSet<>();
        User user = User.builder()
                .id(1L)
                .login("Login")
                .firstName("Jan")
                .lastName("Kowalski")
                .email("jan@gmail.com")
                .password("pass").build();
        when(clock.instant()).thenReturn(fixed.instant());
        when(clock.getZone()).thenReturn(fixed.getZone());
        when(user.getRoles()).thenReturn(defaultRole);
        when(passwordEncoder.encode(user.getPassword())).thenReturn("pass");

        // when
        userService.addWithDefaultRole(user);
        // then
        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getLogin()).isEqualTo("Login");
        assertThat(user.getFirstName()).isEqualTo("Jan");
        assertThat(user.getLastName()).isEqualTo("Kowalski");
        assertThat(user.getEmail()).isEqualTo("jan@gmail.com");
        assertThat(user.getPassword()).isEqualTo("pass");
        assertThat(user.isEnable()).isFalse();
        assertThat(user.getCreateTime()).isEqualTo(DATE_TIME);
        assertThat(user.getUpdateTime()).isNull();
        assertThat(user.isLocked()).isFalse();
        verify(userRepository.save(user));
    }*/
}