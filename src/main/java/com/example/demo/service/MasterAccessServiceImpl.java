package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CodeMstEntity;
import com.example.demo.repository.CodeMstSelectRepository;

import java.util.List;

@Service
public class MasterAccessServiceImpl implements MasterAccessService {

	//ShainRepositoryのDI
	@Autowired
	private  CodeMstSelectRepository codeMstSelectRepository;


	@Override
	public List<CodeMstEntity> selectCodeList(String codeType) {
		//formから値を取得
		List<CodeMstEntity> resultList = codeMstSelectRepository.selectCodeList(codeType);
		
		return resultList;
	}

}