package com.newjeans.quickboard.web.controller;

import com.newjeans.quickboard.config.BaseResponse;
import com.newjeans.quickboard.service.KeywordService;
import com.newjeans.quickboard.web.dto.KeywordSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.newjeans.quickboard.config.BaseResponseStatus.ALREADY_DELETE_KEYWORD;

@RequiredArgsConstructor
@RestController
public class KeywordController {

    private final KeywordService keywordService;
    @PostMapping("/keyword")
    public Long save(@RequestBody KeywordSaveRequestDto requestDto) {
        return keywordService.save(requestDto);
    }


    @DeleteMapping("/keyword/{keyword}")
    public BaseResponse<String> deleteAllByKeyword(@PathVariable("keyword") String keyword) {
        // 프론트에 없는 키워드는 삭제 불가능 하기 때문에 요청 오는 키워드는 중복된 키워드 삭제 요청
        try {
            keywordService.deleteAllByKeyword(keyword);
            String result = "키워드 삭제 완료";
            return new BaseResponse<>(result);
        } catch (Exception exception) {
            return new BaseResponse<>(ALREADY_DELETE_KEYWORD);
        }
    }

}
