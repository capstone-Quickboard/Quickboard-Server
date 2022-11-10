package com.newjeans.quickboard.service;


import com.newjeans.quickboard.config.BaseException;
import com.newjeans.quickboard.domain.Bookmark.BookmarkRepository;
import com.newjeans.quickboard.domain.User.User;
import com.newjeans.quickboard.domain.User.UserRepository;
import com.newjeans.quickboard.domain.department.Department;
import com.newjeans.quickboard.domain.department.DepartmentRepository;
import com.newjeans.quickboard.domain.notice.Notice;
import com.newjeans.quickboard.domain.notice.NoticeRepository;
import com.newjeans.quickboard.web.dto.NoticeListResDto;
import com.newjeans.quickboard.web.dto.NoticeResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.newjeans.quickboard.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final BookmarkRepository bookmarkRepository;

    @Transactional
    public NoticeResDto findByNoticeId(Long noticeId) throws BaseException{

        Optional<Notice> notice = noticeRepository.findById(noticeId);
        if (!notice.isPresent()) {
            throw new BaseException(POSTS_EMPTY_POST_ID);
        }
        NoticeResDto noticeResponseDto = new NoticeResDto(notice.get());
        return noticeResponseDto;
    }

    @Transactional
    public List<NoticeListResDto> findAllByDepartmentId(String uuid, Long departmentId) throws BaseException{
        Optional<Department> department = departmentRepository.findById(departmentId);
        if(!department.isPresent()){
            throw new BaseException(INVALID_DEPARTMENT_ID);
        }
        User user = userRepository.getReferenceByUuid(uuid);
        List<Notice> noticeList = department.get().getNotices();
        List<NoticeListResDto> noticeListResDtoList= new ArrayList<NoticeListResDto>();
        for(Notice notice : noticeList){
            boolean isBookmarked = false ;
            if(bookmarkRepository.existsByUserIdAndNoticeId(user.getId(), notice.getId()))
                isBookmarked=true;
            noticeListResDtoList.add(new NoticeListResDto(notice,isBookmarked));
        }
        return noticeListResDtoList;
    }

}
