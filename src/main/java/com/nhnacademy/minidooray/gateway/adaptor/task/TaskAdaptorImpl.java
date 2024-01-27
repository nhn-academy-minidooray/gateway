package com.nhnacademy.minidooray.gateway.adaptor.task;

import com.nhnacademy.minidooray.gateway.config.ProjectAdaptorProperties;
import com.nhnacademy.minidooray.gateway.domain.task.request.TaskCreateRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.task.request.TaskDeleteRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.task.request.TaskInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.task.response.TaskInfoResponseDTO;
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
public class TaskAdaptorImpl implements TaskAdaptor{
  private final RestTemplate restTemplate;
  private final ProjectAdaptorProperties projectAdaptorProperties;

  @Override
  public List<TaskInfoResponseDTO> selectTaskListInProject(TaskInfoRequestDTO taskInfoRequestDTO) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<String> requestEntity = new HttpEntity<>(headers);
    String urlTemplate = UriComponentsBuilder.fromHttpUrl(projectAdaptorProperties.getAddress() + "/task/list")
        .queryParam("projectId", taskInfoRequestDTO.getProjectId())
        .encode()
        .toUriString();
    ResponseEntity<List<TaskInfoResponseDTO>> responseEntity;
    try {
      responseEntity = restTemplate.exchange(
          urlTemplate,
          HttpMethod.GET,
          requestEntity,
          new ParameterizedTypeReference<List<TaskInfoResponseDTO>>() {
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
  public boolean insertTaskInProject(TaskCreateRequestDTO taskCreateRequestDTO) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<TaskCreateRequestDTO> requestEntity = new HttpEntity<>(taskCreateRequestDTO, headers);
    String urlTemplate = projectAdaptorProperties.getAddress() + "/task/register";
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
  public boolean updateTask(Long taskId, TaskCreateRequestDTO taskModifyRequestDTO) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<TaskCreateRequestDTO> requestEntity = new HttpEntity<>(taskModifyRequestDTO, headers);
    String urlTemplate = projectAdaptorProperties.getAddress() + "/task/{taskId}/modify";
    ResponseEntity<String> responseEntity;
    try {
      responseEntity = restTemplate.exchange(
          urlTemplate,
          HttpMethod.PUT,
          requestEntity,
          new ParameterizedTypeReference<>() {
          }, taskId
      );
      if(responseEntity.getStatusCode().value() == HttpStatus.OK.value())
        return true;
    } catch (HttpClientErrorException e) {

    } catch (HttpServerErrorException e) {

    }
    return false;
  }

  @Override
  public boolean deleteTask(TaskDeleteRequestDTO taskDeleteRequestDTO) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<String> requestEntity = new HttpEntity<>(headers);
    String urlTemplate = projectAdaptorProperties.getAddress() + "/task/{taskId}/delete";
    ResponseEntity<String> responseEntity;
    try {
      responseEntity = restTemplate.exchange(
          urlTemplate,
          HttpMethod.PUT,
          requestEntity,
          new ParameterizedTypeReference<>() {
          }, taskDeleteRequestDTO.getTaskId()
      );
      if(responseEntity.getStatusCode().value() == HttpStatus.OK.value())
        return true;
    } catch (HttpClientErrorException e) {

    } catch (HttpServerErrorException e) {

    }
    return false;
  }
}
