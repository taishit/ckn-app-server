package com.example.demo.service;

import java.util.List;

import com.example.demo.model.CodeMstEntity;

public interface MasterAccessService {
	
	//コード種別に対応するコード値の一覧を取得する
	List<CodeMstEntity> selectCodeList(String codeType);
		
}
