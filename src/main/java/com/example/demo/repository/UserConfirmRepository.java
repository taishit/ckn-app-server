package com.example.demo.repository;

public interface UserConfirmRepository {
	
	//ログイン情報を取得
	String selectConfirm(String userId, String password);

}
