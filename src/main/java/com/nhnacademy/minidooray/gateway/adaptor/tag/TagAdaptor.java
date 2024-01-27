package com.nhnacademy.minidooray.gateway.adaptor.tag;

import com.nhnacademy.minidooray.gateway.domain.tag.request.TagCreateRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.tag.request.TagDeleteRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.tag.request.TagInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.tag.request.TagModifyRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.tag.response.TagInfoResponseDTO;
import java.util.List;

public interface TagAdaptor {
  List<TagInfoResponseDTO> selectTagListInProject(TagInfoRequestDTO tagInfoRequestDTO);
  boolean insertTagInProject(TagCreateRequestDTO tagCreateRequestDTO);
  boolean updateTag(Long tagId, TagModifyRequestDTO tagModifyRequestDTO);
  boolean deleteTag(TagDeleteRequestDTO tagDeleteRequestDTO);
}
