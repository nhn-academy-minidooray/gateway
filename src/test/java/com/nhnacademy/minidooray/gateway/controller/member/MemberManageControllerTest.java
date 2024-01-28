package com.nhnacademy.minidooray.gateway.controller.member;

import com.nhnacademy.minidooray.gateway.domain.member.request.MemberProjectRequestDTO;
import com.nhnacademy.minidooray.gateway.service.member.MemberService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class MemberManageControllerTest {

  @InjectMocks
  private MemberManageController memberManageController;

  @Mock
  private MemberService memberService;

  @Mock
  private Model model;

  @Mock
  private RedirectAttributes redirectAttributes;

  @Test
  void testPostMemberInProjectSuccess() {
    // Given
    MemberProjectRequestDTO memberProjectRequestDTO = new MemberProjectRequestDTO();
    memberProjectRequestDTO.setAccountId("testAccountId");
    memberProjectRequestDTO.setProjectId(1L);

    MockHttpSession session = new MockHttpSession();
    session.setAttribute("ACCOUNT_ID", "testLoggedInAccountId");

    // When
    when(memberService.createMemberInProject(memberProjectRequestDTO)).thenReturn(true);
    String result = memberManageController.postMemberInProject(memberProjectRequestDTO, (String) session.getAttribute("ACCOUNT_ID"), redirectAttributes);

    // Then
    assertEquals("redirect:/project/" + memberProjectRequestDTO.getProjectId(), result);
    verify(redirectAttributes, times(1)).addFlashAttribute(eq("info"), anyString());
  }

  @Test
  void testPostMemberInProjectSelfError() {
    // Given
    MemberProjectRequestDTO memberProjectRequestDTO = new MemberProjectRequestDTO();
    memberProjectRequestDTO.setAccountId("testLoggedInAccountId");
    memberProjectRequestDTO.setProjectId(1L);

    MockHttpSession session = new MockHttpSession();
    session.setAttribute("ACCOUNT_ID", "testLoggedInAccountId");

    // When
    String result = memberManageController.postMemberInProject(memberProjectRequestDTO, (String) session.getAttribute("ACCOUNT_ID"), redirectAttributes);

    // Then
    assertEquals("redirect:/project/" + memberProjectRequestDTO.getProjectId(), result);
    verify(redirectAttributes, times(1)).addFlashAttribute(eq("error"), anyString());
  }

  @Test
  void testPostMemberInProjectFailure() {
    // Given
    MemberProjectRequestDTO memberProjectRequestDTO = new MemberProjectRequestDTO();
    memberProjectRequestDTO.setAccountId("testAccountId");
    memberProjectRequestDTO.setProjectId(1L);

    MockHttpSession session = new MockHttpSession();
    session.setAttribute("ACCOUNT_ID", "testLoggedInAccountId");

    when(memberService.createMemberInProject(any())).thenReturn(false);

    // When
    String result = memberManageController.postMemberInProject(memberProjectRequestDTO, (String) session.getAttribute("ACCOUNT_ID"), redirectAttributes);

    // Then
    assertEquals("redirect:/project/" + memberProjectRequestDTO.getProjectId(), result);
    verify(redirectAttributes, times(1)).addFlashAttribute(eq("error"), anyString());
  }

  @Test
  void testDeleteMemberInProjectSuccess() {
    // Given
    MemberProjectRequestDTO memberProjectRequestDTO = new MemberProjectRequestDTO();
    memberProjectRequestDTO.setAccountId("testAccountId");
    memberProjectRequestDTO.setProjectId(1L);

    // When
    when(memberService.deleteMemberInProject(memberProjectRequestDTO)).thenReturn(true);
    String result = memberManageController.deleteMemberInProject(memberProjectRequestDTO, redirectAttributes);

    // Then
    assertEquals("redirect:/project/" + memberProjectRequestDTO.getProjectId(), result);
    verify(redirectAttributes, times(1)).addFlashAttribute(eq("info"), anyString());
  }

  @Test
  void testDeleteMemberInProjectFailure() {
    // Given
    MemberProjectRequestDTO memberProjectRequestDTO = new MemberProjectRequestDTO();
    memberProjectRequestDTO.setAccountId("testAccountId");
    memberProjectRequestDTO.setProjectId(1L);

    when(memberService.deleteMemberInProject(any())).thenReturn(false);

    // When
    String result = memberManageController.deleteMemberInProject(memberProjectRequestDTO, redirectAttributes);

    // Then
    assertEquals("redirect:/project/" + memberProjectRequestDTO.getProjectId(), result);
    verify(redirectAttributes, times(1)).addFlashAttribute(eq("error"), anyString());
  }
}
