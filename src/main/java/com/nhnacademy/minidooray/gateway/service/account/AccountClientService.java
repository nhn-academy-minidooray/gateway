package com.nhnacademy.minidooray.gateway.service.account;

import com.nhnacademy.minidooray.gateway.domain.account.request.AccountInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.account.request.AccountLoginRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.account.request.AccountRegisterRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.account.response.AccountInfoResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.account.response.AccountStatusInfoResponseDTO;
import java.util.Optional;

public interface AccountClientService {
  Optional<AccountStatusInfoResponseDTO> doLogin(AccountLoginRequestDTO accountLoginRequestDTO);
  boolean createAccount(AccountRegisterRequestDTO accountRegisterRequestDTO);
  Optional<AccountInfoResponseDTO> readAccount(AccountInfoRequestDTO accountInfoRequestDTO);
  boolean deleteAccount(AccountInfoRequestDTO accountInfoRequestDTO);
}
