package com.newjeans.quickboard.web.controller;


import com.newjeans.quickboard.service.NoticeService;
import com.newjeans.quickboard.web.dto.NoticeListResponseDto;
import com.newjeans.quickboard.web.dto.NoticeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class NoticeController {

    private final NoticeService noticeService;

//    @GetMapping("notice/{departmentId}")
//    public NoticeListResponseDto findByDepartmentId(@PathVariable Long departmentId){
//        return noticeService.findByDepartmentId(departmentId);
//    }
    @GetMapping("/")
    public String connectedTest(){
        return "연결됨";
    }

    @GetMapping("/notice/{departmentId}/{noticeId}")
    public NoticeResponseDto findByNoticeId(@PathVariable Long noticeId){
        return noticeService.findByNoticeId(noticeId);
    }
}
