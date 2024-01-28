package com.nhnacademy.minidooray.gateway.adaptor.comment;

import com.nhnacademy.minidooray.gateway.config.ProjectAdaptorProperties;
import com.nhnacademy.minidooray.gateway.domain.comment.request.CommentCreateRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.comment.request.CommentModifyRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.comment.response.CommentResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.tag.request.TagCreateRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.tag.response.TagInfoResponseDTO;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
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
public class CommentAdaptorImpl implements CommentAdaptor{
  private final ProjectAdaptorProperties projectAdaptorProperties;
  private final RestTemplate restTemplate;

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
  public List<CommentResponseDTO> selectCommentListByTaskId(Long taskId) {
    HttpEntity<String> requestEntity = new HttpEntity<>(getHeader());
    String urlTemplate = UriComponentsBuilder.fromHttpUrl(projectAdaptorProperties.getAddress() + "/comment/list")
        .queryParam("taskId", taskId)
        .encode()
        .toUriString();
    ResponseEntity<List<CommentResponseDTO>> responseEntity;
    try {
      responseEntity = restTemplate.exchange(
          urlTemplate,
          HttpMethod.GET,
          requestEntity,
          new ParameterizedTypeReference<>() {
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
  public boolean insertCommentInProject(CommentCreateRequestDTO commentCreateRequestDTO) {
    HttpEntity<CommentCreateRequestDTO> requestEntity = new HttpEntity<>(commentCreateRequestDTO, getHeader());
    String urlTemplate = projectAdaptorProperties.getAddress() + "/comment/register";
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
  public boolean updateCommentInProject(Long commentId, CommentModifyRequestDTO commentModifyRequestDTO) {
    HttpEntity<CommentModifyRequestDTO> requestEntity = new HttpEntity<>(commentModifyRequestDTO, getHeader());
    String urlTemplate = projectAdaptorProperties.getAddress() + "/comment/{commentId}/modify";
    ResponseEntity<String> responseEntity;
    try {
      responseEntity = restTemplate.exchange(
          urlTemplate,
          HttpMethod.PUT,
          requestEntity,
          new ParameterizedTypeReference<>() {
          }, commentId
      );
      if(responseEntity.getStatusCode().value() == HttpStatus.OK.value())
        return true;
    } catch (HttpClientErrorException e) {

    } catch (HttpServerErrorException e) {

    }
    return false;
  }
}
