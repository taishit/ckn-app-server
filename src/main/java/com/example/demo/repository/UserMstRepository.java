package com.example.demo.repository;

public interface UserMstRepository {
	
	//新しいパスワードに更新
	void updatePassword(String userId, String password, String userName);

}
