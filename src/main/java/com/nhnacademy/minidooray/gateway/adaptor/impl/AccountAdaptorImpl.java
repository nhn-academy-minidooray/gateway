package com.nhnacademy.minidooray.gateway.adaptor.impl;

import com.nhnacademy.minidooray.gateway.adaptor.AccountAdaptor;
import com.nhnacademy.minidooray.gateway.config.AccountAdaptorProperties;
import com.nhnacademy.minidooray.gateway.domain.account.Account;
import com.nhnacademy.minidooray.gateway.domain.account.request.AccountInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.account.request.AccountLoginRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.account.request.AccountRegisterRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.account.response.AccountInfoResponseDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountAdaptorImpl implements AccountAdaptor {
  private final RestTemplate restTemplate;
  private final AccountAdaptorProperties accountAdaptorProperties;

  @Override
  public boolean insertAccount(AccountRegisterRequestDTO accountRegisterRequestDTO) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<AccountRegisterRequestDTO> requestEntity = new HttpEntity<>(
        accountRegisterRequestDTO, headers);
    ResponseEntity<String> responseEntity = restTemplate.exchange(
        accountAdaptorProperties.getAddress() + "/account/register",
        HttpMethod.POST,
        requestEntity,
        new ParameterizedTypeReference<>() {
        }
    );
    log.debug("insertAccount(): statusCode -> {}", responseEntity.getStatusCode());
    return !responseEntity.getStatusCode().isError();
    // todo exception handling
  }

  @Override
  public boolean isMatchAccount(AccountLoginRequestDTO accountLoginRequestDTO) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<AccountLoginRequestDTO> requestEntity = new HttpEntity<>(accountLoginRequestDTO, headers);
    ResponseEntity<String> responseEntity = restTemplate.exchange(
        accountAdaptorProperties.getAddress() + "/account/login",
        HttpMethod.POST,
        requestEntity,
        new ParameterizedTypeReference<>() {
        }
    );

    log.debug("isExistAccount(): statusCode -> {}", responseEntity.getStatusCode());
    // todo exception handling
    return !responseEntity.getStatusCode().isError();
  }

  @Override
  public AccountInfoResponseDTO selectAccount(AccountInfoRequestDTO accountInfoRequestDTO) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<AccountInfoRequestDTO> requestEntity = new HttpEntity<>(accountInfoRequestDTO, headers);
    log.debug("selectAccount: header -> {}, body -> {}", requestEntity.getHeaders(), requestEntity.getBody());
    ResponseEntity<AccountInfoResponseDTO> responseEntity = restTemplate.exchange(
        accountAdaptorProperties.getAddress() + "/account/info",
        HttpMethod.GET,
        requestEntity,
        new ParameterizedTypeReference<>() {
        }
    );

    log.debug("selectAccount(): accountId -> {}", accountInfoRequestDTO.getId());
    log.debug("selectAccount(): statusCode -> {}", responseEntity.getStatusCode());
    // todo exception handling
    return responseEntity.getBody();
  }

  @Override
  public boolean deleteAccount(AccountInfoRequestDTO accountInfoRequestDTO) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<AccountInfoRequestDTO> requestEntity = new HttpEntity<>(accountInfoRequestDTO, headers);
    ResponseEntity<String> responseEntity = restTemplate.exchange(
        accountAdaptorProperties.getAddress() + "/account/delete",
        HttpMethod.DELETE,
        requestEntity,
        new ParameterizedTypeReference<>() {
        }
    );
    return !responseEntity.getStatusCode().isError();
    // todo exception handling
  }
}
