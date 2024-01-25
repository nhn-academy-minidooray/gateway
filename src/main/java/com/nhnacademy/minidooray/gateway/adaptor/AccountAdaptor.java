package com.nhnacademy.minidooray.gateway.adaptor;

import com.nhnacademy.minidooray.gateway.domain.account.request.AccountInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.account.request.AccountLoginRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.account.request.AccountRegisterRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.account.response.AccountInfoResponseDTO;

public interface AccountAdaptor {
  boolean insertAccount(AccountRegisterRequestDTO accountRegisterRequestDTO);
  boolean isMatchAccount(AccountLoginRequestDTO accountLoginRequestDTO);
  AccountInfoResponseDTO selectAccount(AccountInfoRequestDTO accountInfoRequestDTO);
  boolean deleteAccount(AccountInfoRequestDTO accountInfoRequestDTO);
}
