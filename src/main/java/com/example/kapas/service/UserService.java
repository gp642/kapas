package com.example.kapas.service;

import com.example.kapas.entity.User;
import com.example.kapas.model.LoggedInUser;
import com.example.kapas.model.Login;
import com.example.kapas.model.UserSession;
import com.example.kapas.repository.UserRepository;
import com.example.kapas.util.EncryptionUtil;
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
