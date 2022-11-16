package com.newjeans.quickboard.web.controller;

import com.newjeans.quickboard.UuidService;
import com.newjeans.quickboard.config.BaseException;
import com.newjeans.quickboard.config.BaseResponse;
import com.newjeans.quickboard.service.DepartmentService;
import com.newjeans.quickboard.service.UserService;
import com.newjeans.quickboard.web.dto.DepartmentSaveReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.newjeans.quickboard.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@RestController
public class DepartmentController {
    private final DepartmentService departmentService;
    private final UuidService uuidService;
    private final UserService userService;

    @PatchMapping("/userdepartment")
    public BaseResponse<String> updateDepartment(@RequestBody DepartmentSaveReqDto departmentReq) { //유저 전공 학과 등록
        try {
            String uuid = uuidService.getUuid(); //header에서 uuid 받아옴
            if (!userService.checkUuidExist(uuid)) {//uuid가 db에 저장되어 있는지 조회
                throw new BaseException(INVALID_UUID);
            }
            Long departmentId = departmentReq.getDepartmentId();
            Long savedDepartmentId = departmentService.updateUserDepartment(uuid, departmentId);
            String result = "유저 학과 등록 완료 \n 학과코드 : " + savedDepartmentId;
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @PostMapping("alarmdepartment") //알람 등록 (제공처 코드 1~10만)
    public BaseResponse<String> saveAlarmDepartment(@RequestBody DepartmentSaveReqDto departmentReq) { //유저 전공 학과 등록
        try {
            String uuid = uuidService.getUuid(); //header에서 uuid 받아옴
            if (!userService.checkUuidExist(uuid)) {//uuid가 db에 저장되어 있는지 조회
                throw new BaseException(INVALID_UUID);
            }
            Long departmentId = departmentReq.getDepartmentId();
            Long savedDepartmentId = departmentService.saveAlarmDepartment(uuid, departmentId);
            String result = "알람 등록 완료 \n 제공처(학과) 코드 : " + savedDepartmentId;
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @DeleteMapping("alarmdepartment/{departmentId}") // 알람 취소 (제공처 코드 1~10만)
    public BaseResponse<String> deleteAlarmDepartment(@PathVariable Long departmentId){
        try{
            String uuid = uuidService.getUuid(); //header에서 uuid 받아옴
            if (!userService.checkUuidExist(uuid))//uuid가 db에 저장되어 있는지 조회
                throw new BaseException(INVALID_UUID);
            Long deletedDepartmentId = departmentService.deleteAlarmDepartment(uuid, departmentId);
            String result = "알람 취소 성공 \n 제공처(학과) 코드 : "+deletedDepartmentId;
            return new BaseResponse<>(result);

        }catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @PostMapping("alarmdepartment/mymajor") // 학과(유저전공) 알림 등록
    public BaseResponse<String> saveAlarmMyMajor() {
        try {
            String uuid = uuidService.getUuid(); //header에서 uuid 받아옴
            if (!userService.checkUuidExist(uuid)) {//uuid가 db에 저장되어 있는지 조회
                throw new BaseException(INVALID_UUID);
            }
            Long savedDepartmentId = departmentService.saveAlarmMyMajor(uuid);
            String result = "알람 등록 완료 \n 제공처(학과) 코드 : " + savedDepartmentId;
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @DeleteMapping("alarmdepartment/mymajor") // 학과(유저전공) 알림 취소
    public BaseResponse<String> deleteAlarmMyMajor(){
        try{
            String uuid = uuidService.getUuid(); //header에서 uuid 받아옴
            if (!userService.checkUuidExist(uuid))//uuid가 db에 저장되어 있는지 조회
                throw new BaseException(INVALID_UUID);
            Long deletedDepartmentId = departmentService.deleteAlarmMyMajor(uuid);
            String result = "알람 취소 성공 \n 제공처(학과) 코드 : "+deletedDepartmentId;
            return new BaseResponse<>(result);

        }catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }


}

