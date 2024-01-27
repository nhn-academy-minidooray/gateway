package com.nhnacademy.minidooray.gateway.adaptor.tag;

import com.nhnacademy.minidooray.gateway.config.ProjectAdaptorProperties;
import com.nhnacademy.minidooray.gateway.domain.tag.request.TagCreateRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.tag.request.TagDeleteRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.tag.request.TagInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.tag.request.TagModifyRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.tag.response.TagInfoResponseDTO;
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
public class TagAdaptorImpl implements TagAdaptor{
  private final RestTemplate restTemplate;
  private final ProjectAdaptorProperties projectAdaptorProperties;

  @Override
  public List<TagInfoResponseDTO> selectTagListInProject(TagInfoRequestDTO tagInfoRequestDTO) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<String> requestEntity = new HttpEntity<>(headers);
    String urlTemplate = UriComponentsBuilder.fromHttpUrl(projectAdaptorProperties.getAddress() + "/tag/list")
        .queryParam("projectId", tagInfoRequestDTO.getProjectId())
        .queryParam("taskId", tagInfoRequestDTO.getTaskId())
        .encode()
        .toUriString();
    ResponseEntity<List<TagInfoResponseDTO>> responseEntity;
    try {
      responseEntity = restTemplate.exchange(
          urlTemplate,
          HttpMethod.GET,
          requestEntity,
          new ParameterizedTypeReference<List<TagInfoResponseDTO>>() {
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
  public boolean insertTagInProject(TagCreateRequestDTO tagCreateRequestDTO) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<TagCreateRequestDTO> requestEntity = new HttpEntity<>(tagCreateRequestDTO, headers);
    String urlTemplate = projectAdaptorProperties.getAddress() + "/tag/register";
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
  public boolean updateTag(Long tagId, TagModifyRequestDTO tagModifyRequestDTO) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<TagModifyRequestDTO> requestEntity = new HttpEntity<>(tagModifyRequestDTO, headers);
    String urlTemplate = projectAdaptorProperties.getAddress() + "/tag/{tagId}/modify";
    ResponseEntity<String> responseEntity;
    try {
      responseEntity = restTemplate.exchange(
          urlTemplate,
          HttpMethod.PUT,
          requestEntity,
          new ParameterizedTypeReference<>() {
          }, tagId
      );
      if(responseEntity.getStatusCode().value() == HttpStatus.OK.value())
        return true;
    } catch (HttpClientErrorException e) {

    } catch (HttpServerErrorException e) {

    }
    return false;
  }

  @Override
  public boolean deleteTag(TagDeleteRequestDTO tagDeleteRequestDTO) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<String> requestEntity = new HttpEntity<>(headers);
    String urlTemplate = projectAdaptorProperties.getAddress() + "/tag/{tagId}/delete";
    ResponseEntity<String> responseEntity;
    try {
      responseEntity = restTemplate.exchange(
          urlTemplate,
          HttpMethod.PUT,
          requestEntity,
          new ParameterizedTypeReference<>() {
          }, tagDeleteRequestDTO.getTagId()
      );
      if(responseEntity.getStatusCode().value() == HttpStatus.OK.value())
        return true;
    } catch (HttpClientErrorException e) {

    } catch (HttpServerErrorException e) {

    }
    return false;
  }
}
