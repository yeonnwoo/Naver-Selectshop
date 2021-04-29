package com.yeonwoo.privateshop.controller;

import com.yeonwoo.privateshop.models.ItemDto;
import com.yeonwoo.privateshop.utils.NaverShopSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SearchController {

    private final NaverShopSearch naverShopSearch;

    @GetMapping("/api/search")
    public List<ItemDto> getItems(@RequestParam String query)
    {
        String resultString = naverShopSearch.search(query);
        return naverShopSearch.JSONtoItem(resultString);

    }
}
