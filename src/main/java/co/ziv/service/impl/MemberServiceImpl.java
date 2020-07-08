package co.ziv.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import co.ziv.constant.MessageCode;
import co.ziv.constant.SystemInstance;
import co.ziv.model.MemberAccount;
import co.ziv.repository.MemberRepository;
import co.ziv.request.GeneralRequest;
import co.ziv.response.SaveAndUpdateResponse;
import co.ziv.response.FindALLResponse;
import co.ziv.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Override
	public FindALLResponse findAll() {
		List<MemberAccount> members = memberRepository.findAll();
		return new FindALLResponse(MessageCode.SUCCESS, SystemInstance.STATUS.QUERY_SUCCESS, members);
	}
	
	@Override
	public Map<String, Object> findAll(Integer page, Integer rows) {
//        Integer indexPage = (page - 1) * rows;
		List<MemberAccount> members = memberRepository.findAll();
        Page<MemberAccount> pageResult = memberRepository.findAll(PageRequest.of(page , rows, Sort.by("id")));

		Map<String, Object> map = new HashMap<>();
		map.put("total", members.size());
		map.put("rows", pageResult.getContent());
        return map;
	}

	@Override
	public SaveAndUpdateResponse add(GeneralRequest request) {
		MemberAccount member = new MemberAccount(request.getId(), request.getAccount(), request.getPassword(), request.getName(), request.getEmail());
		String errors = doMemeberValidate(member);
		LOGGER.info(request + "\n" + errors);
		SaveAndUpdateResponse response = null;
		if(StringUtils.isBlank(errors)) {
			try {
				memberRepository.save(member);
				response = new SaveAndUpdateResponse(MessageCode.SUCCESS, SystemInstance.STATUS.SAVE_SUCCESS, member);
			} catch (DataIntegrityViolationException e) {
				String messageCode = getMessageCode(e);
				response = new SaveAndUpdateResponse(messageCode, SystemInstance.STATUS.SAVE_FAIL, member);
			}
		} else {
			response = new SaveAndUpdateResponse(MessageCode.FAIL, errors, member);
		}
		return response;
	}
	
	@Override
	public SaveAndUpdateResponse update(GeneralRequest request) {
		MemberAccount member = new MemberAccount(request.getId(), request.getAccount(), request.getPassword(), request.getName(), request.getEmail());
		MemberAccount memberChecked = null;
		if(member.getId() > 0) {
			memberChecked = this.findMemberAccountById(member.getId());
		} else {
			memberChecked = this.findMemberAccountByAccount(member.getAccount());
		}
		
		String errors = doMemeberValidate(member);
		SaveAndUpdateResponse response = null;
		if(memberChecked != null && StringUtils.isBlank(errors)) {
			try {
				memberRepository.saveAndFlush(member);
				response = new SaveAndUpdateResponse(MessageCode.SUCCESS, SystemInstance.STATUS.UPDATE_SUCCESS, member);
			} catch (DataIntegrityViolationException e) {
				String messageCode = getMessageCode(e);
				response = new SaveAndUpdateResponse(messageCode, SystemInstance.STATUS.UPDATE_FAIL, member);
			}
		} else if (StringUtils.isNotBlank(errors)) { 
			response = new SaveAndUpdateResponse(MessageCode.FAIL, errors, member);
		} else {
			response = new SaveAndUpdateResponse(MessageCode.NOT_FOUND_MEMBER, SystemInstance.STATUS.UPDATE_FAIL_NO_MEMBER, member);
		}
		return response;
	}
	
	@Override
	public MemberAccount findMemberAccountByAccount(String account) {
		return memberRepository.findMemberAccountByAccount(account);
	}
	
	@Override
	public MemberAccount findMemberAccountById(int id) {
		return memberRepository.findMemberAccountById(id);
	}
	
//	private int getRealPageIndex(int index, int maxIndex, List<MemberAccount> members) {
//		index = index < 0 ? 0 : index;
//		return index > maxIndex ? maxIndex : index;
//	}
	
	private String getMessageCode(Exception e) {
		String messageCode = MessageCode.ERROR;
		if(e.getMessage().contains(SystemInstance.ACCOUNT_INDEX)) {
			messageCode = MessageCode.ACCOUNT_DUPLICATE;
		} else if (e.getMessage().contains(SystemInstance.NAME_INDEX)) {
			messageCode = MessageCode.NAME_DUPLICATE;
		} else if (e.getMessage().contains(SystemInstance.EMAIL_INDEX)) {
			messageCode = MessageCode.EMAIL_DUPLICATE;
		}
		return messageCode;
	}
	
	private String doMemeberValidate(MemberAccount member) {
		StringBuilder errors = new StringBuilder();
		if(StringUtils.isBlank(member.getAccount()) || member.getAccount().length() < 5 || !member.getAccount().matches(SystemInstance.Regex.IS_ENGLISH)) {
			errors.append("<p>帳號格式不正確</p>");
		} 
		if (StringUtils.isBlank(member.getPassword()) || member.getPassword().length() < 8 || !member.getPassword().matches(SystemInstance.Regex.IS_ENGLISH_NUMBER)) {
			errors.append("<p>密碼格式不正確</p>");
		} 
		if (StringUtils.isBlank(member.getName()) || member.getName().length() < 3 || !member.getName().matches(SystemInstance.Regex.IS_NAME)) {
			errors.append("<p>姓名格式不正確</p>");
		} 
		if (StringUtils.isBlank(member.getEmail()) || !member.getEmail().matches(SystemInstance.Regex.IS_EMAIL)) {
			errors.append("<p>Email格式不正確</p>");
		}
		return errors.toString();
	}
}
