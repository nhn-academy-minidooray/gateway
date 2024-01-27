package com.nhnacademy.minidooray.gateway.service.member;

import com.nhnacademy.minidooray.gateway.adaptor.member.MemberAdaptor;
import com.nhnacademy.minidooray.gateway.domain.account.request.AccountInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.member.request.MemberAddProjectRequestDTO;
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
  public boolean createMemberInProject(MemberAddProjectRequestDTO memberAddProjectRequestDTO) {
    if(accountClientService.readAccount(new AccountInfoRequestDTO(
        memberAddProjectRequestDTO.getAccountId())).isPresent()) {
      log.debug("존재하는 계정");
      if(memberAdaptor.getMemberListByProjectId(
          new MemberListRequestDTO(memberAddProjectRequestDTO.getProjectId())).stream().noneMatch(
          memberResponse -> memberResponse.getAccountId()
              .equals(memberAddProjectRequestDTO.getAccountId()))) {
        log.debug("멤버에 현재 없음");
        return memberAdaptor.insertMemberInProject(memberAddProjectRequestDTO);
      }
    }
    return false;
  }
}
