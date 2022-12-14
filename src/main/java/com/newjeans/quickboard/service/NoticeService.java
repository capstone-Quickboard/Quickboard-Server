package com.newjeans.quickboard.service;


import com.newjeans.quickboard.config.BaseException;
import com.newjeans.quickboard.domain.bookmark.BookmarkRepository;
import com.newjeans.quickboard.domain.user.User;
import com.newjeans.quickboard.domain.user.UserRepository;
import com.newjeans.quickboard.domain.department.Department;
import com.newjeans.quickboard.domain.department.DepartmentRepository;
import com.newjeans.quickboard.domain.notice.Notice;
import com.newjeans.quickboard.domain.notice.NoticeRepository;
import com.newjeans.quickboard.domain.userNoticeDeadline.UserNoticeDeadline;
import com.newjeans.quickboard.domain.userNoticeDeadline.UserNoticeDeadlineRepository;
import com.newjeans.quickboard.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.newjeans.quickboard.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final BookmarkRepository bookmarkRepository;
    private final UserNoticeDeadlineRepository userNoticeDeadlineRepository;

    @Transactional
    public NoticeResDto findByNoticeId(String uuid, Long noticeId) throws BaseException{
        User user = userRepository.findByUuid(uuid);
        Optional<Notice> notice = noticeRepository.findById(noticeId);
        if (!notice.isPresent()) {
            throw new BaseException(POSTS_EMPTY_POST_ID);
        }
        // 유저가 커스텀한 마감기한이 있다면 따로 설정
        if(userNoticeDeadlineRepository.existsByUserIdAndNoticeId(user.getId(), notice.get().getId())){
            UserNoticeDeadline userNoticeDeadline = userNoticeDeadlineRepository.findByUserIdAndNoticeId(user.getId(), notice.get().getId());
            NoticeResDto noticeResDto = new NoticeResDto(notice.get());
            noticeResDto.setDeadline(userNoticeDeadline.getDeadline());
            return noticeResDto;
        }
        return new NoticeResDto(notice.get());
    }

    @Transactional
    public SliceResDto<NoticeListResDto> findAllByDepartmentId(String uuid, Long departmentId,
                                              NoticeListReqDto noticeListReqDto,
                                              Pageable pageable) throws BaseException{
        Optional<Department> department = departmentRepository.findById(departmentId);
        if(!department.isPresent()){
            throw new BaseException(INVALID_DEPARTMENT_ID);
        }
        User user = userRepository.getReferenceByUuid(uuid);
        Slice<Notice> sliceNoticeList = noticeRepository.findByDepartmentOrderByUploadDateDesc(department.get(),noticeListReqDto.getLastNoticeId(),pageable);
        List<NoticeListResDto> noticeList = new ArrayList<>();
        for(Notice notice : sliceNoticeList){
            boolean isBookmarked = false ;
            if(bookmarkRepository.existsByUserIdAndNoticeId(user.getId(), notice.getId()))
                isBookmarked=true;
            noticeList.add(new NoticeListResDto(notice,isBookmarked));
        }
        return new SliceResDto<>(sliceNoticeList.getNumberOfElements(), sliceNoticeList.hasNext(), noticeList);
    }

    @Transactional
    public SliceResDto<BookmarkedNoticeListResDto> findAllByBookmarked(String uuid,
                                                                NoticeListReqDto noticeListReqDto,
                                                                Pageable pageable) throws BaseException{
        try {
            User user = userRepository.findByUuid(uuid);

            Slice<Notice> sliceBookmarkedNoticeList = noticeRepository.findBookMarkedNoticeOrderByUploadDateDesc(user,noticeListReqDto.getLastNoticeId(),pageable);
            List<BookmarkedNoticeListResDto> bookmarkedNoticeList = new ArrayList<>();

            for (Notice bookmarkedNotice : sliceBookmarkedNoticeList) {
                //d-day 구하기
                String strDeadLine = bookmarkedNotice.getDeadLine();
                String strToday = new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis())); // 오늘날짜
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                Date deadLine = new Date(dateFormat.parse(strDeadLine).getTime());
                Date today = new Date(dateFormat.parse(strToday).getTime());
                long calculate = deadLine.getTime() - today.getTime();
                int dDays = (int) (calculate / (24 * 60 * 60 * 1000));

                bookmarkedNoticeList.add(new BookmarkedNoticeListResDto(bookmarkedNotice, dDays));
            }
            return new SliceResDto<>(sliceBookmarkedNoticeList.getNumberOfElements(), sliceBookmarkedNoticeList.hasNext(), bookmarkedNoticeList);
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
