package kr.co.freeism.urlshortener.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Martin
 * @since 2017. 11. 2.
 */
@Data
@Entity
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrlMap implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false, unique = true)
    private int idx;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "hash_value", nullable = false)
    private String hashValue;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;

    @PrePersist
    public void persist() {
        LocalDateTime now = LocalDateTime.now();

        createdAt = now;
        modifiedAt = now;
    }

    @PreUpdate
    public void update() {
        modifiedAt = LocalDateTime.now();
    }
}