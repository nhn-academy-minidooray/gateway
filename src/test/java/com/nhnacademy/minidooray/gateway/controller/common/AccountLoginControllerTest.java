package com.nhnacademy.minidooray.gateway.controller.common;
import com.nhnacademy.minidooray.gateway.domain.account.request.AccountLoginRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.account.response.AccountStatusInfoResponseDTO;
import com.nhnacademy.minidooray.gateway.service.account.AccountClientService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class AccountLoginControllerTest {

  @InjectMocks
  private AccountLoginController accountLoginController;

  @Mock
  private AccountClientService accountClientService;

  @Mock
  private Model model;

  @Mock
  private RedirectAttributes redirectAttributes;

  @Test
  void testGetLoginPageWithSession() {
    // Given
    HttpServletRequest request = new MockHttpServletRequest();
    HttpSession session = request.getSession(true);
    session.setAttribute("ACCOUNT_ID", "testAccountId");

    // When
    String result = accountLoginController.getLoginPage(request);

    // Then
    assertEquals("redirect:/project/home", result);
  }

  @Test
  void testGetLoginPageWithoutSession() {
    // Given
    HttpServletRequest request = new MockHttpServletRequest();

    // When
    String result = accountLoginController.getLoginPage(request);

    // Then
    assertEquals("common/login", result);
  }

  @Test
  void testDoLoginSuccess() {
    // Given
    AccountLoginRequestDTO loginRequestDTO = new AccountLoginRequestDTO();
    loginRequestDTO.setId("testId");
    loginRequestDTO.setPassword("testPassword");

    HttpServletRequest request = new MockHttpServletRequest();
    MockHttpServletResponse response = new MockHttpServletResponse();

    Optional<AccountStatusInfoResponseDTO> accountInfoWrapped = Optional.of(new AccountStatusInfoResponseDTO("test", "가입"));
    when(accountClientService.doLogin(any())).thenReturn(accountInfoWrapped);

    // When
    String result = accountLoginController.doLogin(loginRequestDTO, mock(BindingResult.class), request, redirectAttributes);

    // Then
    assertEquals("redirect:/project/home", result);
  }

  @Test
  void testDoLoginFailed() {
    // Given
    AccountLoginRequestDTO loginRequestDTO = new AccountLoginRequestDTO();
    loginRequestDTO.setId("testId");
    loginRequestDTO.setPassword("testPassword");

    HttpServletRequest request = new MockHttpServletRequest();
    when(accountClientService.doLogin(any())).thenReturn(Optional.empty());

    // When
    String result = accountLoginController.doLogin(loginRequestDTO, mock(BindingResult.class), request, redirectAttributes);

    // Then
    assertEquals("redirect:/login", result);
    verify(redirectAttributes, times(1)).addFlashAttribute(eq("error"), anyString());
  }

  @Test
  void testDoLoginDormant() {
    AccountLoginRequestDTO loginRequestDTO = new AccountLoginRequestDTO();
    loginRequestDTO.setId("testId");
    loginRequestDTO.setPassword("testPassword");

    HttpServletRequest request = new MockHttpServletRequest();
    MockHttpServletResponse response = new MockHttpServletResponse();

    Optional<AccountStatusInfoResponseDTO> accountInfoWrapped = Optional.of(new AccountStatusInfoResponseDTO("test", "휴면"));
    when(accountClientService.doLogin(any())).thenReturn(accountInfoWrapped);

    // When
    String result = accountLoginController.doLogin(loginRequestDTO, mock(BindingResult.class), request, redirectAttributes);

    // Then
    assertEquals("redirect:/login", result);
  }

  @Test
  void testDoLoginWithdraw() {
    AccountLoginRequestDTO loginRequestDTO = new AccountLoginRequestDTO();
    loginRequestDTO.setId("testId");
    loginRequestDTO.setPassword("testPassword");

    HttpServletRequest request = new MockHttpServletRequest();
    MockHttpServletResponse response = new MockHttpServletResponse();

    Optional<AccountStatusInfoResponseDTO> accountInfoWrapped = Optional.of(new AccountStatusInfoResponseDTO("test", "탈퇴"));
    when(accountClientService.doLogin(any())).thenReturn(accountInfoWrapped);

    // When
    String result = accountLoginController.doLogin(loginRequestDTO, mock(BindingResult.class), request, redirectAttributes);

    // Then
    assertEquals("redirect:/login", result);
  }
}
