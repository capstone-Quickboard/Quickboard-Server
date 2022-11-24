package com.newjeans.quickboard.domain.keyword;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    void deleteAllByKeyword(String keyword);

    Keyword getReferenceByKeyword(String keyword);

    boolean existsKByKeyword(String keyword);

    Keyword findByKeyword(String keyword);
}
