package com.newjeans.quickboard.service;

import com.newjeans.quickboard.config.BaseException;
import com.newjeans.quickboard.domain.bookmark.Bookmark;
import com.newjeans.quickboard.domain.bookmark.BookmarkRepository;
import com.newjeans.quickboard.domain.user.User;
import com.newjeans.quickboard.domain.user.UserRepository;
import com.newjeans.quickboard.domain.department.Department;
import com.newjeans.quickboard.domain.department.DepartmentRepository;
import com.newjeans.quickboard.domain.notice.Notice;
import com.newjeans.quickboard.domain.notice.NoticeRepository;
import com.newjeans.quickboard.domain.userNoticeDeadline.UserNoticeDeadline;
import com.newjeans.quickboard.domain.userNoticeDeadline.UserNoticeDeadlineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.newjeans.quickboard.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class UserService{
    private final UserRepository userRepository;
    private final NoticeRepository noticeRepository;
    private final BookmarkRepository bookmarkRepository;
    private final UserNoticeDeadlineRepository userNoticeDeadlineRepository;
    private final DepartmentRepository departmentRepository;

    @Transactional
    public void save(String uuid, Long departmentId) throws BaseException {
        //fcm token은 아직 포함 x

        try{
            Department department = departmentRepository.getReferenceById(departmentId);
            User user = User.builder().uuid(uuid).department(department).build();
            userRepository.save(user);
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
    @Transactional
    public boolean checkUuidExist(String uuid){

        return userRepository.existsByUuid(uuid);
    }

    @Transactional
    public Long saveBookmark(String uuid, Long noticeId) throws BaseException{
        try{
            User user = userRepository.getReferenceByUuid(uuid);
            if(bookmarkRepository.existsByUserIdAndNoticeId(user.getId(), noticeId))
                throw new BaseException(ALREADY_EXIST_BOOKMARK);
            Notice notice = noticeRepository.getReferenceById(noticeId);
            Bookmark bookmark = Bookmark.builder().user(user).notice(notice).build();
            return bookmarkRepository.save(bookmark).getNotice().getId();
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional
    public Long deleteBookmark(String uuid, Long noticeId) throws BaseException{
        try{
            User user = userRepository.getReferenceByUuid(uuid);
            //++++없는 북마크 지우려고 할때
            Bookmark bookmark = bookmarkRepository.getReferenceByUserIdAndNoticeId(user.getId(),noticeId);
            bookmarkRepository.delete(bookmark);
            return bookmark.getNotice().getId();
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional
    public void saveDeadline(String uuid, Long noticeId, String deadline) throws BaseException{
        try{
            Notice notice = noticeRepository.getReferenceById(noticeId);
            User user = userRepository.getReferenceByUuid(uuid);

            //이미 마감기한을 등록했는데 POST 요청이 온 경우 처리
            if(userNoticeDeadlineRepository.existsByUserIdAndNoticeId(user.getId(), noticeId)){
                updateDeadline(uuid,noticeId,deadline);
                return;
            }
            UserNoticeDeadline userNoticeDeadline = UserNoticeDeadline.builder()
                    .user(user).notice(notice).deadline(deadline)
                    .build();
            userNoticeDeadlineRepository.save(userNoticeDeadline);
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional
    public void updateDeadline(String uuid, Long noticeId, String deadline) throws BaseException{
        try{
            Notice notice = noticeRepository.getReferenceById(noticeId);
            User user = userRepository.getReferenceByUuid(uuid);
            UserNoticeDeadline userNoticeDeadline = userNoticeDeadlineRepository.findByUserIdAndNoticeId(user.getId(), notice.getId());
            userNoticeDeadline.update(deadline);
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
