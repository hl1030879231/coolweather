package com.example.coolweather.util;

import android.text.TextUtils;

import com.example.coolweather.db.City;
import com.example.coolweather.db.County;
import com.example.coolweather.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 用来处理服务器返回的JSON数据
 */
public class Utility {
    /**
     * 解析与处理服务器返回的省级数据
     * 由于数据内容比较简单，因此都采用JSONObject的方式进行解析
     * @param response
     * @return
     */
    public static boolean handleProvinceResponse(String response){
        if (!TextUtils.isEmpty(response)){
            try {
                //生成一个JSON对象数组
                JSONArray allProvince = new JSONArray(response);
                //获取并处理每一个JSON对象
                for (int i=0;i<allProvince.length();i++){
                    JSONObject provinceJSONObject = allProvince.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceJSONObject.getString("name"));
                    province.setProvinceCode(provinceJSONObject.getInt("id"));
                    //调用对象的save()方法将数据保存到数据库中，litepal里边的一个方法，前面已经进行了类映射
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析与处理服务器返回的市级数据
     * @param response
     * @return
     */
    public static boolean handleCityResponse(String response,int provinceId){
        if (!TextUtils.isEmpty(response)){
            try {
                //生成一个JSON对象数组
                JSONArray allCity = new JSONArray(response);
                //获取并处理每一个JSON对象
                for (int i=0;i<allCity.length();i++){
                    JSONObject cityJSONObject = allCity.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityJSONObject.getString("name"));
                    city.setCityCode(cityJSONObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    //调用对象的save()方法将数据保存到数据库中，litepal里边的一个方法，前面已经进行了类映射
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析与处理服务器返回的县级数据
     * @param response
     * @return
     */
    public static boolean handleCountyResponse(String response,int cityId){
        if (!TextUtils.isEmpty(response)){
            try {
                //生成一个JSON对象数组
                JSONArray allCountyy = new JSONArray(response);
                //获取并处理每一个JSON对象
                for (int i=0;i<allCountyy.length();i++){
                    JSONObject countyJSONObject = allCountyy.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyJSONObject.getString("name"));
                    county.setWeatherId(countyJSONObject.getString("weather_id"));
                    county.setCityId(cityId);
                    //调用对象的save()方法将数据保存到数据库中，litepal里边的一个方法，前面已经进行了类映射
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
