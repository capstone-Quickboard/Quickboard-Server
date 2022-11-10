package com.newjeans.quickboard.web.controller;


import com.newjeans.quickboard.UuidService;
import com.newjeans.quickboard.config.BaseException;
import com.newjeans.quickboard.config.BaseResponse;
import com.newjeans.quickboard.service.NoticeService;
import com.newjeans.quickboard.service.UserService;
import com.newjeans.quickboard.web.dto.NoticeListResDto;
import com.newjeans.quickboard.web.dto.NoticeResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.newjeans.quickboard.config.BaseResponseStatus.ALREADY_EXIST_USER;
import static com.newjeans.quickboard.config.BaseResponseStatus.NOT_FOUND_USER;

@RequiredArgsConstructor
@RestController
public class NoticeController {

    private final NoticeService noticeService;
    private final UuidService uuidService;
    private final UserService userService;

    @GetMapping("/")
    public String connectedTest(){
        return "연결됨";
    }

    @GetMapping("/notice/{noticeId}")
    public BaseResponse<NoticeResDto> findByNoticeId(@PathVariable("noticeId") Long noticeId){
        try{
            return new BaseResponse<>(noticeService.findByNoticeId(noticeId));
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @GetMapping("/notice/list")
    public BaseResponse<List<NoticeListResDto>> findAllByDepartment(@RequestParam Long departmentId){
        try{
            String uuid = uuidService.getUuid(); //Header에서 uuid 받아옴
            if(!userService.checkUuidExist(uuid))
                throw new BaseException(NOT_FOUND_USER);
            return new BaseResponse<>(noticeService.findAllByDepartmentId(uuid,departmentId));
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
