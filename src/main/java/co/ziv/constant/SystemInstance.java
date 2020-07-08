package co.ziv.constant;

public interface SystemInstance {
	String EMPTY = "";
	String ACCOUNT_INDEX = "ACCOUNT_INDEX";
	String NAME_INDEX = "NAME_INDEX";
	String EMAIL_INDEX = "EMAIL_INDEX";
	
	public interface LOGIN {
		String SUCCESS = "登入成功";
		String FAIL = "登入失敗";
		String STATUS_403 = "無權限可執行該動作!";
	}
	
	public interface LOGOUT {
		String SUCCESS = "登出成功";
	}
	
	public interface STATUS {
		String QUERY_SUCCESS = "查詢成功";
		String SAVE_SUCCESS = "新增成功";
		String SAVE_FAIL = "新增失敗";
		String UPDATE_SUCCESS = "更新成功";
		String UPDATE_FAIL = "更新失敗";
		String UPDATE_FAIL_NO_MEMBER = "查無此會員，更新失敗";
	}
	
	public interface Regex {
		String IS_ENGLISH = "[a-zA-Z]+";
		String IS_ENGLISH_NUMBER = "[a-zA-Z0-9]+";
		String IS_NAME = "[\\u4e00-\\u9fa5_a-zA-Z0-9]+";
		String IS_EMAIL = "([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}";
	}
}
