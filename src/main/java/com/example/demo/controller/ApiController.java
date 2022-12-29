package com.example.demo.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;

import com.example.demo.exception.BusinessFailureException;
import com.example.demo.exception.ErrorDetail;
import com.example.demo.model.LoginEntity;
import com.example.demo.model.PasswordChangeEntity;
import com.example.demo.model.RegisterShishutsuForm;
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
	
	@Autowired
	  private MessageSource messageSource;
	
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
	
	/**
	 * スクレイピング処理
	 */
	@GetMapping(value = "scraping")
	public StockPriceInfoResponse sample(@RequestParam String fundNumber) throws IOException{
		
		//返却用のリストを初期化
		String list = "";
		String yield = "";
		String detailInfo = "";
		String haitoSeikoText = "";
		String haitoAmountText = "";
		Integer haitoAmount = 0;
		String haitoRimawariText = "";
		int HaitoInfoCnt = 0;
		int HaitoRimawariInfoCnt = 0;
		String amount = "";
		Integer stockAmount = 0;
		StockPriceInfoResponse responseDto = new StockPriceInfoResponse();
		
		//数値チェック
		boolean isNumeric = StringUtils.isNumeric(fundNumber);
		
		if(isNumeric) {
			//URL作成
			String accessUrl = "https://minkabu.jp/stock/" + fundNumber;
			Document doc = Jsoup.connect(accessUrl).get();
			
			String dividendAccessUrl = "https://kabuyoho.jp/reportDps?bcode=" + fundNumber;
			Document dividendDoc = Jsoup.connect(dividendAccessUrl).get();
			
			String pbrAccessUrl =  "https://kabuyoho.jp/sp/reportTop?bcode=" + fundNumber;
			Document pbrDoc = Jsoup.connect(pbrAccessUrl).get();
			
			//配当性向のためのオブジェクト取得
			Elements haitoSeikoInfo = dividendDoc.select("td.plus");
			
			//PBRのためのオブジェクト取得
			Elements pbrInfo = pbrDoc.select("span.plus");
			 
	        // 各記事のaタグを取得。jQueryのセレクターと同じ感じで記載
	        Elements newsHeadlines = doc.select("div.stock_price");
	        Elements fundList = doc.select("td.ly_vamd_inner.ly_colsize_9_fix.tar.wsnw");
	        
	        //配当性向の要素を取得
	        for (Element haitoSeiko : haitoSeikoInfo) {
	        	HaitoInfoCnt++;
	        	
	        	//配当金取得
	        	if (HaitoInfoCnt == 4) {
	        		haitoAmountText = haitoSeiko.ownText();
	        		haitoAmount = Integer.parseInt(haitoAmountText.substring(0, 3));
	        	}
	        	
	        	//配当性向取得
	        	if (HaitoInfoCnt == 7) {
	        		haitoSeikoText = haitoSeiko.ownText();
	        	}
	        }
	        
	        //PBRの要素を取得
	        for (Element pbr : pbrInfo) {
	        	HaitoRimawariInfoCnt++;
	        	
	        	if (HaitoRimawariInfoCnt == 4) {
	        		haitoRimawariText = pbr.ownText();
	        		
	        	}
	        }
	        System.out.println("配当性向：" + haitoSeikoText);
	        System.out.println("配当金：" + haitoAmount);
	        //配当性向
	        responseDto.setHaitoseiko(haitoSeikoText);
	        //配当金
	        responseDto.setDividendAmount(haitoAmount);
	        
	        //PBR
	        responseDto.setPbr(haitoRimawariText);
	        for (Element fundInfo : fundList) {
	        	detailInfo = fundInfo.ownText();
	        	if (detailInfo.contains("%")) {
	        		yield = detailInfo;
	        	}
	        }
	        //株価取得
	        for (Element headline : newsHeadlines) {
	            System.out.println("現在の株価: " + headline.ownText() + "0" + "円");
	            list = headline.ownText();
	        }
	        for(int i = 0; i <list.length(); i++){
	        	String str = list.substring(i, i+1);
	        	if(NumberUtils.isCreatable(str)) {
	        		amount = amount.concat(str);
	        	}
	        }
	        stockAmount = Integer.parseInt(amount);
	        
	        
			//株価
			responseDto.setStockPrice(stockAmount);
			//配当利回り
			responseDto.setDividendYield(yield);
		}
		
		
		return responseDto;
	}
	
}