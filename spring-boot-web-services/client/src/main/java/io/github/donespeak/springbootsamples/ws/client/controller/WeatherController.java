package io.github.donespeak.springbootsamples.ws.client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yang Guanrong
 * @date 2020/03/05 22:16
 */
@RestController
@RequestMapping("/ws/weather")
public class WeatherController {

    /**
     * 获得国外国家名称和与之对应的ID
     * http://ws.webxml.com.cn/WebServices/WeatherWS.asmx?op=getRegionCountry
     */
    public void getRegionCountry() {

    }

    /**
     * 获得中国省份、直辖市、地区；国家名称（国外）和与之对应的ID
     * http://ws.webxml.com.cn/WebServices/WeatherWS.asmx?op=getRegionDataset
     */
    public void getRegionDataset() {

    }

    /**
     * 获得中国省份、直辖市、地区；国家名称（国外）和与之对应的ID
     * http://ws.webxml.com.cn/WebServices/WeatherWS.asmx?op=getRegionProvince
     */
    public void getRegionProvince() {

    }

    /**
     * 获得支持的城市/地区名称和与之对应的ID
     * http://ws.webxml.com.cn/WebServices/WeatherWS.asmx?op=getSupportCityDataset
     */
    public void getSupportCityDataset() {

    }

    /**
     * 获得支持的城市/地区名称和与之对应的ID
     * http://ws.webxml.com.cn/WebServices/WeatherWS.asmx?op=getSupportCityString
     */
    public void getSupportCityString() {

    }

    /**
     * 获得天气预报数据
     * http://ws.webxml.com.cn/WebServices/WeatherWS.asmx?op=getWeather
     */
    public void getWeather() {

    }
}
