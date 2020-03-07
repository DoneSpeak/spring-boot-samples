package io.github.donespeak.springbootsamples.ws.client.controller;

import io.github.donespeak.springbootsamples.ws.client.wsdl.weather.ArrayOfString;
import io.github.donespeak.springbootsamples.ws.client.wsdl.weather.GetRegionDatasetResponse;
import io.github.donespeak.springbootsamples.ws.client.wsdl.weather.GetSupportCityDatasetResponse;
import io.github.donespeak.springbootsamples.ws.client.wsdl.weather.WeatherWS;
import io.github.donespeak.springbootsamples.ws.client.wsdl.weather.WeatherWSSoap;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping("/region-country")
    public ArrayOfString getRegionCountry() {
        WeatherWSSoap weatherWSSoap = getWeatherWSSoap();
        return weatherWSSoap.getRegionCountry();
    }

    private WeatherWSSoap getWeatherWSSoap() {
        WeatherWS weatherWS = new WeatherWS();
        WeatherWSSoap weatherWSSoap = weatherWS.getWeatherWSSoap();
        return weatherWSSoap;
    }

    /**
     * 获得中国省份、直辖市、地区；国家名称（国外）和与之对应的ID
     * http://ws.webxml.com.cn/WebServices/WeatherWS.asmx?op=getRegionDataset
     */
    @GetMapping("/region-dataset")
    public GetRegionDatasetResponse.GetRegionDatasetResult getRegionDataset() {
        WeatherWSSoap weatherWSSoap = getWeatherWSSoap();
        return weatherWSSoap.getRegionDataset();
    }

    /**
     * 获得中国省份、直辖市、地区；国家名称（国外）和与之对应的ID
     * http://ws.webxml.com.cn/WebServices/WeatherWS.asmx?op=getRegionProvince
     */
    @GetMapping("/region-province")
    public ArrayOfString getRegionProvince() {
        WeatherWSSoap weatherWSSoap = getWeatherWSSoap();
        return weatherWSSoap.getRegionProvince();
    }

    /**
     * 获得支持的城市/地区名称和与之对应的ID
     * http://ws.webxml.com.cn/WebServices/WeatherWS.asmx?op=getSupportCityDataset
     */
    @GetMapping("/support-city-dataset")
    public GetSupportCityDatasetResponse.GetSupportCityDatasetResult getSupportCityDataset(String regionCode) {
        WeatherWSSoap weatherWSSoap = getWeatherWSSoap();
        return weatherWSSoap.getSupportCityDataset(regionCode);
    }

    /**
     * 获得支持的城市/地区名称和与之对应的ID
     * http://ws.webxml.com.cn/WebServices/WeatherWS.asmx?op=getSupportCityString
     */
    @GetMapping("/support-city")
    public ArrayOfString getSupportCityString(String regionCode) {
        WeatherWSSoap weatherWSSoap = getWeatherWSSoap();
        return weatherWSSoap.getSupportCityString(regionCode);
    }

    /**
     * 获得天气预报数据
     * http://ws.webxml.com.cn/WebServices/WeatherWS.asmx?op=getWeather
     */
    @GetMapping("")
    public ArrayOfString getWeather(String cityCode, String userId) {
        WeatherWSSoap weatherWSSoap = getWeatherWSSoap();
        return weatherWSSoap.getWeather(cityCode, userId);
    }
}
