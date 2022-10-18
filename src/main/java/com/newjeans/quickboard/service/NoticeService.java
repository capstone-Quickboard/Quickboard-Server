package com.newjeans.quickboard.service;


import com.newjeans.quickboard.domain.notice.Notice;
import com.newjeans.quickboard.domain.notice.NoticeRepository;
import com.newjeans.quickboard.web.dto.NoticeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional
    public NoticeResponseDto findByNoticeId(Long noticeId){
        Notice entity = noticeRepository.findById(noticeId)
                .orElseThrow(()->new IllegalArgumentException("해당 공지사항이 없습니다. id="+noticeId));
        return new NoticeResponseDto(entity,7);
    }

}
