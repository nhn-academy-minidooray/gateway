package com.nhnacademy.minidooray.gateway.adaptor;

import com.nhnacademy.minidooray.gateway.domain.account.Account;
import com.nhnacademy.minidooray.gateway.domain.account.AccountInfoDTO;
import com.nhnacademy.minidooray.gateway.domain.account.AccountLoginDTO;
import com.nhnacademy.minidooray.gateway.domain.account.AccountRegisterDTO;

public interface AccountAdaptor {
  void createAccount(AccountRegisterDTO accountRegisterDTO);
  boolean doLogin(AccountLoginDTO accountLoginDTO);
  Account getAccount(AccountInfoDTO accountInfoDTO);
  void deleteAccount(AccountInfoDTO accountInfoDTO);
}
