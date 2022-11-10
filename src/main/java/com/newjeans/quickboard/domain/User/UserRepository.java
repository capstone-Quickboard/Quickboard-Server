package com.newjeans.quickboard.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    //uuid 존재여부 확인
    boolean existsByUuid(String uuid);

    User findByUuid(String uuid);
    User getReferenceByUuid(String uuid);
}
