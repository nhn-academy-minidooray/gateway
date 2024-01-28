package com.nhnacademy.minidooray.gateway.service.member;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.minidooray.gateway.adaptor.member.MemberAdaptor;
import com.nhnacademy.minidooray.gateway.domain.account.request.AccountInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.account.response.AccountInfoResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.member.request.MemberListRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.member.request.MemberProjectRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.member.response.MemberListResponseDTO;
import com.nhnacademy.minidooray.gateway.service.account.AccountClientService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MemberServiceImplTest {

    @Mock
    private MemberAdaptor memberAdaptor;

    @Mock
    private AccountClientService accountClientService;

    @InjectMocks
    private MemberServiceImpl memberService;

    @Test
    void testReadMemberList() {
        List<MemberListResponseDTO> expected = List.of(new MemberListResponseDTO());
        MemberListRequestDTO requestDTO = new MemberListRequestDTO();

        when(memberAdaptor.getMemberListByProjectId(requestDTO))
                .thenReturn(expected);

        assertEquals(expected, memberService.readMemberList(requestDTO));
    }

    @Test
    @DisplayName("프로젝트 멤버 등록 성공")
    void testCreateMemberInProjectSuccess() {
        MemberProjectRequestDTO requestDTO = new MemberProjectRequestDTO(1L, "tester");
        AccountInfoRequestDTO accountInfoRequestDTO = new AccountInfoRequestDTO(requestDTO.getAccountId());
        MemberListRequestDTO memberListRequestDTO = new MemberListRequestDTO(requestDTO.getProjectId());

        when(accountClientService.readAccount(accountInfoRequestDTO))
                .thenReturn(Optional.of(new AccountInfoResponseDTO()));

        when(memberAdaptor.getMemberListByProjectId(memberListRequestDTO))
                .thenReturn(List.of());

        when(memberAdaptor.insertMemberInProject(requestDTO))
                .thenReturn(true);

        assertTrue(memberService.createMemberInProject(requestDTO));
    }

    @Test
    @DisplayName("계정이 존재 하지 않을 경우, 멤버 등록 실패")
    void testCreateMemberInProjectFailed() {
        MemberProjectRequestDTO requestDTO = new MemberProjectRequestDTO(1L, "tester");
        AccountInfoRequestDTO accountInfoRequestDTO = new AccountInfoRequestDTO(requestDTO.getAccountId());

        when(accountClientService.readAccount(accountInfoRequestDTO))
                .thenReturn(Optional.empty());

        boolean result = memberService.createMemberInProject(requestDTO);

        assertFalse(result);

        verify(accountClientService).readAccount(accountInfoRequestDTO);
        verify(memberAdaptor, never()).getMemberListByProjectId(any(MemberListRequestDTO.class));
        verify(memberAdaptor, never()).insertMemberInProject(any(MemberProjectRequestDTO.class));
    }

    @Test
    @DisplayName("이미 등록된 멤버일 경우, 멤버 등록 실패")
    void testCreateMemberInProjectWhenAlreadyRegisterMemberInProject() {
        MemberProjectRequestDTO requestDTO = new MemberProjectRequestDTO(1L, "tester");
        AccountInfoRequestDTO accountInfoRequestDTO = new AccountInfoRequestDTO(requestDTO.getAccountId());
        MemberListRequestDTO memberListRequestDTO = new MemberListRequestDTO(requestDTO.getProjectId());
        MemberListResponseDTO memberListResponseDTO = new MemberListResponseDTO(requestDTO.getAccountId());

        when(accountClientService.readAccount(accountInfoRequestDTO))
                .thenReturn(Optional.of(new AccountInfoResponseDTO()));

        when(memberAdaptor.getMemberListByProjectId(memberListRequestDTO))
                .thenReturn(List.of(memberListResponseDTO));

        assertFalse(memberService.createMemberInProject(requestDTO));

        verify(accountClientService).readAccount(accountInfoRequestDTO);
        verify(memberAdaptor).getMemberListByProjectId(memberListRequestDTO);
        verify(memberAdaptor, never()).insertMemberInProject(any(MemberProjectRequestDTO.class));
    }

}