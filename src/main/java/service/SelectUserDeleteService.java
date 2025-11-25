package service;

import dao.UserDAO;
import domain.User;
import dto.UserDTO;

public class SelectUserDeleteService {
	public User selectUserDelete(int deleteId) {
		UserDAO userDAO = new UserDAO();
		// Controller から受け取ったパラメーターを DAO のメソッドへ渡す
		UserDTO dto = userDAO.selectById(deleteId);
		// nullチェックを追加（重要）
		if (dto == null) {
			return null;
		}
		User user = new User(dto.getId(), dto.getLoginId(), dto.getPassword(), dto.getName(), dto.getAuth());
		return user;
	}
}
