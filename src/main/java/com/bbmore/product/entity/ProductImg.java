package com.bbmore.product.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_product_img")
@Getter
@Setter
public class ProductImg {

    @Id
    @Column(name = "product_img_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productImgCode;

    @Column(name= "product_img_name")
    private String productImgName;

    @Column(name = "product_img_ori_name")
    private String productImgOriName;

    @Column(name = "product_img_rep_yn")
    private String productImgRepYn;

    @Column (name = "product_img_url")
    private String productImgUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_code")
    private Product product;

    /// 도매인 객체의 생성주기 관리
    public void updateProductImg(String productImgOriName, String productImgName, String productImgUrl) {
        this.productImgOriName = productImgOriName;
        this.productImgName = productImgName;
        this.productImgUrl = productImgUrl;
    }


}
