package co.ziv.response;

import java.util.List;

import co.ziv.model.MemberAccount;

public class FindPageResponse extends GeneralResponse {
	private int index;
	private int totalPage;
	private List<MemberAccount> members;

	public FindPageResponse() {
		super();
	}

	public FindPageResponse(String messageCode, String message, int index, int totalPage, List<MemberAccount> members) {
		super(messageCode, message);
		this.index = index;
		this.totalPage = totalPage;
		this.members = members;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<MemberAccount> getMembers() {
		return members;
	}

	public void setMembers(List<MemberAccount> members) {
		this.members = members;
	}
}
