package com.example.demo.exception;

import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class ErrorDetail {
	
	@JsonProperty("detailMessage")
    List<String> detailMessage;

}
