package com.example.demo.exception;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

	
	//Controller から throw される BusinessFailureException を捕捉
	@ExceptionHandler(BusinessFailureException.class)
	public ResponseEntity<Object> handleMyException(BusinessFailureException exception, WebRequest request) {
	    HttpHeaders headers = new HttpHeaders();
	
	    return super.handleExceptionInternal(exception,
	            createErrorResponseBody(exception, request),
	            headers,
	            HttpStatus.BAD_REQUEST,
	            request);
	    }

	    // レスポンスのボディ部を作成
	    private responseExceptionEntity createErrorResponseBody(BusinessFailureException exception, WebRequest request) {

	    	responseExceptionEntity responseExceptionEntity = new responseExceptionEntity();
	        int responseCode = HttpStatus.BAD_REQUEST.value();
	        String responseErrorMessage = HttpStatus.BAD_REQUEST.getReasonPhrase();
	        String uri = ((ServletWebRequest) request).getRequest().getRequestURI();

	        responseExceptionEntity.setExceptionOccurrenceTime(ZonedDateTime.now());
	        responseExceptionEntity.setStatus(responseCode);
	        responseExceptionEntity.setError(responseErrorMessage);
	        responseExceptionEntity.setMessage(exception.getMessage());
	        responseExceptionEntity.setPath(uri);
	        responseExceptionEntity.setDetails(exception.getErrorDetails());

	        return responseExceptionEntity;
	    }
	
	
}
