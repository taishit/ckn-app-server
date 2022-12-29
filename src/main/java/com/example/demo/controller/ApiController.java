package com.example.demo.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.BusinessFailureException;
import com.example.demo.model.LoginEntity;
import com.example.demo.model.PasswordChangeEntity;
import com.example.demo.model.ShishutsuEntity;
import com.example.demo.model.ShishutsuForm;
import com.example.demo.model.StockPriceInfoResponse;
import com.example.demo.service.LoginService;
import com.example.demo.service.ShishutsuService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//スクレイピングおためし
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api")
@Configuration
public class ApiController {
	
	private final ShishutsuService shishutsuService;
	
    //ShainServiceのDI
	public ApiController(ShishutsuService shishutsuService) {
	this.shishutsuService = shishutsuService;
	}
	
	@Autowired
	private LoginService loginService;

	@RequestMapping("hello")
    private String hello() throws JsonProcessingException {
		ShishutsuForm shishutsuForm = new ShishutsuForm(1,"Amazon Prime",500,"東京都");
		String json = new ObjectMapper().writeValueAsString(shishutsuForm);
		System.out.println("Hello sample");
        return json;
	}
	
	/**
	 * ユーザー情報検索
	 * @param ShishutsuEntity リクエストデータ
	 * @param model Model
	 * @return ユーザ支出情報
	 * @throws JsonProcessingException 
	 */
	@PostMapping(value = "search")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<ShishutsuEntity> search(@RequestBody ShishutsuEntity request) throws JsonProcessingException {
		String userId = request.getUserId();
		
		return shishutsuService.findByNo(userId);
	}
	
	@Value(value = "${app.config.error-code}")
	String errMsg;
	
	/**
	 * ユーザー情報登録
	 * @param ShishutsuEntity リクエストデータ
	 * @param model Model
	 * @return 
	 * @return なし
	 * @throws BusinessFailureException 
	 */
	@PostMapping(value = "regist")
	@CrossOrigin(origins = "http://localhost:4200")
	public  String registInfo(@Valid @RequestBody ShishutsuEntity request ,BindingResult errors){
		 if (errors.hasErrors()) {
			 throw new BusinessFailureException(errors);
		    }
		//登録処理
		  shishutsuService.registInfo(request);
		  return "Hello";
	
	}
	
	/**
	 * ユーザー情報削除
	 * @param ShishutsuEntity リクエストデータ
	 * @param model Model
	 * @return ユーザ支出情報
	 */
	@PostMapping(value = "delete")
	@CrossOrigin(origins = "http://localhost:4200")
	public void deleteInfo(@RequestBody ShishutsuEntity request){
		String userId = request.getUserId();
		int entryNo = request.getEntryNo();
		shishutsuService.deleteInfo(userId,entryNo);
	}
	
	/**
	 * ユーザー情報更新
	 * @param ShishutsuEntity リクエストデータ
	 * @param model Model
	 * @return なし
	 */
	@PostMapping(value = "update")
	@CrossOrigin(origins = "http://localhost:4200")
	public void updateInfo(@RequestBody ShishutsuEntity request){
		shishutsuService.updateInfo(request);
	}
	
	/**
	 * ユーザーログイン処理
	 * @param LoginEntity リクエストデータ
	 * @param model Model
	 * @return LoginEntity ユーザログイン結果
	 * @throws JsonProcessingException 
	 */
	@PostMapping(value = "userConfirm")
	@CrossOrigin(origins = "http://localhost:4200")
	public LoginEntity userConfirm(@RequestBody LoginEntity request) throws JsonProcessingException {
		String userId = request.getUserId();
		String password = request.getPassword();
		
		return loginService.userConfirm(userId, password);
	}
	
	/**
	 * パスワード変更処理
	 * @param passwordChangeEntity リクエストデータ
	 * @param model Model
	 * @return boolean パスワード変更結果
	 */
	@PostMapping(value = "passwordChange")
	@CrossOrigin(origins = "http://localhost:4200")
	public boolean userPasswordChange(@RequestBody PasswordChangeEntity request){
		String userId = request.getUserId();
		String prevPassword = request.getPrevPassword();
		String nextPassword = request.getNextPassword();
		String userName = request.getUserName();
		
		return loginService.userPassChange(userId, prevPassword, nextPassword, userName);
	}
	
}