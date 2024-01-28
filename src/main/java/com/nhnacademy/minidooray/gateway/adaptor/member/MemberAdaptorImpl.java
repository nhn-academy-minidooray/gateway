package com.nhnacademy.minidooray.gateway.adaptor.member;

import com.nhnacademy.minidooray.gateway.config.ProjectAdaptorProperties;
import com.nhnacademy.minidooray.gateway.domain.member.request.MemberProjectRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.member.request.MemberListRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.member.response.MemberListResponseDTO;
import java.util.Collections;
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
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberAdaptorImpl implements MemberAdaptor{
  private final ProjectAdaptorProperties projectAdaptorProperties;
  private final RestTemplate restTemplate;

  @Override
  public List<MemberListResponseDTO> getMemberListByProjectId(
      MemberListRequestDTO memberListRequestDTO) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<String> requestEntity = new HttpEntity<>(headers);
    ResponseEntity<List<MemberListResponseDTO>> responseEntity;
    String urlTemplate = UriComponentsBuilder.fromHttpUrl(projectAdaptorProperties.getAddress() + "/member/list")
        .queryParam("projectId", memberListRequestDTO.getProjectId())
        .encode()
        .toUriString();

    try {
      responseEntity = restTemplate.exchange(
          urlTemplate,
          HttpMethod.GET,
          requestEntity,
          new ParameterizedTypeReference<>() {
          }
      );
      if(responseEntity.getStatusCode().value() == HttpStatus.OK.value()) {
        log.debug("body -> {}", responseEntity.getBody());
        return responseEntity.getBody();
      }
    } catch (HttpClientErrorException e) {
      log.debug("getMemberListByProjectId(): [Client Error] statusCode -> {}, responseBody -> {}", e.getRawStatusCode(), e.getResponseBodyAsString());
      if (e.getRawStatusCode() == HttpStatus.NOT_FOUND.value()) {
        log.debug("getMemberListByProjectId(): status -> not_found (failed)");
      }
    } catch (HttpServerErrorException e) {
      log.debug("getMemberListByProjectId(): [Server Error] statusCode -> {}, responseBody -> {}", e.getRawStatusCode(), e.getResponseBodyAsString());
      throw e;
    }
    return Collections.emptyList();
  }

  @Override
  public boolean insertMemberInProject(MemberProjectRequestDTO memberProjectRequestDTO) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<MemberProjectRequestDTO> requestEntity = new HttpEntity<>(memberProjectRequestDTO, headers);
    String urlTemplate = projectAdaptorProperties.getAddress() + "/member/register";
    ResponseEntity<String> responseEntity;
    try {
      responseEntity = restTemplate.exchange(
          urlTemplate,
          HttpMethod.POST,
          requestEntity,
          new ParameterizedTypeReference<>() {
          }
      );

      if(responseEntity.getStatusCode().value() == HttpStatus.CREATED.value())
        return true;
    } catch (HttpClientErrorException e) {
      log.debug("getMemberListByProjectId(): [Client Error] statusCode -> {}, responseBody -> {}", e.getRawStatusCode(), e.getResponseBodyAsString());
      if (e.getRawStatusCode() == HttpStatus.NOT_FOUND.value()) {
        log.debug("getMemberListByProjectId(): status -> not_found (failed)");
      }
    } catch (HttpServerErrorException e) {
      log.debug("getMemberListByProjectId(): [Server Error] statusCode -> {}, responseBody -> {}", e.getRawStatusCode(), e.getResponseBodyAsString());
      throw e;
    }
    return false;
  }

  @Override
  public boolean deleteMemberInProject(MemberProjectRequestDTO memberProjectRequestDTO) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<MemberProjectRequestDTO> requestEntity = new HttpEntity<>(memberProjectRequestDTO, headers);
    String urlTemplate = projectAdaptorProperties.getAddress() + "/member/delete";
    ResponseEntity<String> responseEntity;
    try {
      responseEntity = restTemplate.exchange(
          urlTemplate,
          HttpMethod.DELETE,
          requestEntity,
          new ParameterizedTypeReference<>() {
          }
      );

      if(responseEntity.getStatusCode().value() == HttpStatus.OK.value())
        return true;
    } catch (HttpClientErrorException e) {
      log.debug("getMemberListByProjectId(): [Client Error] statusCode -> {}, responseBody -> {}", e.getRawStatusCode(), e.getResponseBodyAsString());
      if (e.getRawStatusCode() == HttpStatus.NOT_FOUND.value()) {
        log.debug("getMemberListByProjectId(): status -> not_found (failed)");
      }
    } catch (HttpServerErrorException e) {
      log.debug("getMemberListByProjectId(): [Server Error] statusCode -> {}, responseBody -> {}", e.getRawStatusCode(), e.getResponseBodyAsString());
      throw e;
    }
    return false;
  }
}