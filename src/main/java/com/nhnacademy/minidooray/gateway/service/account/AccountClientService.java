package com.nhnacademy.minidooray.gateway.service.account;

import com.nhnacademy.minidooray.gateway.adaptor.AccountAdaptor;
import com.nhnacademy.minidooray.gateway.domain.account.request.AccountInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.account.request.AccountLoginRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.account.request.AccountRegisterRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.account.response.AccountInfoResponseDTO;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountClientService {
  private final AccountAdaptor accountAdaptor;

  public boolean doLogin(AccountLoginRequestDTO accountLoginRequestDTO) {
    return accountAdaptor.isMatchAccount(accountLoginRequestDTO);
  }

  public boolean createAccount(AccountRegisterRequestDTO accountRegisterRequestDTO) {
    return accountAdaptor.insertAccount(accountRegisterRequestDTO);
  }

  public AccountInfoResponseDTO readAccount(AccountInfoRequestDTO accountInfoRequestDTO) {
    AccountInfoResponseDTO account = accountAdaptor.selectAccount(accountInfoRequestDTO);
    if(Objects.isNull(account))
      throw new RuntimeException();
    // todo exception handling
    return account;
  }

  public boolean deleteAccount(AccountInfoRequestDTO accountInfoRequestDTO) {
    return accountAdaptor.deleteAccount(accountInfoRequestDTO);
  }
}
