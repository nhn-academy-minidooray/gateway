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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
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

    HttpEntity<String> requestEntity = new HttpEntity<>(headers);
    log.debug("selectAccount: header -> {}, body -> {}", requestEntity.getHeaders(), requestEntity.getBody());
    ResponseEntity<AccountInfoResponseDTO> responseEntity;
    try {
      responseEntity = restTemplate.exchange(
          accountAdaptorProperties.getAddress() + "/account/info/" + accountInfoRequestDTO.getId(),
          HttpMethod.GET,
          requestEntity,
          new ParameterizedTypeReference<>() {
          }
      );
    } catch (HttpClientErrorException e) {
      // HttpClientErrorException은 4xx 상태 코드일 때 발생
      log.debug("Client error: statusCode -> {}, responseBody -> {}", e.getRawStatusCode(), e.getResponseBodyAsString());
      // 원하는 로직을 수행하거나 예외를 다시 던지거나, 기타 처리 가능
      // 예를 들어, 특정 상태 코드에 따라 다르게 처리하고자 할 경우
      if (e.getRawStatusCode() == HttpStatus.NOT_FOUND.value()) {
        // NOT_FOUND 상태 코드에 대한 특별한 처리
      }
      // 원하는 예외를 던지거나, 기타 처리 가능
      throw e;
    } catch (HttpServerErrorException e) {
      // HttpServerErrorException은 5xx 상태 코드일 때 발생
      log.debug("Server error: statusCode -> {}, responseBody -> {}", e.getRawStatusCode(), e.getResponseBodyAsString());
      // 원하는 로직을 수행하거나 예외를 다시 던지거나, 기타 처리 가능
      throw e;
    }    log.debug("selectAccount(): accountId -> {}", accountInfoRequestDTO.getId());
    log.debug("selectAccount(): statusCode -> {}", responseEntity.getStatusCode());

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
