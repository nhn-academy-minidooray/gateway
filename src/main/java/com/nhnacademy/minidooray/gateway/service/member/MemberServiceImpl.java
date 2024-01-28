package com.nhnacademy.minidooray.gateway.service.member;

import com.nhnacademy.minidooray.gateway.adaptor.member.MemberAdaptor;
import com.nhnacademy.minidooray.gateway.domain.account.request.AccountInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.member.request.MemberProjectRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.member.request.MemberListRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.member.response.MemberListResponseDTO;
import com.nhnacademy.minidooray.gateway.service.account.AccountClientService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
  private final MemberAdaptor memberAdaptor;
  private final AccountClientService accountClientService;

  @Override
  public List<MemberListResponseDTO> readMemberList(MemberListRequestDTO memberListRequestDTO) {
    return memberAdaptor.getMemberListByProjectId(memberListRequestDTO);
  }

  @Override
  public boolean createMemberInProject(MemberProjectRequestDTO memberProjectRequestDTO) {
    if(accountClientService.readAccount(new AccountInfoRequestDTO(
        memberProjectRequestDTO.getAccountId())).isPresent()) {
      if(memberAdaptor.getMemberListByProjectId(
          new MemberListRequestDTO(memberProjectRequestDTO.getProjectId())).stream().noneMatch(
          memberResponse -> memberResponse.getAccountId()
              .equals(memberProjectRequestDTO.getAccountId()))) {
        return memberAdaptor.insertMemberInProject(memberProjectRequestDTO);
      }
    }
    return false;
  }

  @Override
  public boolean deleteMemberInProject(MemberProjectRequestDTO memberProjectRequestDTO) {
    return memberAdaptor.deleteMemberInProject(memberProjectRequestDTO);
  }
}
