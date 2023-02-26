package com.kapas.user.service;

import com.kapas.user.entity.User;
import com.kapas.user.model.LoggedInUser;
import com.kapas.user.model.Login;
import com.kapas.user.model.UserSession;
import com.kapas.user.repository.UserRepository;
import com.kapas.util.EncryptionUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final SessionManagerService sessionManagerService;

    public UserService(UserRepository userRepository, SessionManagerService sessionManagerService) {
        this.userRepository = userRepository;
        this.sessionManagerService = sessionManagerService;
    }

    public LoggedInUser loginWithPassword(Login login) throws Exception {
        Optional<User> optionalUser = userRepository.findUserByUserName(login.getUserName());
        User user = optionalUser.orElseThrow(() -> new Exception("Login failed. Provided data may not be correct."));
        String encryptedPassword = EncryptionUtil.encrypt(login.getPassword());
        if (encryptedPassword.equals(user.getPassword())) {
            UserSession userSession = sessionManagerService.createSession(user.getId());
            return new LoggedInUser(user, userSession);
        } else {
            throw new Exception("The password was incorrect.");
        }
    }
}
