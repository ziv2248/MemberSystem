package co.ziv.service;

import java.util.Map;

import co.ziv.model.MemberAccount;
import co.ziv.request.GeneralRequest;
import co.ziv.response.FindALLResponse;
import co.ziv.response.SaveAndUpdateResponse;

public interface MemberService {
	public FindALLResponse findAll();
	public Map<String, Object> findAll(Integer index, Integer pageSize);
	public SaveAndUpdateResponse add(GeneralRequest request);
	public SaveAndUpdateResponse update(GeneralRequest request);

	public MemberAccount findMemberAccountByAccount(String account);
	public MemberAccount findMemberAccountById(int id);
}
