package com.nhnacademy.minidooray.gateway.controller.common;

import com.nhnacademy.minidooray.gateway.domain.account.request.AccountRegisterRequestDTO;
import com.nhnacademy.minidooray.gateway.service.account.AccountClientService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class AccountRegisterControllerTest {

  @InjectMocks
  private AccountRegisterController accountRegisterController;

  @Mock
  private AccountClientService accountClientService;

  @Mock
  private Model model;

  @Mock
  private RedirectAttributes redirectAttributes;

  @Test
  void testGetRegisterPageWithSession() {
    // Given
    HttpServletRequest request = new MockHttpServletRequest();
    HttpSession session = request.getSession(true);
    session.setAttribute("ACCOUNT_ID", "testAccountId");

    // When
    String result = accountRegisterController.getRegisterPage(request);

    // Then
    assertEquals("redirect:/project/home", result);
  }

  @Test
  void testGetRegisterPageWithoutSession() {
    // Given
    HttpServletRequest request = new MockHttpServletRequest();

    // When
    String result = accountRegisterController.getRegisterPage(request);

    // Then
    assertEquals("common/register", result);
  }

  @Test
  void testDoRegisterSuccess() {
    // Given
    AccountRegisterRequestDTO registerRequestDTO = new AccountRegisterRequestDTO();
    registerRequestDTO.setId("testId");
    registerRequestDTO.setPassword("testPassword");
    registerRequestDTO.setName("testName");
    registerRequestDTO.setEmail("test@example.com");

    // When
    when(accountClientService.createAccount(registerRequestDTO)).thenReturn(true);
    String result = accountRegisterController.doRegister(registerRequestDTO, mock(BindingResult.class), redirectAttributes);

    // Then
    assertEquals("redirect:/login", result);
    verify(accountClientService, times(1)).createAccount(any());
  }

  @Test
  void testDoRegisterFailure() {
    // Given
    AccountRegisterRequestDTO registerRequestDTO = new AccountRegisterRequestDTO();
    registerRequestDTO.setId("testId");
    registerRequestDTO.setPassword("testPassword");
    registerRequestDTO.setName("testName");
    registerRequestDTO.setEmail("test@example.com");

    when(accountClientService.createAccount(any())).thenReturn(false);

    // When
    String result = accountRegisterController.doRegister(registerRequestDTO, mock(BindingResult.class), redirectAttributes);

    // Then
    assertEquals("redirect:/register", result);
    verify(redirectAttributes, times(1)).addFlashAttribute(eq("error"), anyString());
  }
}
