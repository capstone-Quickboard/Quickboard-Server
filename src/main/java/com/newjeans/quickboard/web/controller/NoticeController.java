package com.newjeans.quickboard.web.controller;


import com.newjeans.quickboard.UuidService;
import com.newjeans.quickboard.config.BaseException;
import com.newjeans.quickboard.config.BaseResponse;
import com.newjeans.quickboard.domain.notice.Notice;
import com.newjeans.quickboard.service.NoticeService;
import com.newjeans.quickboard.service.UserService;
import com.newjeans.quickboard.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            String uuid = uuidService.getUuid(); //Header에서 uuid 받아옴
            if(!userService.checkUuidExist(uuid))
                throw new BaseException(NOT_FOUND_USER);
            return new BaseResponse<>(noticeService.findByNoticeId(uuid,noticeId));
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }
    /**
    * departmentId로 학과 공지사항 리스트 조회
     * paging 처리
    **/
    @GetMapping("/notice/list") // /notice/list?departmentId=
    public BaseResponse<SliceResDto<NoticeListResDto>> findAllByDepartment(@RequestBody NoticeListReqDto noticeListReqDto, @RequestParam Long departmentId, Pageable pageable){
        try{
            String uuid = uuidService.getUuid(); //Header에서 uuid 받아옴
            if(!userService.checkUuidExist(uuid))
                throw new BaseException(NOT_FOUND_USER);
            return new BaseResponse<>(noticeService.findAllByDepartmentId(uuid,departmentId,noticeListReqDto, pageable));
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @GetMapping("/notice/bookmark")
    public BaseResponse<List<BookmarkedNoticeListResDto>> findAllByBookmarked(){
        try{
            String uuid = uuidService.getUuid(); //Header에서 uuid 받아옴
            if(!userService.checkUuidExist(uuid))
                throw new BaseException(NOT_FOUND_USER);
            return new BaseResponse<>(noticeService.findAllByBookmarked(uuid));
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
