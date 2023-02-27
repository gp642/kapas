package com.kapas.user.model;

import com.kapas.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CroppedUser {

    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private boolean isVerified;

    public CroppedUser(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.mobile = user.getMobile();
    }
}
