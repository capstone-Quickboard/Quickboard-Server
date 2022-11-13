package com.newjeans.quickboard.domain.userNoticeDeadline;

import com.newjeans.quickboard.domain.Bookmark.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserNoticeDeadlineRepository extends JpaRepository<UserNoticeDeadline, Long> {
    UserNoticeDeadline findByUserIdAndNoticeId(Long userId, Long noticeId);
    boolean existsByUserIdAndNoticeId(Long userId, Long noticeId);
}
