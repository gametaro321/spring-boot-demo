package com.example.demo.dto;

import java.io.Serializable;

import lombok.Data;
@Data
public class IdolMasterDataDto implements Serializable{
    /**  */
    long ID;
    /** 名前 */
    String NAME;
    /** ふりがな */
    String FURIGANA;
    /** タイプ */
    String TYPE;
    /** 年齢 */
    String AGE;
    /** 身長 */
    String HEIGHT;
    /** 体重 */
    String WEIGHT;
    /** 誕生日 */
    String BRITHDAY;
    /** 血液型 */
    String BLOOD_TYPE;
    /** 利き手 */
    String DOMINANT_HAND;
    /** スリーサイズ(B) */
    String THREE_SIZE_B;
    /** スリーサイズ(W) */
    String THREE_SIZE_W;
    /** スリーサイズ(H) */
    String THREE_SIZE_H;
    /** 星座 */
    String SIGN;
    /** 出身地 */
    String BRITHPLACE;
    /** 趣味 */
    String HOBBY;
    /** CV */
    String CV;
    /** PATH */
    String IMAGE_PATH;
    /** PATH */
    String DATA_PATH;

}
