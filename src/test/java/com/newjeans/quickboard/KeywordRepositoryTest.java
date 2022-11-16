package com.newjeans.quickboard;

import com.newjeans.quickboard.domain.keyword.Keyword;
import com.newjeans.quickboard.domain.keyword.KeywordRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KeywordRepositoryTest {
    @Autowired
    KeywordRepository keywordRepository;

    @After
    public void cleanup() {
        keywordRepository.deleteAll();
    }

    @Test
    public void 키워드_추가하기() {
        String keyword = "졸업";
        keywordRepository.save(Keyword.builder()
                .keyword(keyword)
//                .subscribersCount(3)
                .build());

        List<Keyword> keywordList = keywordRepository.findAll();

        Keyword keywords = keywordList.get(0);
        assertThat(keywords.getKeyword()).isEqualTo("졸업");
    }


}
