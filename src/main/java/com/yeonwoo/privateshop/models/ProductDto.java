package com.yeonwoo.privateshop.models;

import lombok.Getter;

//새로 등록한 관심상품 받아오는 DTO
@Getter
public class ProductDto {

    private String title;
    private String image;
    private String link;
    private int lprice;

}

