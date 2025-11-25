package service;

import dao.UserDAO;
import domain.User;
import dto.UserDTO;

public class UserRegisterService {

	// DB にユーザーが既に存在するかチェック 
	public boolean userEntryConfirm(User user) {
		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = userDAO.selectByLoginId(user.getLoginId());

		// ユーザーが存在しない場合→登録内容確認画面に画面遷移させる 
		if (userDTO == null) {
			return true;
		} else {
			// 既に存在する場合 
			return false;
		}
	}

	//新規登録実行
	public boolean userEntryDo(User user) {
		UserDAO userDAO = new UserDAO();
		UserDTO dto = new UserDTO(user.getLoginId(), user.getPassword(), user.getName());

		try {
			int result = userDAO.insert(dto);
			return result == 1;
		} catch (Exception e) {
			System.err.println("ユーザー登録中にエラー: " + e.getMessage());
			return false;
		}
	}
}
