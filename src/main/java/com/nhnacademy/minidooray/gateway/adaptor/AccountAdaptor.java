package com.nhnacademy.minidooray.gateway.adaptor;

import com.nhnacademy.minidooray.gateway.domain.account.request.AccountInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.account.request.AccountLoginRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.account.request.AccountRegisterRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.account.response.AccountInfoResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.account.response.AccountStatusInfoResponseDTO;
import java.util.Optional;

public interface AccountAdaptor {
  boolean insertAccount(AccountRegisterRequestDTO accountRegisterRequestDTO);
  Optional<AccountStatusInfoResponseDTO> isMatchAccount(AccountLoginRequestDTO accountLoginRequestDTO);
  Optional<AccountInfoResponseDTO> selectAccount(AccountInfoRequestDTO accountInfoRequestDTO);
  boolean deleteAccount(AccountInfoRequestDTO accountInfoRequestDTO);
}
