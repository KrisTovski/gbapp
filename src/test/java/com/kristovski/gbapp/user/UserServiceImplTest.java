package com.kristovski.gbapp.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private static final long ID = 1L;
    private static final String EMAIL = "test@email.com";
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void shouldReturnUserWithGivenEmail() {
        // given
        User user = User.builder().email(EMAIL).build();
        when(userRepository.findByEmail(anyString())).thenReturn(user);
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
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
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

}