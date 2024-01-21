//package com.messenger.springtechnicservice.usersessionregistry;
//
//import com.messenger.entity.UserSession;
//import com.messenger.springtechnicrepository.usersessionrepository.UserSessionRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class UserSessionRegistryImpl implements UserSessionRegistry {
//
//    private final UserSessionRepository userSessionRepository;
//
//    @Autowired
//    public UserSessionRegistryImpl(UserSessionRepository userSessionRepository) {
//        this.userSessionRepository = userSessionRepository;
//    }
//
//    public void addSession(Integer userId, String sessionId) {
//        userSessionRepository.save(new UserSession(userId, sessionId));
//    }
//
//    public void removeSession(String sessionId) {
//        UserSession userSession = userSessionRepository.findBySessionId(sessionId);
//        if (userSession != null) {
//            userSessionRepository.delete(userSession);
//        }
//    }
//
//    public List<String> getUserSessions(Integer userId) {
//        List<UserSession> userSessions = userSessionRepository.findByUserId(userId);
//        List<String> sessionIds = new ArrayList<>();
//        for (UserSession userSession : userSessions) {
//            sessionIds.add(userSession.getSessionId());
//        }
//        return sessionIds;
//    }
//}
