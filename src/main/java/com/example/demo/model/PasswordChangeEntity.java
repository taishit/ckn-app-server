package com.example.demo.model;

import lombok.Data;

/**
 * 登録費用情報 Form
 */
@Data
public class PasswordChangeEntity {
    /**
     * ユーザID
     */
    private String userId;
    
    /**
     * 変更前パスワード
     */
    private String prevPassword;
    
    /**
     * 変更後パスワード
     */
    private String nextPassword;

    /**
     * ユーザ名
     */
    private String userName;
}
