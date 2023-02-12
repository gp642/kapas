package com.example.kapas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSession {

    private String id;
    private LocalDateTime expiresAt;
    private LocalDateTime createdAt;
}
