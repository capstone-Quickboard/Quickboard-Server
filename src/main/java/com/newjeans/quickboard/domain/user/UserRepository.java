package com.newjeans.quickboard.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    //uuid 존재여부 확인
    boolean existsByUuid(String uuid);

    User findByUuid(String uuid);
    User getReferenceByUuid(String uuid);
}
