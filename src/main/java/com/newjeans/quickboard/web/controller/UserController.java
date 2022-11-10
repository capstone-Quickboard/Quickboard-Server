package com.newjeans.quickboard.web.controller;

import com.newjeans.quickboard.UuidService;
import com.newjeans.quickboard.config.BaseException;
import com.newjeans.quickboard.config.BaseResponse;
import com.newjeans.quickboard.service.UserService;
import com.newjeans.quickboard.web.dto.BookmarkSaveReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("/bookmark")
    public BaseResponse<String> saveBookMark(@RequestBody BookmarkSaveReqDto bookmarkSaveReqDto){
        try{
            String uuid = uuidService.getUuid();//Header에서 uuid 받아옴
            if(!userService.checkUuidExist(uuid)) //uuid가 db에 저장되어 있는지 조회
                throw new BaseException(NOT_FOUND_USER);
            Long noticeId = bookmarkSaveReqDto.getNoticeId();
            Long savedNoticeId = userService.saveBookmark(uuid ,noticeId);
            String result = "공지사항 북마크 성공 noticeId = "+savedNoticeId;
            return new BaseResponse<>(result);
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

}
