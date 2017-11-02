package kr.co.freeism.urlshortener.service;

import kr.co.freeism.urlshortener.repository.UrlMap;
import kr.co.freeism.urlshortener.repository.UrlMapRepository;
import kr.co.freeism.urlshortener.support.HashingUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Martin
 * @since 2017. 11. 2.
 */
@Slf4j
@Service
public class UrlMapService {
    @Autowired
    private UrlMapRepository urlMapRepository;

    public String getHashValue(String url) {
        String hashValue = HashingUtils.hash(url);

        urlMapRepository.saveIfNotExisted(url, hashValue);

        return hashValue;
    }

    public String getUrl(String hashValue) {
        UrlMap urlMap = urlMapRepository.findByHashValue(hashValue);

        if (urlMap == null) {
            log.error("UrlMap is not existed : hashValue = {}", hashValue);
            throw new RuntimeException("UrlMap is not existed");
        }

        return urlMap.getUrl();
    }
}