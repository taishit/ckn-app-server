package com.example.demo.service;

import java.util.List;

import com.example.demo.model.RegisterShishutsuForm;
import com.example.demo.model.ShishutsuEntity;
import com.example.demo.model.ShishutsuEntityList;
import com.example.demo.model.ShishutsuForm;

public interface ShishutsuService {
	
	//ユーザIDから紐づく費用情報を検索
	List<ShishutsuEntity> findByNo(String userId);
		
	//支出情報を更新する
	void updateInfo(ShishutsuEntity shishutsuEntity);
		
	//支出情報を登録する
	void registInfo(ShishutsuEntity shishutsuEntity);
	
	//ユーザIDから紐づく費用情報を削除
	void deleteInfo(String userId, int entryNo);

}
