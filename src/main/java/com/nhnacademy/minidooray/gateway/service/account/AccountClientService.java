package com.nhnacademy.minidooray.gateway.service.account;

import com.nhnacademy.minidooray.gateway.adaptor.AccountAdaptor;
import com.nhnacademy.minidooray.gateway.domain.account.request.AccountInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.account.request.AccountLoginRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.account.request.AccountRegisterRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.account.response.AccountInfoResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.account.response.AccountStatusInfoResponseDTO;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountClientService {
  private final AccountAdaptor accountAdaptor;

  public Optional<AccountStatusInfoResponseDTO> doLogin(AccountLoginRequestDTO accountLoginRequestDTO) {
    return accountAdaptor.isMatchAccount(accountLoginRequestDTO);
  }

  public boolean createAccount(AccountRegisterRequestDTO accountRegisterRequestDTO) {
    return accountAdaptor.insertAccount(accountRegisterRequestDTO);
  }

  public Optional<AccountInfoResponseDTO> readAccount(AccountInfoRequestDTO accountInfoRequestDTO) {
    return accountAdaptor.selectAccount(accountInfoRequestDTO);
  }

  public boolean deleteAccount(AccountInfoRequestDTO accountInfoRequestDTO) {
    return accountAdaptor.deleteAccount(accountInfoRequestDTO);
  }
}
