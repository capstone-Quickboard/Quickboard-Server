package com.newjeans.quickboard.web.controller;

import com.newjeans.quickboard.UuidService;
import com.newjeans.quickboard.config.BaseException;
import com.newjeans.quickboard.config.BaseResponse;
import com.newjeans.quickboard.service.KeywordService;
import com.newjeans.quickboard.service.UserService;
import com.newjeans.quickboard.web.dto.KeywordSubscribeReqDto;
import com.newjeans.quickboard.web.dto.KeywordSubscribeResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.newjeans.quickboard.config.BaseResponseStatus.NOT_FOUND_USER;

@RestController
@RequiredArgsConstructor
public class KeywordController {

    private final UuidService uuidService;
    private final UserService userService;
    private final KeywordService keywordService;

    /**
     * 키워드 등록
     */
    @PostMapping("/keyword")
    public BaseResponse<KeywordSubscribeResDto> KeywordSubscribe(@RequestBody KeywordSubscribeReqDto keywordRegisterDto){
        try{
            String uuid = uuidService.getUuid(); //Header에서 uuid 받아옴
            if(!userService.checkUuidExist(uuid))
                throw new BaseException(NOT_FOUND_USER);
            KeywordSubscribeResDto keywordSubscribeResDto = keywordService.subscribeKeyword(uuid, keywordRegisterDto.getKeyword());
            return new BaseResponse<>(keywordSubscribeResDto);
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }

    }

    @DeleteMapping("/keyword/{keywordId}")
    public BaseResponse<String> KeywordCancel(@PathVariable Long keywordId){
        try{
            String uuid = uuidService.getUuid(); //Header에서 uuid 받아옴
            if(!userService.checkUuidExist(uuid))
                throw new BaseException(NOT_FOUND_USER);
            String keyword = keywordService.cancelKeyword(uuid, keywordId);
            String result = "키워드 삭제 완료. 키워드 : "+keyword;
            return new BaseResponse<>(result);
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
