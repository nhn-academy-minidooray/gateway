package com.nhnacademy.minidooray.gateway.controller.account;

import com.nhnacademy.minidooray.gateway.domain.account.response.AccountInfoResponseDTO;
import com.nhnacademy.minidooray.gateway.exception.AccountInfoNotFoundException;
import com.nhnacademy.minidooray.gateway.service.account.AccountClientService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.ui.Model;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
class AccountDetailControllerTest {

  @Mock
  private AccountClientService accountClientService;

  @InjectMocks
  private AccountDetailController accountDetailController;

  @Mock
  private Model model;

  @Test
  void testGetMyPageSuccess() {
    String accountId = "testAccountId";
    AccountInfoResponseDTO accountInfoResponseDTO = new AccountInfoResponseDTO();
    Optional<AccountInfoResponseDTO> accountWrapper = Optional.of(accountInfoResponseDTO);

    when(accountClientService.readAccount(any())).thenReturn(accountWrapper);

    String result = accountDetailController.getMyPage(accountId, model);

    assertEquals("project/mypage", result);

    verify(model, times(1)).addAttribute(eq("ACCOUNT_INFO"), any());
  }

  @Test
  void testGetMyPageFailure() {
    String accountId = "testAccountId";

    when(accountClientService.readAccount(any())).thenReturn(Optional.empty());

    assertThrows(AccountInfoNotFoundException.class,
        () -> accountDetailController.getMyPage(accountId, model));

    verify(model, never()).addAttribute(eq("ACCOUNT_INFO"), any());
  }
}
