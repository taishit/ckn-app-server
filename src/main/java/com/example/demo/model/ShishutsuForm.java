package com.example.demo.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShishutsuForm {

/*ID*/
private int id;

public ShishutsuForm(int id, String name, int cost, String address) {
	this.id = id;
	this.name = name;
	this.cost = cost;
	this.address = address;
}

	
/*支出項目*/
@NotEmpty(message = "支出項目を入力してください")
private String name;

/*支出金額*/
@NotNull(message = "支出金額を入力してください")
private int cost;

/*住所*/
private String address;



}