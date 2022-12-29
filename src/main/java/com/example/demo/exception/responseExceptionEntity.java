package com.example.demo.exception;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class responseExceptionEntity {
    
    /**
     * TimeStamp
     */
	@JsonProperty("timestamp")
    private ZonedDateTime exceptionOccurrenceTime;
	
    /**
     * ステータス
     */
	@JsonProperty("status")
    private int status;
	
    /**
     * エラー
     */
	@JsonProperty("error")
    private String error;
    
    /**
     * エラーメッセージ
     */
	@JsonProperty("message")
    private String message;
    
    /**
     * エラー詳細
     */
	@JsonProperty("details")
    private ErrorDetail details;
    
    /**
     * path
     */
	@JsonProperty("path")
    private String path;
    

}
