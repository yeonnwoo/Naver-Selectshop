package com.yeonwoo.privateshop.models;

import lombok.Getter;
import org.json.JSONObject;

@Getter
public class ItemDto {
    private String title;
    private String image;
    private String link;
    private int lprice;

    public ItemDto(JSONObject jsonObject)
    {
        this.title= jsonObject.getString("title");
        this.image= jsonObject.getString("image");
        this.link=jsonObject.getString("link");
        this.lprice= jsonObject.getInt("lprice");
    }

}

