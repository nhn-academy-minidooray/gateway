package com.nhnacademy.minidooray.gateway.adaptor.impl;

import com.nhnacademy.minidooray.gateway.adaptor.AccountAdaptor;
import com.nhnacademy.minidooray.gateway.domain.account.Account;
import com.nhnacademy.minidooray.gateway.domain.account.AccountInfoDTO;
import com.nhnacademy.minidooray.gateway.domain.account.AccountLoginDTO;
import com.nhnacademy.minidooray.gateway.domain.account.AccountRegisterDTO;

public class AccountAdaptorImpl implements AccountAdaptor {
  @Override
  public void createAccount(AccountRegisterDTO accountRegisterDTO) {

  }

  @Override
  public boolean doLogin(AccountLoginDTO accountLoginDTO) {
    return false;
  }

  @Override
  public Account getAccount(AccountInfoDTO accountInfoDTO) {
    return null;
  }

  @Override
  public void deleteAccount(AccountInfoDTO accountInfoDTO) {

  }
}
