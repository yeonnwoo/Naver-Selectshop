package com.yeonwoo.privateshop.controller;

import com.yeonwoo.privateshop.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductService productService;

    //관심상품 받아오기
    @GetMapping("/api/products")
    public List<Product> getProduct()
    {
        return productRepository.findAll();
    }

    //관심상품 등록
    @PostMapping("/api/products")
    public Product makeProduct(@RequestBody ProductDto productDto)
    {
        Product product = new Product(productDto);
        productRepository.save(product);
        return product;

    }

    // 설정 가격 변경
    @PutMapping("/api/products/{id}")
    public Long updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto requestDto) {
        return productService.updatePrice(id, requestDto);
    }



}
