package com.yeonwoo.privateshop.models;

import lombok.RequiredArgsConstructor;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Long updatePrice(Long id,ProductMypriceRequestDto requestDto)
    {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        product.updatePrice(requestDto);
        return id;
    }

    @Transactional
    public void update(Long id, ItemDto itemDto)
    {
        //해당 id의 product 찾기
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("해당하는 값이 존재하지 않음")
        );

        product.update(itemDto);

    }

}

