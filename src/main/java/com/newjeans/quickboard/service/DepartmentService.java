package com.newjeans.quickboard.service;

import com.newjeans.quickboard.config.BaseException;
import com.newjeans.quickboard.domain.alarmDepartment.AlarmDepartment;
import com.newjeans.quickboard.domain.alarmDepartment.AlarmDepartmentRepository;
import com.newjeans.quickboard.domain.user.User;
import com.newjeans.quickboard.domain.user.UserRepository;
import com.newjeans.quickboard.domain.department.Department;
import com.newjeans.quickboard.domain.department.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.newjeans.quickboard.config.BaseResponseStatus.DATABASE_ERROR;
import static com.newjeans.quickboard.config.BaseResponseStatus.DEPARTMENT_ID_RANGE_ERROR;

@RequiredArgsConstructor
@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final AlarmDepartmentRepository alarmDepartmentRepository;

    @Transactional
    public Long updateUserDepartment(String uuid,Long departmentId) throws BaseException {
        if(!(departmentId>10)) //10이하 학과코드는 없음
                throw new BaseException(DEPARTMENT_ID_RANGE_ERROR);
        try{
            User user = userRepository.getReferenceByUuid(uuid);
            Department department = departmentRepository.getReferenceById(departmentId);
            user.departmentUpdate(department);
            return user.getDepartment().getId();
        }catch (Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }
    @Transactional
    public Long saveAlarmDepartment(String uuid,Long departmentId) throws BaseException {
        if(!(departmentId<=10)) //10이하 코드(유세인트, 기숙사, 총학 등등만 등록가능)
            throw new BaseException(DEPARTMENT_ID_RANGE_ERROR);
        try{
            User user = userRepository.getReferenceByUuid(uuid);
            Department department = departmentRepository.getReferenceById(departmentId);
            AlarmDepartment alarmDepartment = AlarmDepartment.builder().user(user).department(department).build();
            return alarmDepartmentRepository.save(alarmDepartment).getDepartment().getId();
        }catch (Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional
    public Long deleteAlarmDepartment(String uuid,Long departmentId) throws BaseException {
        if(!(departmentId<=10)) //10이하 코드(유세인트, 기숙사, 총학 등등만 등록가능)
            throw new BaseException(DEPARTMENT_ID_RANGE_ERROR);
        try{
            User user = userRepository.getReferenceByUuid(uuid);
            AlarmDepartment alarmDepartment = alarmDepartmentRepository.getReferenceByUserIdAndDepartmentId(user.getId(),departmentId);
            alarmDepartmentRepository.delete(alarmDepartment);
            return alarmDepartment.getDepartment().getId();
        }catch (Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional
    public Long saveAlarmMyMajor(String uuid) throws BaseException {
        try{
            User user = userRepository.getReferenceByUuid(uuid);
            AlarmDepartment alarmDepartment = AlarmDepartment.builder().user(user).department(user.getDepartment()).build();
            return alarmDepartmentRepository.save(alarmDepartment).getDepartment().getId();
        }catch(Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional
    public Long deleteAlarmMyMajor(String uuid) throws BaseException {
        try{
            User user = userRepository.getReferenceByUuid(uuid);
            AlarmDepartment alarmDepartment = alarmDepartmentRepository.getReferenceByUserIdAndDepartmentId(user.getId(),user.getDepartment().getId());
            alarmDepartmentRepository.delete(alarmDepartment);
            return alarmDepartment.getDepartment().getId();
        }catch (Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
