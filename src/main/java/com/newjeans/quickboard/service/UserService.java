package com.newjeans.quickboard.service;

import com.newjeans.quickboard.config.BaseException;
import com.newjeans.quickboard.domain.User.User;
import com.newjeans.quickboard.domain.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.newjeans.quickboard.config.BaseResponseStatus.DATABASE_ERROR;

@RequiredArgsConstructor
@Service
public class UserService{
    private final UserRepository userRepository;

    @Transactional
    public void save(String uuid) throws BaseException {
        //fcmtoken은 아직 포함 x
        User user = User.builder().uuid(uuid).build();
        try{
            userRepository.save(user);
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
        return ;
    }

}
