package com.newjeans.quickboard.web.controller;

import com.newjeans.quickboard.UuidService;
import com.newjeans.quickboard.config.BaseException;
import com.newjeans.quickboard.config.BaseResponse;
import com.newjeans.quickboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final UuidService uuidService;

    @PostMapping("/user")
    public BaseResponse<String> saveUser(){
        try{
            String uuid = uuidService.getUuid(); //Header에서 uuid 받아옴
            userService.save(uuid);
            return new BaseResponse<>("유저 등록 성공");
        }catch(BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

}
