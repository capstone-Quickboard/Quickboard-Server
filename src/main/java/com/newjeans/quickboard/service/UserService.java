package com.newjeans.quickboard.service;

import com.newjeans.quickboard.config.BaseException;
import com.newjeans.quickboard.domain.Bookmark.Bookmark;
import com.newjeans.quickboard.domain.Bookmark.BookmarkRepository;
import com.newjeans.quickboard.domain.User.User;
import com.newjeans.quickboard.domain.User.UserRepository;
import com.newjeans.quickboard.domain.notice.Notice;
import com.newjeans.quickboard.domain.notice.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.newjeans.quickboard.config.BaseResponseStatus.DATABASE_ERROR;
import static com.newjeans.quickboard.config.BaseResponseStatus.INVALID_UUID;

@RequiredArgsConstructor
@Service
public class UserService{
    private final UserRepository userRepository;
    private final NoticeRepository noticeRepository;
    private final BookmarkRepository bookmarkRepository;

    @Transactional
    public void save(String uuid) throws BaseException {
        //fcmtoken은 아직 포함 x
        User user = User.builder().uuid(uuid).build();
        try{
            userRepository.save(user);
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
    @Transactional
    public boolean checkUuidExist(String uuid) throws BaseException{

        return userRepository.existsByUuid(uuid);
    }

    @Transactional
    public Long saveBookmark(String uuid, Long noticeId) throws BaseException{
        try{
            User user = userRepository.getReferenceByUuid(uuid);
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
            Bookmark bookmark = bookmarkRepository.getReferenceByUserIdAndNoticeId(user.getId(),noticeId);
            bookmarkRepository.delete(bookmark);
            return bookmark.getNotice().getId();
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
