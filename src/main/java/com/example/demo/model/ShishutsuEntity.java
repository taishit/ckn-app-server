package com.example.demo.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Negative;
import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * 登録費用情報 Form
 */
@Data
public class ShishutsuEntity {
    /**
     * ID
     */
	@NotBlank(message = "{require.check.userId}")
    private String userId;
    
    /**
     * 登録番号
     */
    private int entryNo;
    
    /**
     * 費用名称
     */
    @NotBlank(message = "{require.check.costName}")
    private String costName;
    /**
     * 費用種別
     */
    @NotBlank(message = "{require.check.costType}")
    private String costType;
    
    /**
     * 費用
     */
    @Negative(message = "{require.check.cost}")
    private BigDecimal cost;
    
    /**
     * 登録日時
     */
    private Date createDate;
    
    /**
     * 登録者
     */
    private String createName;
    
    /**
     * 更新日時
     */
    private Date updateDate;

    /**
     * 更新者
     */
    private String updateName;
}
