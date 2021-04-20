package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
@Data
public class ZipCodeDto2 {
    /** 郵便番号情報リスト */
	@JsonIgnoreProperties()
	List<ZipCodeDataDto> results = new ArrayList<ZipCodeDataDto>();


}
