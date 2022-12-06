package com.newjeans.quickboard.domain.subscribe;

import com.newjeans.quickboard.domain.keyword.Keyword;
import com.newjeans.quickboard.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {
    Subscribe getReferenceByUserAndKeyword(User user, Keyword keyword);
}
