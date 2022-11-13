package com.newjeans.quickboard.domain.userDepartment;

import com.newjeans.quickboard.domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDepartmentRepository extends JpaRepository<UserDepartment, Long> {
    UserDepartment getReferenceByUser(User user);
}
