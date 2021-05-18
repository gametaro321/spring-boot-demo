package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
@Data
public class IdolMasterDto {

    /** ステータス */
    int status;
    /** メッセージ */
    String message;
    List<IdolMasterDataDto> results = new ArrayList<>();

}
