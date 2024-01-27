package com.nhnacademy.minidooray.gateway.adaptor.member;

import com.nhnacademy.minidooray.gateway.domain.member.request.MemberAddProjectRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.member.request.MemberListRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.member.response.MemberListResponseDTO;
import java.util.List;

public interface MemberAdaptor {
  List<MemberListResponseDTO> getMemberListByProjectId(MemberListRequestDTO memberListRequestDTO);

  boolean insertMemberInProject(MemberAddProjectRequestDTO memberAddProjectRequestDTO);
}
