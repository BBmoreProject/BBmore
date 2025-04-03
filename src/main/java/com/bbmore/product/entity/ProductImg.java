package com.bbmore.product.entity;

import com.bbmore.product.config.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="product_img")
@Getter
@Setter
public class ProductImg extends BaseTimeEntity {

    @Id
    @Column(name="product_img_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productImgName;

    private String productOriginalImgName;

    private String imgUrl;

    private String representativeImg; /// 대표 이미지 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public void updateProductImg(String productOriginalImgName,
                                 String productImgName, String imgUrl) {
        this.productOriginalImgName = productOriginalImgName;
        this.productImgName = productImgName;
        this.imgUrl = imgUrl;
    }
}
