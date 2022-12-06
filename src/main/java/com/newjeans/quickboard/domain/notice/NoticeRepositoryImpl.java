package com.newjeans.quickboard.domain.notice;

import com.newjeans.quickboard.domain.bookmark.QBookmark;
import com.newjeans.quickboard.domain.department.Department;
import com.newjeans.quickboard.domain.user.User;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class NoticeRepositoryImpl implements NoticeRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    private final QNotice notice = QNotice.notice;
    private final QBookmark bookmark = QBookmark.bookmark;

    @Override
    public Slice<Notice> findByDepartmentOrderByUploadDateDesc(Department department,Long lastNoticeId, Pageable pageable) {
        List<Notice> noticeList = queryFactory.selectFrom(notice)
                .where(
                        //no-offset 페이징 처리
                        lastNoticeId(lastNoticeId),

                        notice.department.eq(department)
                )
                .orderBy(notice.uploadDate.desc())
                .limit(pageable.getPageSize()+1)
                .fetch();

        return checkLastPage(pageable,noticeList);
    }

    @Override
    public Slice<Notice> findBookMarkedNoticeOrderByUploadDateDesc(User user, Long lastNoticeId, Pageable pageable) {
        List<Notice> noticeList = queryFactory.selectFrom(notice)
                .join(notice.bookmarks,bookmark)
                .where(
                        //no-offset 페이징 처리
                        lastNoticeId(lastNoticeId),
                        bookmark.user.id.eq(user.getId())
                ).orderBy(notice.uploadDate.desc())
                .limit(pageable.getPageSize()+1)
                .fetch();
        return checkLastPage(pageable,noticeList);
    }

    // no-offset 방식 처리하는 메서드
    private BooleanExpression lastNoticeId(Long noticeId) {
        if (noticeId == null) {
            return null;
        }
        return notice.id.lt(noticeId); //notice_id < lastNoticeId
    }



    // 무한 스크롤 방식 처리하는 메서드
    private Slice<Notice> checkLastPage(Pageable pageable, List<Notice> results) {

        boolean hasNext = false;

        // 조회한 결과 개수가 요청한 페이지 사이즈보다 크면 뒤에 더 있음, next = true
        if (results.size() > pageable.getPageSize()) {
            hasNext = true;
            results.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(results, pageable, hasNext);
    }
}
