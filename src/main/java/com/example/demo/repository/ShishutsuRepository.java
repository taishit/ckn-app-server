package com.example.demo.repository;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.model.ShishutsuEntity;

public interface ShishutsuRepository {
	
	//支出を検索
	List<ShishutsuEntity> selectByNo(String userId);
	
	//支出を更新
	void updateInfo(String userId, String costName, String costType, BigDecimal cost, String userName, int entryNo);
	
	//支出を登録
	void registInfo(String userId, String costName, String costType, BigDecimal cost, String userName, int entryNo);
	
	//支出を削除
	void deleteInfo(String userId ,int entryNo);

}
