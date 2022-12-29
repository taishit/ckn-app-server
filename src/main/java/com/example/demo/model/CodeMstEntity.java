package com.example.demo.model;

import java.util.Date;
import lombok.Data;

/**
 * 登録費用情報 Form
 */
@Data
public class CodeMstEntity {
    /**
     * No
     */
    private int no;
    
    /**
     * コード種別
     */
    private String codeType;
    
    /**
     * コード名称
     */
    private String codeName;
    
    /**
     * コード値
     */
    private String codeValue;
    
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
