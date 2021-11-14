package com.kristovski.gbapp.api.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {

    private Long id;
    @NotEmpty
    private String login;
    private String firstName;
    private String lastName;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String password;
    private boolean enable;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private boolean locked;
    @JsonProperty("user_url")
    private String userUrl;
}
