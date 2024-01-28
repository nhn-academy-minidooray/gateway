package com.nhnacademy.minidooray.gateway.service.tag;

import com.nhnacademy.minidooray.gateway.adaptor.tag.TagAdaptor;
import com.nhnacademy.minidooray.gateway.domain.tag.request.TagCreateRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.tag.request.TagDeleteRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.tag.request.TagListInProjectRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.tag.request.TagListInTaskRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.tag.request.TagModifyRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.tag.response.TagInfoResponseDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService{
  private final TagAdaptor tagAdaptor;

  @Override
  public boolean createTag(TagCreateRequestDTO tagCreateRequestDTO) {
    return tagAdaptor.insertTagInProject(tagCreateRequestDTO);
  }

  @Override
  public boolean updateTag(Long tagId, TagModifyRequestDTO tagModifyRequestDTO) {
    return tagAdaptor.updateTag(tagId, tagModifyRequestDTO);
  }

  @Override
  public boolean deleteTag(TagDeleteRequestDTO tagDeleteRequestDTO) {
    return tagAdaptor.deleteTag(tagDeleteRequestDTO);
  }

  @Override
  public List<TagInfoResponseDTO> readTagListInTask(TagListInTaskRequestDTO tagListInTaskRequestDTO) {
    return tagAdaptor.selectTagListInTask(tagListInTaskRequestDTO);
  }

  @Override
  public List<TagInfoResponseDTO> readTagListInProject(
      TagListInProjectRequestDTO tagListInProjectRequestDTO) {
    return tagAdaptor.selectTagListInProject(tagListInProjectRequestDTO);
  }
}
