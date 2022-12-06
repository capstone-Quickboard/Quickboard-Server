package com.newjeans.quickboard.service;

import com.newjeans.quickboard.config.BaseException;
import com.newjeans.quickboard.domain.keyword.Keyword;
import com.newjeans.quickboard.domain.keyword.KeywordRepository;
import com.newjeans.quickboard.domain.subscribe.Subscribe;
import com.newjeans.quickboard.domain.subscribe.SubscribeRepository;
import com.newjeans.quickboard.domain.user.User;
import com.newjeans.quickboard.domain.user.UserRepository;
import com.newjeans.quickboard.web.dto.KeywordSubscribeResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.newjeans.quickboard.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class KeywordService {

    private final KeywordRepository keywordRepository;
    private final SubscribeRepository subscribeRepository;
    private final UserRepository userRepository;

    @Transactional
    public KeywordSubscribeResDto subscribeKeyword(String uuid, String keyword_string) throws BaseException {
        try {
            User user = userRepository.getReferenceByUuid(uuid);
            //해당 키워드 단어가 keyword 테이블에 존재하는지 확인
            if (!keywordRepository.existsByKeyword(keyword_string)) {
                Keyword keyword = Keyword.builder().keyword(keyword_string).build();
                keywordRepository.save(keyword);
            }
            Keyword keyword = keywordRepository.findByKeyword(keyword_string);
            Subscribe subscribe = Subscribe.builder().user(user).keyword(keyword).build();
            subscribeRepository.save(subscribe);
            keyword.plusSubscriberCount();
            KeywordSubscribeResDto keywordSubscribeResDto = KeywordSubscribeResDto.builder()
                    .keywordId(keyword.getId()).keyword(keyword.getKeyword())
                    .build();
            return keywordSubscribeResDto;
        }catch (Exception e){
            e.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional
    public String cancelKeyword(String uuid, Long keywordId)throws BaseException{
        try {
            User user = userRepository.getReferenceByUuid(uuid);
            Keyword keyword = keywordRepository.getReferenceById(keywordId);
            Subscribe subscribe = subscribeRepository.getReferenceByUserAndKeyword(user, keyword);
            subscribeRepository.delete(subscribe);
            keyword.minusSubscriberCount();
            if(keyword.getSubscribersCount()==0)
                keywordRepository.delete(keyword);
            return keyword.getKeyword();
        }catch (Exception e){
            e.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
