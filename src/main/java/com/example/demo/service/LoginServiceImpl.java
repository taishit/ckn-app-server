package com.example.demo.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.LoginEntity;
import com.example.demo.repository.UserConfirmRepository;
import com.example.demo.repository.UserMstRepository;


@Service
public class LoginServiceImpl implements LoginService {

	//ShainRepositoryのDI
	@Autowired
	private  UserConfirmRepository userConfirmRepository;
	
	@Autowired
	private  UserMstRepository userMstRepository;



	@Override
	public LoginEntity userConfirm(String userId, String password) {
		//変数の初期化
		String userName = "";
		LoginEntity loginEntity = new LoginEntity();
		
		//検索処理
		userName = userConfirmRepository.selectConfirm(userId, password);
		
		//設定処理
		if(StringUtils.isNotEmpty(userName)) {
			loginEntity.setLoginResult(true);
			loginEntity.setUserName(userName);
		}
		
		return loginEntity;
	}
	
	@Override
	public boolean userPassChange(String userId, String prevPassword, String nextPassword, String userName) {
		Boolean confirmResult = false;
		//TODO prevPassとDBに登録されている現在のパスワードが一致するかどうかをチェックする。現在パスワードが誤って連携される場合はエラーにする。
		
		//パスワードが変更前と同じかどうかチェック
		if(prevPassword != nextPassword) {
			//パスワードを更新する
			userMstRepository.updatePassword(userId, nextPassword, userName);
			confirmResult = true;
		}
		return confirmResult;
	}

}