package com.newjeans.quickboard.domain.keyword;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {

    boolean existsByKeyword(String keyword);
    Keyword findByKeyword(String keyword);
}
