package com.example.demo.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;
@Data
@Entity
@Table(name="AV_IDOL_DATA")
public class AVIdolDataDto {
    /**  */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")

    private Integer ID; 
    /** 名前 */
	@Column(name="NAME")
	@NotBlank(message = "名前を入力してください")
	private String NAME;
    /** ふりがな */
	@Column(name="FURIGANA")
	@NotBlank
	private String FURIGANA;
    /** タイプ */
	@Column(name="TYPE")
	private String TYPE;
    /** 年齢 */
	@Column(name="AGE")
	private String AGE;
    /** 身長 */
	@Column(name="HEIGHT")
	private String HEIGHT;
    /** 体重 */
	@Column(name="WEIGHT")
	private String WEIGHT;
    /** 誕生日 */
	@Column(name="BRITHDAY")
	private String BRITHDAY;
    /** 血液型 */
	@Column(name="BLOOD_TYPE")
	private String BLOOD_TYPE;
    /** 利き手 */
	@Column(name="DOMINANT_HAND")
	private String DOMINANT_HAND;
    /** スリーサイズ(B) */
	@Column(name="THREE_SIZE_B")
	private String THREE_SIZE_B;
    /** スリーサイズ(W) */
	@Column(name="THREE_SIZE_W")
	private String THREE_SIZE_W;
    /** スリーサイズ(H) */
	@Column(name="THREE_SIZE_H")
	private String THREE_SIZE_H;
    /** 星座 */
	@Column(name="SIGN")
	private String SIGN; 
    /** 出身地 */
	@Column(name="BRITHPLACE")
	private String BRITHPLACE;
    /** 趣味 */
	@Column(name="HOBBY")
	private String HOBBY;
    /** CV */
	@Column(name="CV")
	private String CV;
    /** PATH */
	@Column(name="IMAGE_PATH" )
	private String IMAGE_PATH;
    /** PATH */
	@Column(name="DATA_PATH" )
	private String DATA_PATH;

}
