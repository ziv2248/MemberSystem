package co.ziv.response;

public class GeneralResponse {
	private String messageCode;
	private String message;
	
	public GeneralResponse() {
		super();
	}
	
	public GeneralResponse(String messageCode, String message) {
		super();
		this.messageCode = messageCode;
		this.message = message;
	}
	
	public String getMessageCode() {
		return messageCode;
	}
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
