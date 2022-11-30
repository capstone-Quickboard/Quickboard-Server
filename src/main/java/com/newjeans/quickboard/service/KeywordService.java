package com.newjeans.quickboard.service;

import com.newjeans.quickboard.config.BaseException;
import com.newjeans.quickboard.domain.subscribe.Subscribe;
import com.newjeans.quickboard.domain.User.User;
import com.newjeans.quickboard.domain.keyword.Keyword;
import com.newjeans.quickboard.domain.keyword.KeywordRepository;
import com.newjeans.quickboard.domain.User.UserRepository;
import com.newjeans.quickboard.domain.subscribe.SubscribeRepository;
import com.newjeans.quickboard.web.dto.KeywordSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.newjeans.quickboard.config.BaseResponseStatus.DATABASE_ERROR;

@RequiredArgsConstructor
@Service
public class KeywordService {

    private final KeywordRepository keywordRepository;

    private final UserRepository userRepository;

    private final SubscribeRepository subscribeRepository;
    @Transactional
    public void save(String uuid, KeywordSaveRequestDto requestDto) throws BaseException {
        try {
            if(checkKeywordExist(requestDto.getKeyword())) {
                Keyword keyword = keywordRepository.findByKeyword(requestDto.getKeyword());
                keyword.plusSubscribers();
                User user = userRepository.getReferenceByUuid(uuid);
                Keyword findKeyword = keywordRepository.findByKeyword(requestDto.getKeyword());
                Subscribe subscribe = Subscribe.builder().user(user).keyword(findKeyword).build();
                subscribeRepository.save(subscribe);
            } else {
                User user = userRepository.getReferenceByUuid(uuid);
                Keyword saveKeyword = Keyword.builder().keyword(requestDto.getKeyword()).build();
                keywordRepository.save(saveKeyword);
                Keyword findKeyword = keywordRepository.findByKeyword(requestDto.getKeyword());
                findKeyword.plusSubscribers();
                Subscribe subscribe = Subscribe.builder().user(user).keyword(findKeyword).build();
                subscribeRepository.save(subscribe);
            }
        }catch (Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void deleteAllByKeyword(String uuid, String keyword) throws BaseException {
        try {
            User user = userRepository.getReferenceByUuid(uuid);
            Keyword deleteKeyword = keywordRepository.getReferenceByKeyword(keyword);
            Subscribe deleteSubscribe = subscribeRepository.getReferenceByUuidKeyword(uuid, keyword);
            subscribeRepository.deleteAllBySubscribe(deleteSubscribe);
            System.out.println("@@@@@@@@@@@@@@@@@");
            deleteKeyword.minusSubscribers();
            if(deleteKeyword.getSubscribersCount()==0) {
                keywordRepository.delete(deleteKeyword);
            }
        } catch(Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public boolean checkKeywordExist(String keyword) {
        return keywordRepository.existsByKeyword(keyword);
    }
}


