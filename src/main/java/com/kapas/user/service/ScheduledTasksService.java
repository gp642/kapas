package com.kapas.user.service;

import com.kapas.user.repository.SessionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ScheduledTasksService {

    private static final String EVERY_MIDNIGHT = "0 0 0 * * *";
    private static final String EVERY_MINUTE = "* * * * * *";
    private static final String IST_ZONE = "GMT+05:30";
    private final SessionsRepository sessionOfUserRepository;


    @Scheduled(cron = EVERY_MINUTE, zone = IST_ZONE)
    @Transactional(rollbackFor = Throwable.class)
    public void deleteExpiredUserSessions() {
        sessionOfUserRepository.deleteAllExpiredSessions();
    }
}
