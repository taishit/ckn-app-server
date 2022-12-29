package com.example.demo.model;

import lombok.Data;

/**
 * ログイン用情報 Form
 */
@Data
public class LoginEntity {
    /**
     * ユーザID
     */
    private String userId;
    
    /**
     * ユーザー名
     */
    private String userName;
    
    /**
     * パスワード
     */
    private String password;
    
    /**
     * ログイン結果
     */
    private boolean loginResult;

}
