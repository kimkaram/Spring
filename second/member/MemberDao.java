package com.kh.second.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.kh.second.member.model.vo.Member;

@Repository("memberDao")
public class MemberDao {
	
	@Autowired
	private SqlSessionTemplate mybatisSession;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	public Member loginCheck(Member member) {
		Member loginMember =  mybatisSession.selectOne("memberMapper.loginCheck", member);
		
		if(!bcryptPasswordEncoder.matches(member.getUserpwd(), loginMember.getUserpwd())) {
			loginMember = null;
		}
		
		return loginMember;
	}

	public int insertMember(Member member) {
		return mybatisSession.insert("memberMapper.insertMember", member);
	}

	/*public ArrayList<Member> memberAll() {
		return mybatisSession.selectList("memberMapper.selectList");
	}
*/
	public int updateMember(Member member) {
		return mybatisSession.update("memberMapper.updateMember", member);
	}

	public int deleteMember(String userid) {
		return mybatisSession.delete("memberMapper.deleteMember", userid);
	}

	public Member selectMember(String userid) {
		//System.out.println("dao : " + userid);
		return mybatisSession.selectOne("memberMapper.selectMember", userid);
	}

}
