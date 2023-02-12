package com.example.kapas.service;

import com.example.kapas.entity.SessionsOfUser;
import com.example.kapas.model.UserSession;
import com.example.kapas.repository.SessionOfUserRepository;
import com.example.kapas.util.EncryptionUtil;
import com.example.kapas.util.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class SessionManagerService {

    private static final Logger LOGGER = LogManager.getLogger(SessionManagerService.class);
    private static final String SALT_SPLITTER = "~";
    private static final int EXPIRES_AT_WEBAPP = 60 * 60 * 24 * 2; // 2 days

    private final SessionOfUserRepository sessionOfUserRepository;

    public SessionManagerService(SessionOfUserRepository sessionOfUserRepository) {
        this.sessionOfUserRepository = sessionOfUserRepository;
    }


    private static String encryptUserSession(String sessionSalt) throws Exception {
        return EncryptionUtil.encrypt(sessionSalt);
    }

    private static String createSessionSalt(String userId) {
        return userId + SALT_SPLITTER + Utility.generateRandomNumber(1, 20) + System.currentTimeMillis();
    }

    @Transactional
    public UserSession createSession(Integer userId) throws Exception {

        final LocalDateTime currentTime = LocalDateTime.now();

        String sessionSalt = createSessionSalt(userId.toString());
        String sessionId = encryptUserSession(sessionSalt);

        LOGGER.debug(" ================================= SESSION ID  " + sessionId);

        UserSession userSession = new UserSession(sessionId, currentTime.plusSeconds(EXPIRES_AT_WEBAPP), currentTime);
        SessionsOfUser sessionsOfUser = new SessionsOfUser(userId, userSession);
        sessionOfUserRepository.save(sessionsOfUser);
        return userSession;
    }
}
