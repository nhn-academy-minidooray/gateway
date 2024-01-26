package com.nhnacademy.minidooray.gateway.adaptor.project;

import com.nhnacademy.minidooray.gateway.config.ProjectAdaptorProperties;
import com.nhnacademy.minidooray.gateway.domain.project.request.ProjectInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.project.request.ProjectListInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.project.request.ProjectRegisterRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.project.response.ProjectInfoResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.project.response.ProjectListInfoResponseDTO;
import java.util.Collections;
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
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProjectAdaptorImpl implements ProjectAdaptor {
  private final RestTemplate restTemplate;
  private final ProjectAdaptorProperties projectAdaptorProperties;

  @Override
  public boolean insertProject(ProjectRegisterRequestDTO projectRegisterRequestDTO) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<ProjectRegisterRequestDTO> requestEntity = new HttpEntity<>(projectRegisterRequestDTO, headers);
    ResponseEntity<String> responseEntity;
    try {
      responseEntity = restTemplate.exchange(
          projectAdaptorProperties.getAddress() + "/project/register",
          HttpMethod.POST,
          requestEntity,
          new ParameterizedTypeReference<>() {
          }
      );
      log.debug("insertProject(): [Create Success] statusCode -> {}", responseEntity.getStatusCode().value());
      return responseEntity.getStatusCode().value() == HttpStatus.CREATED.value();
    } catch (
        HttpClientErrorException e) {
      log.debug("insertProject(): [Client Error] statusCode -> {}, responseBody -> {}", e.getRawStatusCode(), e.getResponseBodyAsString());
      if (e.getRawStatusCode() == HttpStatus.CONFLICT.value()) {
        log.debug("insertProject(): status -> conflict (create failed)");
      }
    } catch (
        HttpServerErrorException e) {
      log.debug("insertProject(): [Server Error] statusCode -> {}, responseBody -> {}", e.getRawStatusCode(), e.getResponseBodyAsString());
      throw e;
      // todo 이 부분은 서버 에러라 에러 페이지로 보내는 게 어떨지?
    }
    return false;
  }

  @Override
  public List<ProjectListInfoResponseDTO> selectProjectListByAccountId(
      ProjectListInfoRequestDTO projectListInfoRequestDTO) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<String> requestEntity = new HttpEntity<>(headers);
    String urlTemplate = UriComponentsBuilder.fromHttpUrl(projectAdaptorProperties.getAddress() + "/project/list")
            .queryParam("accountId", projectListInfoRequestDTO.getAccountId())
                .encode()
                    .toUriString();
    ResponseEntity<List<ProjectListInfoResponseDTO>> responseEntity;
    try {
      responseEntity = restTemplate.exchange(
          urlTemplate,
          HttpMethod.GET,
          requestEntity,
          new ParameterizedTypeReference<>() {
          }
      );
      log.debug("selectProjectListByAccountId(): [Create Success] statusCode -> {}", responseEntity.getStatusCode().value());
      if(responseEntity.getStatusCode().value() == HttpStatus.OK.value()) {
        log.debug("{}", responseEntity.getBody());
        return responseEntity.getBody();
      }
    } catch (HttpClientErrorException e) {
      log.debug("selectProjectListByAccountId(): [Client Error] statusCode -> {}, responseBody -> {}", e.getRawStatusCode(), e.getResponseBodyAsString());
      if (e.getRawStatusCode() == HttpStatus.CONFLICT.value()) {
        log.debug("selectProjectListByAccountId(): status -> conflict (create failed)");
      }
    } catch (HttpServerErrorException e) {
      log.debug("selectProjectListByAccountId(): [Server Error] statusCode -> {}, responseBody -> {}", e.getRawStatusCode(), e.getResponseBodyAsString());
      throw e;
      // todo 이 부분은 서버 에러라 에러 페이지로 보내는 게 어떨지?
    }
    return Collections.emptyList();
  }

  @Override
  public Optional<ProjectInfoResponseDTO> selectProjectDetailByProjectId(
      ProjectInfoRequestDTO projectInfoRequestDTO) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<String> requestEntity = new HttpEntity<>(headers);
    String urlTemplate = UriComponentsBuilder.fromHttpUrl(projectAdaptorProperties.getAddress() + "/project")
        .queryParam("projectId", projectInfoRequestDTO.getProjectId())
        .encode()
        .toUriString();
    ResponseEntity<ProjectInfoResponseDTO> responseEntity;
    try {
      responseEntity = restTemplate.exchange(
          urlTemplate,
          HttpMethod.GET,
          requestEntity,
          new ParameterizedTypeReference<>() {
          }
      );
      log.debug("selectProjectDetailByProjectId(): [Create Success] statusCode -> {}", responseEntity.getStatusCode().value());
      if(responseEntity.getStatusCode().value() == HttpStatus.OK.value())
        return Optional.ofNullable(responseEntity.getBody());
    } catch (HttpClientErrorException e) {
      log.debug("selectProjectDetailByProjectId(): [Client Error] statusCode -> {}, responseBody -> {}", e.getRawStatusCode(), e.getResponseBodyAsString());
      if (e.getRawStatusCode() == HttpStatus.CONFLICT.value()) {
        log.debug("selectProjectDetailByProjectId(): status -> conflict (create failed)");
      }
    } catch (HttpServerErrorException e) {
      log.debug("selectProjectDetailByProjectId(): [Server Error] statusCode -> {}, responseBody -> {}", e.getRawStatusCode(), e.getResponseBodyAsString());
      throw e;
      // todo 이 부분은 서버 에러라 에러 페이지로 보내는 게 어떨지?
    }
    return Optional.empty();
  }
}
