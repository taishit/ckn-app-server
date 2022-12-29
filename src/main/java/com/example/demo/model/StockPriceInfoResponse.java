package com.example.demo.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Negative;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import lombok.Data;

/**
 * 株価情報 Response
 */
@Data
public class StockPriceInfoResponse {
    /**
     * 株価
     */
    private Integer stockPrice;
    
    /**
     * 配当性向
     */
    private String haitoseiko;
    
    /**
     * 配当金
     */
    private Integer dividendAmount;
    
    /**
     * 配当利回り
     */
    private String dividendYield;
    
    /**
     * PBR
     */
    private String pbr;
    
}
