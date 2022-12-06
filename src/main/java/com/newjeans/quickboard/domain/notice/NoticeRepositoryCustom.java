package com.newjeans.quickboard.domain.notice;

import com.newjeans.quickboard.domain.department.Department;
import com.newjeans.quickboard.domain.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface NoticeRepositoryCustom {
    /**
     * 학과 공지사항 리스트를 최신 업로드된 공지사항부터 페이징 처리해서 받아줌
     */
    Slice<Notice> findByDepartmentOrderByUploadDateDesc(Department department,Long lastNoticeId, Pageable pageable);
    /**
     * 사용자가 북마크한 공지사항 리스트를 최신 업로드된 공지사항부터 페이징 처리해서 받아줌
     */
    Slice<Notice> findBookMarkedNoticeOrderByUploadDateDesc(User user, Long lastNoticeId, Pageable pageable);
}
