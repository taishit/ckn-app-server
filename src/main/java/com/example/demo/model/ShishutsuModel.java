package com.example.demo.model;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 登録費用情報 Entity
 */
@Data
@Getter
@Setter
public class ShishutsuModel {
    /**
     * ID
     */
    private Long userId;
    
    /**
     * 費用名称
     */
    private String costName;
    /**
     * 費用種別
     */
    private String costType;
    
    /**
     * 費用
     */
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
