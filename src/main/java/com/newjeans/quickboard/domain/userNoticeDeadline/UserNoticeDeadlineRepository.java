package com.newjeans.quickboard.domain.userNoticeDeadline;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserNoticeDeadlineRepository extends JpaRepository<UserNoticeDeadline, Long> {
    UserNoticeDeadline findByUserIdAndNoticeId(Long userId, Long noticeId);
    boolean existsByUserIdAndNoticeId(Long userId, Long noticeId);
}
