package validation;

import java.util.ArrayList;
import java.util.List;

public class Validation {

	//受け取ったListの要素に空文字が含まれているかチェックするメソッド。
	public List<String> isBlank(List<String> list) {
		List<String> newList = new ArrayList<String>();
		for (String s : list) {
			//要素が空文字でないなら、新しいListに追加
			if (s != null && !s.trim().isEmpty()) {
				newList.add(s);
			}
		}
		return newList;
	}

	// エラーメッセージを保持する String 型の errorMsgList という名前のリスト 
	private List<String> errorMsgList;

	public Validation() {
		// コンストラクタ。new したときに メンバ変数のリストを初期化 
		this.errorMsgList = new ArrayList<>();
	}

	// エラーメッセージが 1 つでも存在するかどうかをチェックするメソッド 
	public boolean hasErrorMsg() {
		if (errorMsgList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	// 指定されたテキストが null（何も入ってない状態）
	// または空文字（スペースで空白の文字を意図的に入力している状態）かどうかをチェック
	// null か空文字ならエラーメッセージを追加 
	public void isBlank(String textName, String text) {
		if (text == null || text.isEmpty()) {
			this.errorMsgList.add(textName + "が入力されていません");
		}
	}

	// テキストの長さが最小値以上かどうかをチェック。超えている場合はエラーメッセージを追加
	public void length(String textName, String text, int min, int max) {
		if (text == null || text.length() < min || text.length() > max) {
			this.errorMsgList.add(textName + "は、" + min + "文字以上、" + max + "文字以下で入力してください");
		}
	}

	// テキストの長さが最大値以下かどうかをチェック。超えている場合はエラーメッセージを追加 
	public void length(String textName, String text, int max) {
		if (text == null || text.length() > max) {
			this.errorMsgList.add(textName + "は、" + max + "文字以内で入力してください");
		}
	}

	// エラーメッセージをリストに追加 
	public void addErrorMsg(String msg) {
		errorMsgList.add(msg);
	}

	// 保持しているエラーメッセージのリストを返す 
	public List<String> getErrorMsgList() {
		return errorMsgList;
	}

}