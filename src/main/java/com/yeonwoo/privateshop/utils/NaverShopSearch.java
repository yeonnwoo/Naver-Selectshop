package com.yeonwoo.privateshop.utils;

import com.yeonwoo.privateshop.models.ItemDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class NaverShopSearch {
    public String search(String query){
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("X-Naver-Client-Id", "OrOZxAHgkDXmIaE80m1h");
        headers.add("X-Naver-Client-Secret", "fnQdJfFvlI");

        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  \"username\":\"test2\",\n");
        sb.append("  \"contents\":\"안녕하세요\"\n");
        sb.append("}");
        String body = sb.toString();

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/shop.json?query="+query, HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        String response = responseEntity.getBody();
        System.out.println("Response status: " + status);
        System.out.println(response);

        return response;
    }

    public List<ItemDto> JSONtoItem(String response)
    {
        JSONObject obJson = new JSONObject(response);
        JSONArray items=obJson.getJSONArray("items");

        List<ItemDto> ListDto = new ArrayList<>();
        for(int i=0;i< items.length();i++)
        {
            JSONObject temp = (JSONObject) items.get(i);
            ItemDto itemDto = new ItemDto(temp);

            ListDto.add(itemDto);
        }

        return ListDto;

    }
}
