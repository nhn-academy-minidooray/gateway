package com.nhnacademy.minidooray.gateway.controller.tag;

import com.nhnacademy.minidooray.gateway.domain.tag.request.TagCreateRequestDTO;
import com.nhnacademy.minidooray.gateway.service.tag.TagService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class TagCreateControllerTest {

  @InjectMocks
  private TagCreateController tagCreateController;

  @Mock
  private TagService tagService;

  @Mock
  private RedirectAttributes redirectAttributes;

  @Test
  void testPostTagInProject() {
    // Given
    TagCreateRequestDTO tagCreateRequestDTO = new TagCreateRequestDTO();
    when(tagService.createTag(any())).thenReturn(true);

    // When
    String result = tagCreateController.postTagInProject(tagCreateRequestDTO, redirectAttributes);

    // Then
    assertEquals("redirect:/project/" + tagCreateRequestDTO.getProjectId(), result);
    verify(tagService, times(1)).createTag(eq(tagCreateRequestDTO));
    verify(redirectAttributes, times(1)).addFlashAttribute(eq("info"), eq("태그 추가가 완료되었습니다"));
  }

  @Test
  void testPostTagInProjectWithError() {
    // Given
    TagCreateRequestDTO tagCreateRequestDTO = new TagCreateRequestDTO();
    when(tagService.createTag(any())).thenReturn(false);

    // When
    String result = tagCreateController.postTagInProject(tagCreateRequestDTO, redirectAttributes);

    // Then
    assertEquals("redirect:/project/" + tagCreateRequestDTO.getProjectId(), result);
    verify(tagService, times(1)).createTag(eq(tagCreateRequestDTO));
    verify(redirectAttributes, times(1)).addFlashAttribute(eq("error"), eq("태그를 추가하면서 오류가 발생하였습니다"));
  }
}
