package com.nhnacademy.minidooray.gateway.service.account.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.nhnacademy.minidooray.gateway.adaptor.account.AccountAdaptorImpl;
import com.nhnacademy.minidooray.gateway.domain.account.request.AccountInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.account.request.AccountLoginRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.account.request.AccountRegisterRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.account.response.AccountInfoResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.account.response.AccountStatusInfoResponseDTO;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AccountClientServiceImplTest {
    @Mock
    private AccountAdaptorImpl accountAdaptor;

    @InjectMocks
    private AccountClientServiceImpl accountClientService;

    @Test
    void testDoLogin() {
        AccountLoginRequestDTO requestDTO = new AccountLoginRequestDTO("tester", "활성");

        Optional<AccountStatusInfoResponseDTO> expected = Optional.of(new AccountStatusInfoResponseDTO());

        when(accountAdaptor.isMatchAccount(requestDTO))
                .thenReturn(expected);

        assertEquals(expected, accountClientService.doLogin(requestDTO));
    }

    @Test
    void testCreateAccount() {
        AccountRegisterRequestDTO requestDTO = new AccountRegisterRequestDTO("test", "1234", "test@naver.com", "tester");

        when(accountAdaptor.insertAccount(requestDTO))
                .thenReturn(true);

        assertTrue(accountClientService.createAccount(requestDTO));
    }

    @Test
    void testReadAccount() {
        AccountInfoRequestDTO requestDTO = new AccountInfoRequestDTO("tester");

        Optional<AccountInfoResponseDTO> expected = Optional.of(new AccountInfoResponseDTO());

        when(accountAdaptor.selectAccount(requestDTO))
                .thenReturn(expected);

        assertEquals(expected, accountClientService.readAccount(requestDTO));
    }

    @Test
    void testDeleteAccount() {
        AccountInfoRequestDTO requestDTO = new AccountInfoRequestDTO("tester");

        when(accountAdaptor.deleteAccount(requestDTO))
                .thenReturn(true);

        assertTrue(accountClientService.deleteAccount(requestDTO));
    }
}