package com.nhnacademy.minidooray.gateway.adaptor.milestone;

import com.nhnacademy.minidooray.gateway.config.ProjectAdaptorProperties;
import com.nhnacademy.minidooray.gateway.domain.milestone.request.MilestoneRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.milestone.response.MilestoneDetailResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.milestone.response.MilestoneInfoResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.tag.response.TagInfoResponseDTO;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
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
public class MilestoneAdaptorImpl implements MilestoneAdaptor{
  private final RestTemplate restTemplate;
  private final ProjectAdaptorProperties projectAdaptorProperties;
  private static HttpHeaders headers;

  private static HttpHeaders getHeader() {
    if(Objects.isNull(headers)) {
      headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      headers.setAccept(List.of(MediaType.APPLICATION_JSON));
    }
    return headers;
  }

  @Override
  public Optional<MilestoneInfoResponseDTO> selectMilestoneInTask(Long taskId) {
    HttpEntity<String> requestEntity = new HttpEntity<>(getHeader());
    String urlTemplate = UriComponentsBuilder.fromHttpUrl(projectAdaptorProperties.getAddress() + "/milestone")
        .queryParam("taskId", taskId)
        .encode()
        .toUriString();

    ResponseEntity<MilestoneInfoResponseDTO> responseEntity;
    try {
      responseEntity = restTemplate.exchange(
          urlTemplate,
          HttpMethod.GET,
          requestEntity,
          new ParameterizedTypeReference<>() {
          }
      );
      if(responseEntity.getStatusCode().value() == HttpStatus.OK.value())
        return Optional.ofNullable(responseEntity.getBody());
    } catch (HttpClientErrorException e) {

    } catch (HttpServerErrorException e) {

    }
    return Optional.empty();
  }

  @Override
  public List<MilestoneInfoResponseDTO> selectMilestoneListInProject(Long projectId) {
    HttpEntity<String> requestEntity = new HttpEntity<>(getHeader());
    String urlTemplate = UriComponentsBuilder.fromHttpUrl(projectAdaptorProperties.getAddress() + "/milestone/list")
        .queryParam("projectId", projectId)
        .encode()
        .toUriString();

    ResponseEntity<List<MilestoneInfoResponseDTO>> responseEntity;
    try {
      responseEntity = restTemplate.exchange(
          urlTemplate,
          HttpMethod.GET,
          requestEntity,
          new ParameterizedTypeReference<List<MilestoneInfoResponseDTO>>() {
          }
      );
      if(responseEntity.getStatusCode().value() == HttpStatus.OK.value())
        return responseEntity.getBody();
    } catch (HttpClientErrorException e) {

    } catch (HttpServerErrorException e) {

    }
    return Collections.emptyList();
  }

  @Override
  public Optional<MilestoneDetailResponseDTO> selectMilestoneDetail(Long milestoneId) {
    HttpEntity<String> requestEntity = new HttpEntity<>(getHeader());
    String urlTemplate = projectAdaptorProperties.getAddress() + "/milestone/{milestoneId}";

    ResponseEntity<MilestoneDetailResponseDTO> responseEntity;
    try {
      responseEntity = restTemplate.exchange(
          urlTemplate,
          HttpMethod.GET,
          requestEntity,
          new ParameterizedTypeReference<>() {
          }, milestoneId
      );
      if(responseEntity.getStatusCode().value() == HttpStatus.OK.value())
        return Optional.ofNullable(responseEntity.getBody());
    } catch (HttpClientErrorException e) {

    } catch (HttpServerErrorException e) {

    }
    return Optional.empty();
  }

  @Override
  public boolean insertMilestoneInProject(MilestoneRequestDTO milestoneRequestDTO) {
    HttpEntity<MilestoneRequestDTO> requestEntity = new HttpEntity<>(milestoneRequestDTO, getHeader());
    String urlTemplate = projectAdaptorProperties.getAddress() + "/milestone/register";
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

    } catch (HttpServerErrorException e) {

    }
    return false;
  }

  @Override
  public boolean updateMilestone(Long milestoneId, MilestoneRequestDTO milestoneRequestDTO) {
    HttpEntity<String> requestEntity = new HttpEntity<>(getHeader());
    String urlTemplate = projectAdaptorProperties.getAddress() + "/milestone/{milestoneId}/modify";
    ResponseEntity<String> responseEntity;
    try {
      responseEntity = restTemplate.exchange(
          urlTemplate,
          HttpMethod.PUT,
          requestEntity,
          new ParameterizedTypeReference<>() {
          }, milestoneId
      );
      if(responseEntity.getStatusCode().value() == HttpStatus.OK.value())
        return true;
    } catch (HttpClientErrorException e) {

    } catch (HttpServerErrorException e) {

    }
    return false;
  }

  @Override
  public boolean deleteMilestone(Long milestoneId) {
    HttpEntity<String> requestEntity = new HttpEntity<>(getHeader());
    String urlTemplate = projectAdaptorProperties.getAddress() + "/milestone/{milestoneId}/delete";

    ResponseEntity<String> responseEntity;
    try {
      responseEntity = restTemplate.exchange(
          urlTemplate,
          HttpMethod.DELETE,
          requestEntity,
          new ParameterizedTypeReference<>() {
          }, milestoneId
      );
      if(responseEntity.getStatusCode().value() == HttpStatus.OK.value())
        return true;
    } catch (HttpClientErrorException e) {

    } catch (HttpServerErrorException e) {

    }
    return false;
  }
}
