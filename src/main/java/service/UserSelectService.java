package service;

import java.util.List;

import dao.UserDAO;
import dto.UserDTO;

//ユーザーを取得するサービス。
public class UserSelectService {
	//全ユーザー取得
	public List<UserDTO> getAllUsers() {
		UserDAO dao = new UserDAO();
		List<UserDTO> userList = dao.selectAll();
		return userList;
	}

	//ID指定でユーザー取得
	public UserDTO findById(int id) {
		UserDAO dao = new UserDAO();
		UserDTO dto = dao.selectById(id);
		return dto;
	}
}
