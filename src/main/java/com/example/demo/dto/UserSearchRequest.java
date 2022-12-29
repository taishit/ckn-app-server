package com.example.demo.dto;

import java.io.Serializable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
/**
 * ユーザー情報 検索用リクエストデータ
 */
@Data
@Getter
@Setter
public class UserSearchRequest implements Serializable {
  /**
   * ユーザーID
   */
  private int id;
  
  public int getId() {
	  return id;
	  }

  public void setId(int id) {
  this.id = id;
  }
}
