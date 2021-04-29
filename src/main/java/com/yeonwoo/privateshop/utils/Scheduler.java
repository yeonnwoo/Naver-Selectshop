package com.yeonwoo.privateshop.utils;

import com.yeonwoo.privateshop.models.ItemDto;
import com.yeonwoo.privateshop.models.Product;
import com.yeonwoo.privateshop.models.ProductRepository;
import com.yeonwoo.privateshop.models.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class Scheduler {

    private final ProductRepository productRepository;
    private final NaverShopSearch naverShopSearch;
    private final ProductService productService;

    @Scheduled(cron = " 0 0 2 * * *")
    public void updatePrice() throws InterruptedException {
        System.out.println("가격 업데이트 중입니다");

        List<Product> productList= productRepository.findAll();

        for(int i=0;i<productList.size();i++)
        {
            //1초에 하나씩 검색하는걸로 제한
            TimeUnit.SECONDS.sleep(1);

            Product product = productList.get(i);

            //title보내서 API로 검색
            String title=product.getTitle();
            String result=naverShopSearch.search(title);
            List<ItemDto> resultList= naverShopSearch.JSONtoItem(result);

            //유사도 기준으로 첫번째꺼
            ItemDto ans = resultList.get(0);

            //i번째 상품을 update해야함
            Long id = product.getId();

            productService.update(id,ans);


        }
    }


}
