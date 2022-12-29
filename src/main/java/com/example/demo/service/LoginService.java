package com.example.demo.service;

import com.example.demo.model.LoginEntity;

public interface LoginService {
	
	//ユーザIDが登録された情報かどうか確認
	LoginEntity userConfirm(String userId, String password);
	
	//ユーザIDに紐づくパスワードを変更する
	boolean userPassChange(String userId, String prevPassword, String nextpassword, String userName);
		
}
