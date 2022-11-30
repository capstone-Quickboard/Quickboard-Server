package com.newjeans.quickboard.domain.subscribe;

import com.newjeans.quickboard.domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {

    void deleteAllByUser(User user);

    Subscribe getReferenceByUuidKeyword(String uuid, String keyword);

    void deleteAllBySubscribe(Subscribe deleteSubscribe);
}
