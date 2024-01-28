package com.nhnacademy.minidooray.gateway.service.tag;

import com.nhnacademy.minidooray.gateway.domain.tag.request.TagCreateRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.tag.request.TagDeleteRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.tag.request.TagListInProjectRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.tag.request.TagListInTaskRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.tag.request.TagModifyRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.tag.response.TagInfoResponseDTO;
import java.util.List;

public interface TagService {
  boolean createTag(TagCreateRequestDTO tagCreateRequestDTO);
  boolean updateTag(Long tagId, TagModifyRequestDTO tagModifyRequestDTO);
  boolean deleteTag(TagDeleteRequestDTO tagDeleteRequestDTO);
  List<TagInfoResponseDTO> readTagListInTask(TagListInTaskRequestDTO tagListInTaskRequestDTO);
  List<TagInfoResponseDTO> readTagListInProject(TagListInProjectRequestDTO tagListInProjectRequestDTO);
}
