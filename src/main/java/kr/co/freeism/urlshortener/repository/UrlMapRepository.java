package kr.co.freeism.urlshortener.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Martin
 * @since 2017. 11. 2.
 */
@Repository
public class UrlMapRepository {
    @Autowired
    private UrlMapJpaRepository jpaRepository;

    @Transactional
    public void saveIfNotExisted(String url, String hashValue) {
        UrlMap urlMapAtDb = jpaRepository.findByHashValue(hashValue);

        if (urlMapAtDb == null) {
            jpaRepository.save(createUrlMap(url, hashValue));
        }
    }

    public UrlMap findByHashValue(String hashValue) {
        return jpaRepository.findByHashValue(hashValue);
    }

    private UrlMap createUrlMap(String url, String hashValue) {
        return UrlMap.builder()
            .url(url)
            .hashValue(hashValue)
            .build();
    }
}