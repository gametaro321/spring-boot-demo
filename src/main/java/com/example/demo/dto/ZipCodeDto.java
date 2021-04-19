package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
@Data
public class ZipCodeDto {

    /** ステータス */
    int status;

    /** メッセージ */
    String message;

    /** 郵便番号情報リスト */
    List<ZipCodeDataDto> results = new ArrayList<>();


}
