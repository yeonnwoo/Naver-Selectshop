package com.yeonwoo.privateshop.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Product extends Timestamped{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private int lprice;

    @Column(nullable = false)
    private int myprice;

    public Product(ProductDto productDto) {
        this.title= productDto.getTitle();
        this.image= productDto.getImage();
        this.link= productDto.getLink();
        this.lprice= productDto.getLprice();
        this.myprice=0;
    }

    public void updatePrice(ProductMypriceRequestDto productMypriceRequestDto)
    {
        this.myprice= productMypriceRequestDto.getMyprice();
    }

    public void update(ItemDto itemDto)
    {
        this.lprice= itemDto.getLprice();
    }


}

