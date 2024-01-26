package com.nhnacademy.minidooray.gateway.domain.account;

import lombok.Getter;

@Getter
public enum Status {
  JOIN("가입"),
  DORMANT("휴면"),
  WITHDRAWAL("탈퇴");

  private final String value;

  Status(String value){
    this.value = value;
  }
}
