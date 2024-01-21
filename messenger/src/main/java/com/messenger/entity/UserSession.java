package com.messenger.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user_session")
@NoArgsConstructor
public class UserSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "session_id")
    private String sessionId;

    public UserSession(Integer userId, String sessionId) {
        this.userId = userId;
        this.sessionId = sessionId;
    }
}
