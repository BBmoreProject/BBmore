package com.bbmore.product.config;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
@Getter
/**
 * @AuditingEntityListener.class = 엔티티의 생성자와 수정자를 자동으로 기록함
 * @MappedSuperClass : 클래스가 데이터베이스에 매핑되지 않고
 * 이 클래스를 상속받는 엔티티 클래스에 필드를 재공함
 *
 * abstract: 다른 인스턴스를 상속받아 구현함. 이 클래스의 직접적인 인스턴스 생성 방지.
 *
 */
public abstract class BaseEntity extends BaseTimeEntity{

    @CreatedBy
    @Column(updatable = false)
    private String createBy;

    @LastModifiedBy
    private String modifiedBy;

}
