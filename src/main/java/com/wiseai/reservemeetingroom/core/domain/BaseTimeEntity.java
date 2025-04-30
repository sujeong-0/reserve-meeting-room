package com.wiseai.reservemeetingroom.core.domain;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.Instant;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Entity 에서 공통으로 사용되는 내용의 정의를 담당합니다.
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    /**
     * 추가 일시
     */
    @CreatedDate
    private Instant createdAt;


    /**
     * 변경 일시
     */
    @LastModifiedDate
    private Instant updatedAt;
}
