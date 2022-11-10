package com.newjeans.quickboard.web.controller;

import com.newjeans.quickboard.UuidService;
import com.newjeans.quickboard.config.BaseException;
import com.newjeans.quickboard.config.BaseResponse;
import com.newjeans.quickboard.service.UserService;
import com.newjeans.quickboard.web.dto.BookmarkReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.newjeans.quickboard.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final UuidService uuidService;

    @PostMapping("/user")
    public BaseResponse<String> saveUser(){
        try{
            String uuid = uuidService.getUuid(); //Header에서 uuid 받아옴
            if(userService.checkUuidExist(uuid))
                throw new BaseException(ALREADY_EXIST_USER);
            userService.save(uuid);
            return new BaseResponse<>("유저 등록 성공");
        }catch(BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    //북마크 등록
    @PostMapping("/bookmark")
    public BaseResponse<String> saveBookMark(@RequestBody BookmarkReqDto bookmarkReqDto){
        try{
            String uuid = uuidService.getUuid();//Header에서 uuid 받아옴
            if(!userService.checkUuidExist(uuid)) //uuid가 db에 저장되어 있는지 조회
                throw new BaseException(NOT_FOUND_USER);
            Long noticeId = bookmarkReqDto.getNoticeId();
            Long savedNoticeId = userService.saveBookmark(uuid ,noticeId);
            String result = "공지사항 북마크 등록 성공 noticeId = "+savedNoticeId;
            return new BaseResponse<>(result);
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    //북마크 삭제
    @DeleteMapping("/bookmark/{noticeId}")
    public BaseResponse<String> deleteBookMark(@PathVariable("noticeId") Long noticeId){
        try{
            String uuid = uuidService.getUuid();//Header에서 uuid 받아옴
            if(!userService.checkUuidExist(uuid)) //uuid가 db에 저장되어 있는지 조회
                throw new BaseException(NOT_FOUND_USER);
            Long deletedNoticeId = userService.deleteBookmark(uuid ,noticeId);
            String result = "공지사항 북마크 삭제 성공 noticeId = "+deletedNoticeId;
            return new BaseResponse<>(result);
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }


}
