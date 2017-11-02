package kr.co.freeism.urlshortener.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * @author Martin
 * @since 2017. 11. 2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UrlMapRepositoryTest {
    private static final String URL = "http://localhost";
    private static final String HASH_VALUE = "296beed8";

    @Autowired
    private UrlMapRepository dut;
    @Autowired
    private UrlMapJpaRepository urlMapJpaRepository;

    @Test
    public void saveIfNotExisted() {
        dut.saveIfNotExisted(URL, HASH_VALUE);

        verifyUrlMapAtDb();
    }

    @Test
    public void saveIfNotExistedButExisted() {
        givenUrlMapAtDb();

        dut.saveIfNotExisted(URL, HASH_VALUE);

        verifyUrlMapAtDb();
    }

    @Test
    public void findByHashValue() {
        givenUrlMapAtDb();

        UrlMap result = dut.findByHashValue(HASH_VALUE);

        verifyUrlMap(result);
    }

    private void givenUrlMapAtDb() {
        urlMapJpaRepository.save(mockUrlMap());
    }

    private void verifyUrlMapAtDb() {
        UrlMap result = urlMapJpaRepository.findByHashValue(HASH_VALUE);

        verifyUrlMap(result);
    }

    private void verifyUrlMap(UrlMap result) {
        System.out.println(result);
        assertThat(result.getIdx(), not(nullValue()));
        assertThat(result.getUrl(), is(URL));
        assertThat(result.getHashValue(), is(HASH_VALUE));
        assertThat(result.getCreatedAt(), not(nullValue()));
        assertThat(result.getModifiedAt(), not(nullValue()));
    }

    private UrlMap mockUrlMap() {
        return UrlMap.builder()
            .url(URL)
            .hashValue(HASH_VALUE)
            .build();
    }
}