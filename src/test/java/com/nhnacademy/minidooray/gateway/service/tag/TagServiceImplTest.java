package com.nhnacademy.minidooray.gateway.service.tag;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.nhnacademy.minidooray.gateway.adaptor.tag.TagAdaptor;
import com.nhnacademy.minidooray.gateway.domain.tag.request.TagCreateRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.tag.request.TagModifyRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TagServiceImplTest {

    @Mock
    private TagAdaptor tagAdaptor;

    @InjectMocks
    private TagServiceImpl tagService;

    @Test
    void testCreateTag() {
        TagCreateRequestDTO requestDTO = new TagCreateRequestDTO(1L, "tester");

        when(tagAdaptor.insertTagInProject(requestDTO))
                .thenReturn(true);

        assertTrue(tagService.createTag(requestDTO));
    }

    @Test
    void testUpdateTag() {
        TagModifyRequestDTO requestDTO = new TagModifyRequestDTO("tester");

        when(tagAdaptor.updateTag(1L, requestDTO))
                .thenReturn(true);

        assertTrue(tagService.updateTag(1L, requestDTO));
    }

}