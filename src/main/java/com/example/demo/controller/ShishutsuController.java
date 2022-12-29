package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.model.RegisterShishutsuForm;
import com.example.demo.model.ShishutsuEntity;
import com.example.demo.service.ShishutsuService;

@Controller
public class ShishutsuController {
	
	private final ShishutsuService shishutsuService;

	//ShainServiceのDI
	public ShishutsuController(ShishutsuService shishutsuService) {
	this.shishutsuService = shishutsuService;
	}

@ModelAttribute
public RegisterShishutsuForm InitForm() {
return new RegisterShishutsuForm();
}

@RequestMapping("/input")
public String index() {
return "top/index";
}

//管理画面
@RequestMapping("/user/namagement")
public String management() {
return "user/namagement";
}

//User画面からTOP画面に遷移する
@RequestMapping("user/top/input")
public String top() {
return "top/index";
}

/**
 * 支出情報登録
 * @param userSearchRequest リクエストデータ
 * @param model Model
 * @return 支出情報登録画面
 */
@RequestMapping("/user/add")
public String add() {
return "user/add";
}

/**
 * 支出情報検索
 * @param userSearchRequest リクエストデータ
 * @param model Model
 * @return 支出情報検索画面
 */
@RequestMapping("/user/search")
public String search() {
return "user/search";
}

/**
 * 支出情報情報登録
 * @param userSearchRequest リクエストデータ
 * @param model Model
 * @return 支出情報登録画面
 */
@RequestMapping(value = "/user/register")
public String register() {
		return "user/register";
}

/**
 * ユーザー情報検索
 * @param registerShishutsuFrom リクエストデータ
 * @param model Model
 * @return 情報検索画面
 */
@RequestMapping(value = "/user/search_out", method = RequestMethod.POST)
public String searchOut(RegisterShishutsuForm registerShishutsuFrom, Model model) {
	String userId = registerShishutsuFrom.getUserId();
	List<ShishutsuEntity>List = shishutsuService.findByNo(userId);
	model.addAttribute("shishutsuEntityList", List);
	
		return "user/searchOut";
}

/**
 * ユーザー情報登録
 * @param registerShishutsuFrom リクエストデータ
 * @param model Model
 * @return 情報登録画面
 */
@RequestMapping(value = "/user/register_out", method = RequestMethod.POST)
public String registerOut(ShishutsuEntity registerShishutsuFrom, Model model) {
	shishutsuService.registInfo(registerShishutsuFrom);
	model.addAttribute("userId", registerShishutsuFrom.getUserId());
	model.addAttribute("costName", registerShishutsuFrom.getCostName());
	model.addAttribute("costType", registerShishutsuFrom.getCostType());
	model.addAttribute("cost", registerShishutsuFrom.getCost());
	
		return "user/registerOut";
}

}