package com.example.demo.service.serviceImpl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ShishutsuEntity;
import com.example.demo.repository.ShishutsuRepository;
import com.example.demo.service.ShishutsuService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShishutsuServiceImpl implements ShishutsuService {

	//ShainRepositoryのDI
	@Autowired
	private  ShishutsuRepository shishutsuRepository;


	@Override
	public List<ShishutsuEntity> findByNo(String userId) {

		List<ShishutsuEntity> resultList = shishutsuRepository.selectByNo(userId);
		
		List<ShishutsuEntity> shishutsuList = new ArrayList<ShishutsuEntity>(); 
		
		//for文を回して取得結果をリストに詰める
		for(ShishutsuEntity shishutsuEntity : resultList) {
			
			ShishutsuEntity entity = new ShishutsuEntity();
			
			//繰り返しているshishutsuEntityの要素をnewしたEntityに詰める
			entity.setUserId(shishutsuEntity.getUserId());
			entity.setEntryNo(shishutsuEntity.getEntryNo());
			entity.setCost(shishutsuEntity.getCost());
			entity.setCostName(shishutsuEntity.getCostName());
			entity.setCostType(shishutsuEntity.getCostType());
			entity.setCreateDate(shishutsuEntity.getCreateDate());
			entity.setCreateName(shishutsuEntity.getCreateName());
			entity.setUpdateDate(shishutsuEntity.getUpdateDate());
			entity.setUpdateName(shishutsuEntity.getUpdateName());
			
			//すべての要素を詰め終えたらリストにaddする
			shishutsuList.add(entity);
		}
	
		return shishutsuList;
	}

	@Override
	public void registInfo(ShishutsuEntity registerShishutsuForm) {
		
		//UserId取得
		String userId = registerShishutsuForm.getUserId();
		//UserId取得
		String costName = registerShishutsuForm.getCostName();
		//UserId取得
		String costType = registerShishutsuForm.getCostType();
		//UserId取得
		BigDecimal cost = registerShishutsuForm.getCost();
		//UserName設定
		String userName = "taishi_t";
		
		//EntryNo設定
		//1.EntryNoの最大を保持するための変数を定義
		int entryMaxNo = 0;
		
		//2.ユーザに紐づく最新の情報を取得
		List<ShishutsuEntity> resultList = shishutsuRepository.selectByNo(userId);
		
		//3.for文を回してentryNoの最大値を設定
		for(ShishutsuEntity shishutsuEntity : resultList) {
			//4.現在のentryMaxNoよりも取得したentryNoが大きい場合、最大値として設定
			if(entryMaxNo < shishutsuEntity.getEntryNo()) {
				entryMaxNo = shishutsuEntity.getEntryNo();	
			}
		}
		//5.entryMaxNoをインクリメント
		entryMaxNo += 1;
		
		shishutsuRepository.registInfo(userId, costName, costType, cost, userName, entryMaxNo);
	}

	@Override
	public void deleteInfo(String userId, int entryNo) {
		//削除用SQL呼び出し
		shishutsuRepository.deleteInfo(userId, entryNo);

	}
	
	@Override
	public void updateInfo(ShishutsuEntity updateShishutsuForm) {
		//userId設定
		String userId = updateShishutsuForm.getUserId();
		//costName設定
		String costName = updateShishutsuForm.getCostName();
		//costType設定
		String costType = updateShishutsuForm.getCostType();
		//cost設定
		BigDecimal cost = updateShishutsuForm.getCost();
		//userName設定
		String userName = "taishi_t";
		//entryNo設定
		int entryNo = updateShishutsuForm.getEntryNo();
		
		//更新用SQL呼び出し
		shishutsuRepository.updateInfo(userId, costName, costType, cost, userName, entryNo);

	}

}