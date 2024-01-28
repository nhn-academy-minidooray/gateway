package com.nhnacademy.minidooray.gateway.adaptor.member;

import com.nhnacademy.minidooray.gateway.domain.member.request.MemberProjectRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.member.request.MemberListRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.member.response.MemberListResponseDTO;
import java.util.List;

public interface MemberAdaptor {
  List<MemberListResponseDTO> getMemberListByProjectId(MemberListRequestDTO memberListRequestDTO);

  boolean insertMemberInProject(MemberProjectRequestDTO memberProjectRequestDTO);
  boolean deleteMemberInProject(MemberProjectRequestDTO memberProjectRequestDTO);
}
