package com.newjeans.quickboard.domain.notice;

import com.newjeans.quickboard.domain.department.Department;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface NoticeRepositoryCustom {

    Slice<Notice> findByDepartmentOrderByUploadDateDesc(Department department,Long lastNoticeId, Pageable pageable);
}
