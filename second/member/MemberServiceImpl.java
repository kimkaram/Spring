package com.kh.second.member.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.second.member.model.dao.MemberDao;
import com.kh.second.member.model.vo.Member;

@Service("memberService")
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDao memberDao;
	
	@Override
	public Member loginCheck(Member member) {
		
		return memberDao.loginCheck(member);
	}

	@Override
	public int insertMember(Member member) {
		return memberDao.insertMember(member);
	}

	@Override
	public int updateMember(Member member) {
		return memberDao.updateMember(member);
	}

	@Override
	public int deleteMember(String userid) {
		return memberDao.deleteMember(userid);
	}

	@Override
	public ArrayList<Member> memberAll() {
		return null;
	}

	@Override
	public Member selectMember(String userid) {
		//System.out.println("service : " + userid);
		return memberDao.selectMember(userid);
	}

	
}
