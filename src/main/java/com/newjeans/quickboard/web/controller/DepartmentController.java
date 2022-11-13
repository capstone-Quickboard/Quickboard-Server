package com.newjeans.quickboard.web.controller;

import com.newjeans.quickboard.UuidService;
import com.newjeans.quickboard.config.BaseException;
import com.newjeans.quickboard.config.BaseResponse;
import com.newjeans.quickboard.service.DepartmentService;
import com.newjeans.quickboard.service.UserDepartmentService;
import com.newjeans.quickboard.service.UserService;
import com.newjeans.quickboard.web.dto.DepartmentSaveReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.newjeans.quickboard.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@RestController
public class DepartmentController {
    private final UserDepartmentService userDepartmentService;

    private final UuidService uuidService;
    private final UserService userService;

    @PostMapping("/userdepartment")
    public BaseResponse<String> saveDepartment(@RequestBody DepartmentSaveReqDto departmentReq) { //유저 전공 학과 등록
        //############departmentId가 범위밖일 경우 에러처리
        try {
            String uuid = uuidService.getUuid(); //header에서 uuid 받아옴
            if (!userService.checkUuidExist(uuid)) {//uuid가 db에 저장되어 있는지 조회
                throw new BaseException(INVALID_UUID);
            }
            Long departmentId = departmentReq.getDepartmentId();
            Long savedDepartmentId = userDepartmentService.save(uuid, departmentId);
            String result = "유저 학과 등록 완료 \n 학과코드 : " + savedDepartmentId;
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }


}

