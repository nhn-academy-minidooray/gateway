package com.nhnacademy.minidooray.gateway.controller.account;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AccountLogoutControllerTest {

  @InjectMocks
  private AccountLogoutController accountLogoutController;

  @Mock
  private HttpSession session;

  @Mock
  private MockHttpServletResponse response;

  @Test
  void testDoLogout() {
    doNothing().when(response).addCookie(any());
    String result = accountLogoutController.doLogout(session, response);
    assertEquals("redirect:/login", result);
    verify(session, times(1)).invalidate();
    verify(response, times(1)).addCookie(any(Cookie.class));
  }
}
