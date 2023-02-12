package com.example.kapas.service;

import com.example.kapas.repository.SessionOfUserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScheduledTasksService {

    private static final String EVERY_MIDNIGHT = "0 0 0 * * *";
    private static final String IST_ZONE = "GMT+05:30";
    private final SessionOfUserRepository sessionOfUserRepository;

    public ScheduledTasksService(SessionOfUserRepository sessionOfUserRepository) {
        this.sessionOfUserRepository = sessionOfUserRepository;
    }


    @Scheduled(cron = EVERY_MIDNIGHT, zone = IST_ZONE)
    @Transactional(rollbackFor = Throwable.class)
    public void deleteExpiredUserSessions() {
        sessionOfUserRepository.deleteAllExpiredSessions();
    }
}
