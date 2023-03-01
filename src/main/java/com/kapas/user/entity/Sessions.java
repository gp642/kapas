package com.kapas.user.entity;

import com.kapas.user.model.UserSession;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sessions")
public class Sessions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(name = "session_id", nullable = false)
    private String sessionId;
    @Column(name = "time_stamp", nullable = false)
    private LocalDateTime timeStamp;
    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Sessions(User user, UserSession userSession) {
        this.user = user;
        this.sessionId = userSession.getId();
        this.timeStamp = userSession.getCreatedAt();
        this.expiresAt = userSession.getExpiresAt();
        this.createdAt = userSession.getCreatedAt();
    }
}