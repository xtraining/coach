package com.coach.resolver;

import java.util.List;

import org.springframework.stereotype.Service;

import com.coach.request.UpdateMemberDetailRequest;
import com.coach.request.UpdateMemberStatusRequest;
import com.coach.response.MemberDetailResponse;
import com.coach.response.MemberNewsResponse;
@Service
public interface IMemberResolver{
	public List<MemberDetailResponse> getMember(Long coachId,
			Long courseId);
	public MemberDetailResponse getMemberDetail(Long coachId, Long courseId, Long memberId);
	public List<MemberNewsResponse> getMemberNews(Long coachId);
	public void updateMember(UpdateMemberDetailRequest request);
	public void updateMemberStatus(UpdateMemberStatusRequest request);
}
