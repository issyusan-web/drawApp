package service;

import dao.UserDAO;
import domain.User;
import dto.UserDTO;

//ユーザーが入力した情報をチェックするサービス
public class UserLoginService {
	public User loginCheck(String loginId, String password) {
		UserDAO userDAO = new UserDAO();
		// Controller から受け取ったパラメーターを DAO のメソッドへ渡す
		UserDTO userDTO = userDAO.selectByLoginId(loginId);

		// DAO での select 成功時は UserDTO にログインしたユーザー情報が格納されている
		// ユーザーが入力した値と DB の値が等しいかチェック
		if (userDTO != null && userDTO.getPassword().equals(password)) {
			// 等しい場合は DTO の情報を Domain に移行
			User user = new User(userDTO.getLoginId(), userDTO.getPassword(), userDTO.getName());
			user.setId(userDTO.getId());
			user.setAuth(userDTO.getAuth());
			return user;
		}
		return null;
	}

}
