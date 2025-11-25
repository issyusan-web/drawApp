package service;

import dao.UserDAO;

//ユーザー情報を削除するためのサービス。
public class UserDeleteService {
	public boolean userDeleteDo(int id) {
		// 削除したいユーザーIDをDTOに格納
		UserDAO userDAO = new UserDAO();
		int result = userDAO.delete(id);

		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}
}