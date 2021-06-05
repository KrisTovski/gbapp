package com.kristovski.gbapp.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {

    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private boolean enable;
    private boolean locked;

}
