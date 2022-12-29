package com.example.demo.controller;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;

import com.example.demo.model.CodeMstEntity;
import com.example.demo.model.RegisterShishutsuForm;
import com.example.demo.model.ShishutsuEntity;
import com.example.demo.model.ShishutsuForm;
import com.example.demo.service.MasterAccessService;
import com.example.demo.service.ShishutsuService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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