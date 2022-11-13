package com.newjeans.quickboard.service;

import com.newjeans.quickboard.config.BaseException;
import com.newjeans.quickboard.domain.User.User;
import com.newjeans.quickboard.domain.User.UserRepository;
import com.newjeans.quickboard.domain.department.Department;
import com.newjeans.quickboard.domain.department.DepartmentRepository;
import com.newjeans.quickboard.domain.userDepartment.UserDepartment;
import com.newjeans.quickboard.domain.userDepartment.UserDepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.newjeans.quickboard.config.BaseResponseStatus.DATABASE_ERROR;

@RequiredArgsConstructor
@Service
public class UserDepartmentService {
    private final UserDepartmentRepository userDepartmentRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long save(String uuid,Long departmentId) throws BaseException {
        try {
            User user = userRepository.getReferenceByUuid(uuid);
            Department department = departmentRepository.getReferenceById(departmentId);
            UserDepartment userDepartment = UserDepartment.builder().user(user).department(department).build();

            return userDepartmentRepository.save(userDepartment).getDepartment().getId(); // departmentId 리턴
        }catch (Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
