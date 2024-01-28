package com.nhnacademy.minidooray.gateway.adaptor.account;

import com.nhnacademy.minidooray.gateway.adaptor.account.AccountAdaptor;
import com.nhnacademy.minidooray.gateway.config.AccountAdaptorProperties;
import com.nhnacademy.minidooray.gateway.domain.account.request.AccountInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.account.request.AccountLoginRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.account.request.AccountRegisterRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.account.response.AccountInfoResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.account.response.AccountStatusInfoResponseDTO;
import java.util.List;
import java.util.Optional;
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
    ResponseEntity<String> responseEntity;
    try {
      responseEntity = restTemplate.exchange(
          accountAdaptorProperties.getAddress() + "/account/register",
          HttpMethod.POST,
          requestEntity,
          new ParameterizedTypeReference<>() {
          }
      );
      return responseEntity.getStatusCode().value() == HttpStatus.CREATED.value();
    } catch (HttpClientErrorException e) {
      if (e.getRawStatusCode() == HttpStatus.CONFLICT.value()) {
      }
    } catch (HttpServerErrorException e) {
      throw e;
      // todo 이 부분은 서버 에러라 에러 페이지로 보내는 게 어떨지?
    }
    return false;
  }

  @Override
  public Optional<AccountStatusInfoResponseDTO> isMatchAccount(AccountLoginRequestDTO accountLoginRequestDTO) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<AccountLoginRequestDTO> requestEntity;
    try {
      requestEntity = new HttpEntity<>(accountLoginRequestDTO, headers);
      ResponseEntity<AccountStatusInfoResponseDTO> responseEntity = restTemplate.exchange(
          accountAdaptorProperties.getAddress() + "/account/login",
          HttpMethod.POST,
          requestEntity,
          new ParameterizedTypeReference<>() {
          }
      );
      if(responseEntity.getStatusCode().value() == HttpStatus.OK.value())
        return Optional.ofNullable(responseEntity.getBody());
    } catch (HttpClientErrorException e) {
      if (e.getRawStatusCode() == HttpStatus.CONFLICT.value()) {
      }
    } catch (HttpServerErrorException e) {
      throw e;
      // todo 이 부분은 서버 에러라 에러 페이지로 보내는 게 어떨지?
    }
    return Optional.empty();
  }

  @Override
  public Optional<AccountInfoResponseDTO> selectAccount(AccountInfoRequestDTO accountInfoRequestDTO) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<String> requestEntity = new HttpEntity<>(headers);
    ResponseEntity<AccountInfoResponseDTO> responseEntity;
    try {
      responseEntity = restTemplate.exchange(
          accountAdaptorProperties.getAddress() + "/account/info/" + accountInfoRequestDTO.getId(),
          HttpMethod.GET,
          requestEntity,
          new ParameterizedTypeReference<>() {
          }
      );
      log.debug("isMatchAccount(): [Create Success] statusCode -> {}", responseEntity.getStatusCode().value());
      if(responseEntity.getStatusCode().value() == HttpStatus.OK.value())
        return Optional.ofNullable(responseEntity.getBody());
    } catch (HttpClientErrorException e) {
      log.debug("isMatchAccount(): [Client Error] statusCode -> {}, responseBody -> {}", e.getRawStatusCode(), e.getResponseBodyAsString());
      if (e.getRawStatusCode() == HttpStatus.CONFLICT.value()) {
      }
    } catch (HttpServerErrorException e) {
      throw e;
      // todo 이 부분은 서버 에러라 에러 페이지로 보내는 게 어떨지?
    }
    return Optional.empty();
  }

  @Override
  public boolean deleteAccount(AccountInfoRequestDTO accountInfoRequestDTO) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<AccountInfoRequestDTO> requestEntity = new HttpEntity<>(accountInfoRequestDTO, headers);
    ResponseEntity<String> responseEntity;
    try {
      responseEntity= restTemplate.exchange(
          accountAdaptorProperties.getAddress() + "/account/delete",
          HttpMethod.DELETE,
          requestEntity,
          new ParameterizedTypeReference<>() {
          }
      );
      return responseEntity.getStatusCode().value() == HttpStatus.OK.value();
    } catch (HttpClientErrorException e) {
      if (e.getRawStatusCode() == HttpStatus.CONFLICT.value()) {
      }
    } catch (HttpServerErrorException e) {
      throw e;
      // todo 이 부분은 서버 에러라 에러 페이지로 보내는 게 어떨지?
    }
    return false;
  }
}
