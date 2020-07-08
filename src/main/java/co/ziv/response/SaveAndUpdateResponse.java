package co.ziv.response;

import co.ziv.model.MemberAccount;

public class SaveAndUpdateResponse extends GeneralResponse {
	private MemberAccount member;

	public SaveAndUpdateResponse() {
		super();
	}

	public SaveAndUpdateResponse(String messageCode, String message, MemberAccount member) {
		super(messageCode, message);
		this.member = member;
	}

	public MemberAccount getMember() {
		return member;
	}

	public void setMember(MemberAccount member) {
		this.member = member;
	}
}
