package com.example.coolweather.gson;

import com.google.gson.annotations.SerializedName;
//JSON的实体类，由于JSON的解析非常简单，只需要将数据对应的实体类创建好久可以了
public class Basic {
    //@SerializedName注释可以让JSON字段与JAVA字段之间建立映射关系
    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String weatherId;

    public Update update;

    public class Update{
        @SerializedName("loc")
        public String updateTime;
    }
}
