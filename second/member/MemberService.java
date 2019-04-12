package com.kh.second.member.model.service;

import java.util.ArrayList;

import com.kh.second.member.model.vo.Member;

public interface MemberService {
	Member loginCheck(Member member);
	int insertMember(Member member);
	int updateMember(Member member);
	int deleteMember(String userid);
	ArrayList<Member> memberAll();
	Member selectMember(String userid);
}

