package com.example.demo.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.Getter;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

//検査例外(RuntimeException)を継承
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
@Getter
public class  BusinessFailureException extends RuntimeException  {

	private static final long serialVersionUID = 1L;
	private ErrorDetail errorDetails;
	
	@Autowired 
    private MessageSource messageSource;
	
	public BusinessFailureException(BindingResult bindingResult) {
		
		//親クラスにバリデーションエラーのメッセージを渡す
		super("VALIDATION ERROR");
		
		ErrorDetail errorDetail = new ErrorDetail();
		List<String> messageList = new ArrayList<String>();
		
		//errorDetail.setDetailMessage("詳細なメッセージ");
		List<ObjectError> allErrors  = bindingResult.getAllErrors();
		 for(ObjectError error : allErrors) {
			 //エラー(文字列)をメッセージリストにaddしていく
			 //messageList.add(error.toString());
			 messageList.add(error.getDefaultMessage());
			
		 }
		 //作成したエラーリストをエラー詳細リストに設定
		 errorDetail.setDetailMessage(messageList);
		 this.errorDetails = errorDetail;
	}
	
	public void BusinessFailureExceptionHandle(String message){
		System.out.println("ERROR");
		
		
	}
}
