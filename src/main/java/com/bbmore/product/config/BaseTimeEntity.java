package com.bbmore.product.config;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * @EntityListeners(value = {AuditingEntityListener.class}):
 * 엔티티의 생명주기 이벤트를 감지하여 특정 콜백 메소드를 호출하는 리스너를 지정합니다.
 * 자동으로 감시하는 기능을 가지고 있음
 *
 * @MappedSuper : 이 클래스 자체는 엔티티가 아니지만, 이 클래스를 상속받는 클래스들에게 매핑 정보를 제공한다는 의미입니다.
 * 이 어노테이션이 있는 클래스의 필드들은 상속받는 엔티티 클래스의 컬럼으로 자동 매핑됩니다.
 * 데이터베이스 테이블로는 생성되지 않고, 자식 엔티티들의 테이블에 컬럼으로만 존재합니다.
 */
@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
@Getter
@Setter
public abstract class BaseTimeEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regTime;

    @LastModifiedDate
    private LocalDateTime updateTime;


}
