package com.newjeans.quickboard;

import com.newjeans.quickboard.config.BaseException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.newjeans.quickboard.config.BaseResponseStatus.*;

@Service
public class UuidService {
    /*
        Header에서 UUID-TOKEN 으로 UUID 추출
        @return String
         */
    public String getUuid() throws BaseException{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String uuidToken = request.getHeader("UUID-TOKEN");
        if(uuidToken == null || uuidToken.length() == 0){
            throw new BaseException(EMPTY_UUID);
        }
        return uuidToken;
    }

}
