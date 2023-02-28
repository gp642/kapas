package com.kapas.user.service;

import com.kapas.user.entity.Sessions;
import com.kapas.user.model.UserSession;
import com.kapas.user.repository.SessionsRepository;
import com.kapas.util.EncryptionUtil;
import com.kapas.util.AppUtils;
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

    private final SessionsRepository sessionOfUserRepository;

    public SessionManagerService(SessionsRepository sessionOfUserRepository) {
        this.sessionOfUserRepository = sessionOfUserRepository;
    }


    private static String encryptUserSession(String sessionSalt) throws Exception {
        return EncryptionUtil.encrypt(sessionSalt);
    }

    private static String createSessionSalt(String userId) {
        return userId + SALT_SPLITTER + AppUtils.generateRandomNumber(1, 20) + System.currentTimeMillis();
    }

    @Transactional
    public UserSession createSession(Integer userId) throws Exception {

        final LocalDateTime currentTime = LocalDateTime.now();

        String sessionSalt = createSessionSalt(userId.toString());
        String sessionId = encryptUserSession(sessionSalt);

        UserSession userSession = new UserSession(sessionId, currentTime.plusSeconds(EXPIRES_AT_WEBAPP), currentTime);
        Sessions sessionsOfUser = new Sessions(userId, userSession);
        sessionOfUserRepository.save(sessionsOfUser);
        return userSession;
    }
}
