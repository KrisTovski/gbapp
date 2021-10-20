package com.kristovski.gbapp.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserServiceImpl userService;
    @Mock
    UserRepository userRepository;


    @Test
    public void shouldCreateNewUser(){

       /* // given
        User user = new User();
        user.setId(1L);
        user.setLogin("MyLogin");
        user.setFirstName("Bob");
        user.setLastName("Marley");
        user.setEmail("bob@gmail.com");
        user.setPassword("pass");
        user.setEnable(true);
        user.setCreateTime(LocalDateTime.of(2021,5,10,20,25,34));
        user.setUpdateTime(null);
        user.setLocked(false);


        // when

        // then*/
    }

}