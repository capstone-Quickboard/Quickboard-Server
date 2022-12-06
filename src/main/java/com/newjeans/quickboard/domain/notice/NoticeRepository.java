package com.newjeans.quickboard.domain.notice;

import com.newjeans.quickboard.domain.department.Department;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import java.util.List;


public interface NoticeRepository extends JpaRepository<Notice, Long>, NoticeRepositoryCustom{

    /**
     * 학과 공지사항 리스트를 최신 업로드된 공지사항부터 페이징 처리해서 받아줌
     */
    Slice<Notice> findByDepartmentOrderByUploadDateDesc(Department department, Pageable pageable);


}

