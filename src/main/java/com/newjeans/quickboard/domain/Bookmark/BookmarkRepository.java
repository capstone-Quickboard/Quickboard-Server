package com.newjeans.quickboard.domain.Bookmark;

import com.newjeans.quickboard.domain.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
}
