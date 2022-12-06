package com.newjeans.quickboard.domain.bookmark;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Bookmark getReferenceByUserIdAndNoticeId(Long userId, Long noticeId);
    boolean existsByUserIdAndNoticeId(Long userId, Long noticeId);
}
