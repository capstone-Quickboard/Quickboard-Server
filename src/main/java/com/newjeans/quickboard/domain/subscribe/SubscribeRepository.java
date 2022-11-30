package com.newjeans.quickboard.domain.subscribe;

import com.newjeans.quickboard.domain.User.User;
import com.newjeans.quickboard.domain.keyword.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {
    Subscribe findByUserAndKeyword(User user, Keyword keyword);
}
