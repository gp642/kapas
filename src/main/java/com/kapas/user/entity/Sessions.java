package com.kapas.user.entity;

import com.kapas.user.model.UserSession;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sessions")
public class Sessions implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Column(name = "session_id", nullable = false)
    private String sessionId;
    @Column(name = "time_stamp", nullable = false)
    private LocalDateTime timeStamp;
    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Sessions(Integer userId, UserSession userSession) {
        this.userId = userId;
        this.sessionId = userSession.getId();
        this.timeStamp = userSession.getCreatedAt();
        this.expiresAt = userSession.getExpiresAt();
        this.createdAt = userSession.getCreatedAt();
    }
}