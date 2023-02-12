package com.example.kapas.model;

import com.example.kapas.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoggedInUser {
    private CroppedUser user;
    private UserSession session;

    public LoggedInUser(User user, UserSession session) {
        this.user = new CroppedUser(user);
        this.session = session;
    }
}
