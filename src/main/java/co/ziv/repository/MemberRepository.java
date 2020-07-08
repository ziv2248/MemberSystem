package co.ziv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.ziv.model.MemberAccount;

public interface MemberRepository extends JpaRepository<MemberAccount, Integer>{
	public MemberAccount findMemberAccountByAccount(String account);
	public MemberAccount findMemberAccountById(int id);
	public List<MemberAccount> findAll();
}