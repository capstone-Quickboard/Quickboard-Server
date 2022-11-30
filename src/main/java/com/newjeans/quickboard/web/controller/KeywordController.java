package com.newjeans.quickboard.web.controller;

import com.newjeans.quickboard.UuidService;
import com.newjeans.quickboard.config.BaseException;
import com.newjeans.quickboard.config.BaseResponse;
import com.newjeans.quickboard.service.KeywordService;
import com.newjeans.quickboard.service.UserService;
import com.newjeans.quickboard.web.dto.KeywordSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.newjeans.quickboard.config.BaseResponseStatus.ALREADY_DELETE_KEYWORD;
import static com.newjeans.quickboard.config.BaseResponseStatus.INVALID_UUID;

@RequiredArgsConstructor
@RestController
public class KeywordController {

    private final KeywordService keywordService;

    private final UuidService uuidService;

    private final UserService userService;

    @PostMapping("/keyword")
    public BaseResponse<String> saveKeyword(@RequestBody KeywordSaveRequestDto requestDto) {
        try {
            String uuid = uuidService.getUuid();
            if(!userService.checkUuidExist(uuid)) {
                throw new BaseException(INVALID_UUID);
            }
            keywordService.save(uuid, requestDto);
            String result = "키워드 등록";
            return new BaseResponse<>(result);
        }
        catch(BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }


    @DeleteMapping("/keyword/{keyword}")
    public BaseResponse<String> deleteAllByKeyword(@PathVariable("keyword") String keyword) {
        // 프론트에 없는 키워드는 삭제 불가능 하기 때문에 요청 오는 키워드는 중복된 키워드 삭제 요청
        try {
            String uuid = uuidService.getUuid();
            if(!userService.checkUuidExist(uuid)) {
                throw new BaseException(INVALID_UUID);
            }
            keywordService.deleteAllByKeyword(uuid, keyword);
            String result = "키워드 삭제 완료";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

}
