package com.kapas.user.service;

import com.kapas.user.repository.SessionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ScheduledTasksService {

    private static final String EVERY_MIDNIGHT = "0 0 0 * * *";
    private static final String EVERY_MINUTE = "* * * * * *";
    private static final String IST_ZONE = "GMT+05:30";
    private final SessionsRepository sessionOfUserRepository;


    @Scheduled(cron = EVERY_MIDNIGHT, zone = IST_ZONE)
    public void deleteExpiredUserSessions() {
        sessionOfUserRepository.deleteAllExpiredSessions();
    }
}
