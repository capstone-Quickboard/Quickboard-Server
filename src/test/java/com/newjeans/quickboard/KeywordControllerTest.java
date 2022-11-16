package com.newjeans.quickboard;

import com.newjeans.quickboard.domain.keyword.Keyword;
import com.newjeans.quickboard.domain.keyword.KeywordRepository;
import com.newjeans.quickboard.web.dto.KeywordSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KeywordControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private KeywordRepository keywordRepository;

    @After
    public void tearDown() throws Exception {
        keywordRepository.deleteAll();
    }

    @Test
    public void Keyword_등록() throws Exception {
        String keyword = "장학금";
        KeywordSaveRequestDto requestDto = KeywordSaveRequestDto.builder()
                .keyword(keyword)
                .build();


        String url = "http://localhost:" + port + "/keyword";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Keyword> all = keywordRepository.findAll();
        assertThat(all.get(0).getKeyword()).isEqualTo("keyword");
    }



}
