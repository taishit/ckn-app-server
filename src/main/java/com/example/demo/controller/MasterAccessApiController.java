package com.example.demo.controller;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CodeMstEntity;
import com.example.demo.service.MasterAccessService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/master")
public class MasterAccessApiController {
	
	private final MasterAccessService masterAccessService;
	
    //ShainServiceのDI
	public MasterAccessApiController(MasterAccessService masterAccessService) {
	this.masterAccessService = masterAccessService;
	}
	
	/**
	 * ユーザー情報検索
	 * @param ShishutsuEntity リクエストデータ
	 * @param model Model
	 * @return ユーザ支出情報
	 * @throws JsonProcessingException 
	 */
	@PostMapping(value = "code")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<CodeMstEntity> search() {
		String codeType = "費用種別";
		List<CodeMstEntity> CodeList = masterAccessService.selectCodeList(codeType);
		
		return CodeList;
	}
}