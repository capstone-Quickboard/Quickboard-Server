package com.newjeans.quickboard.service;

import com.newjeans.quickboard.config.BaseException;
import com.newjeans.quickboard.config.BaseResponseStatus;
import com.newjeans.quickboard.domain.keyword.Keyword;
import com.newjeans.quickboard.domain.keyword.KeywordRepository;
import com.newjeans.quickboard.web.dto.KeywordSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.newjeans.quickboard.config.BaseResponseStatus.DATABASE_ERROR;

@RequiredArgsConstructor
@Service
public class KeywordService {

    private final KeywordRepository keywordRepository;

    public Long save(KeywordSaveRequestDto requestDto) {

        if(checkKeywordExist(requestDto.getKeyword())) {
            requestDto.KeywordplusRequestDto(requestDto.getSubscriberCount());
            return keywordRepository.findByKeyword(requestDto.getKeyword()).getId();

        } else
            return keywordRepository.save(requestDto.toEntity()).getId();
    }


    public void deleteAllByKeyword(String keyword) throws BaseException {
        try {
            Keyword deleteKeyword = keywordRepository.getReferenceByKeyword(keyword);
            keywordRepository.delete(deleteKeyword);
        } catch(Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public boolean checkKeywordExist(String keyword) {
        return keywordRepository.existsKByKeyword(keyword);
    }

    public void plussubscribersCount(Keyword keyword) {
        keyword.builder().subscribersCount(keyword.getSubscribersCount() + 1).build();
    }



}


