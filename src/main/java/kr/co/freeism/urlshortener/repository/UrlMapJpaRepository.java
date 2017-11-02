package kr.co.freeism.urlshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Martin
 * @since 2017. 11. 2.
 */
interface UrlMapJpaRepository extends JpaRepository<UrlMap, Long> {
    UrlMap findByHashValue(String hashValue);
}