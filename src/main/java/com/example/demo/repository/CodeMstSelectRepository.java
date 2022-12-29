package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.CodeMstEntity;

public interface CodeMstSelectRepository {
	
	//コード種別に紐づくコード情報を検索
	List<CodeMstEntity> selectCodeList(String codeType);

}
