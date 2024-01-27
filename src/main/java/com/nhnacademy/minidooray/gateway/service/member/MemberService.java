package com.nhnacademy.minidooray.gateway.service.member;

import com.nhnacademy.minidooray.gateway.domain.member.request.MemberAddProjectRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.member.request.MemberListRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.member.response.MemberListResponseDTO;
import java.util.List;

public interface MemberService {
  List<MemberListResponseDTO> readMemberList(MemberListRequestDTO memberListRequestDTO);
  boolean createMemberInProject(MemberAddProjectRequestDTO memberAddProjectRequestDTO);
}
