//package com.messenger.springtechnicrepository.usersessionrepository;
//
//import com.messenger.entity.UserSession;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Repository
//@Transactional(readOnly = true)
//public interface UserSessionRepository extends JpaRepository<UserSession, Integer> {
//
//    UserSession findBySessionId(String sessionId);
//
//    List<UserSession> findByUserId(Integer userId);
//}
