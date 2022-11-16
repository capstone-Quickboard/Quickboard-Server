package com.newjeans.quickboard.domain.alarmDepartment;

import com.newjeans.quickboard.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmDepartmentRepository extends JpaRepository<AlarmDepartment, Long> {
    AlarmDepartment getReferenceByUserIdAndDepartmentId(Long userId, Long departmentId);
}
