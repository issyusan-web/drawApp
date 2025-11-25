package service;

import dao.UserDAO;
import domain.User;
import dto.UserDTO;

//ユーザー情報を変更するサービス。
public class UserEditService {
	public boolean userEditDo(User user) {
		//受け取ったドメインをDTOへ保存
		UserDTO dto = new UserDTO(user.getId(),user.getLoginId(), user.getPassword(), user.getName(),user.getAuth());
		UserDAO userDAO = new UserDAO();
		int result = userDAO.edit(dto);

		//実行結果によって返す値を変える。
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}
}