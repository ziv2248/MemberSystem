package co.ziv.response;

import java.util.List;

import co.ziv.model.MemberAccount;

public class FindALLResponse extends GeneralResponse {
	private List<MemberAccount> members;

	public FindALLResponse() {
		super();
	}

	public FindALLResponse(String messageCode, String message, List<MemberAccount> members) {
		super(messageCode, message);
		this.members = members;
	}

	public List<MemberAccount> getMembers() {
		return members;
	}

	public void setMembers(List<MemberAccount> members) {
		this.members = members;
	}
}
