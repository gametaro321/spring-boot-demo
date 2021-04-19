package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.ZipCodeDto;

@Service
public class ZipCodeService {

    @Autowired
    @Qualifier("zipCodeSearchRestTemplate")
    RestTemplate restTemplate;

    /** 郵便番号検索API リクエストURL */
    private static final String URL1 = "http://zipcloud.ibsnet.co.jp/api/search?zipcode={zipcode}";

    public ZipCodeDto service(String zipcode) {
    	String xx = "";
        return restTemplate.getForObject(URL1, ZipCodeDto.class, zipcode,xx);
    }

    /**  */
    private static final String URL2 = "http://localhost:8090/";

    public ZipCodeDto service(ZipCodeDto zipCodeDto) {
        return restTemplate.postForObject(URL2, zipCodeDto, ZipCodeDto.class);
    }
}
