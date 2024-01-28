package com.nhnacademy.minidooray.gateway.service.milestone;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.nhnacademy.minidooray.gateway.adaptor.milestone.MilestoneAdaptor;
import com.nhnacademy.minidooray.gateway.domain.milestone.request.MilestoneRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.milestone.response.MilestoneDetailResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.milestone.response.MilestoneInfoResponseDTO;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MilestoneServiceImplTest {

    @Mock
    private MilestoneAdaptor milestoneAdaptor;

    @InjectMocks
    private MilestoneServiceImpl milestoneService;

    @Test
    void testReadMilestoneInTask() {
        Optional<MilestoneInfoResponseDTO> expected = Optional.of(new MilestoneInfoResponseDTO());

        when(milestoneAdaptor.selectMilestoneInTask(1L))
                .thenReturn(expected);

        assertEquals(expected, milestoneService.readMilestoneInTask(1L));
    }

    @Test
    void testReadMilestoneListInProject() {
        List<MilestoneInfoResponseDTO> expected = List.of(new MilestoneInfoResponseDTO());

        when(milestoneAdaptor.selectMilestoneListInProject(1L))
                .thenReturn(expected);

        assertEquals(expected, milestoneService.readMilestoneListInProject(1L));
    }

    @Test
    void testReadMilestoneDetail() {
        Optional<MilestoneDetailResponseDTO> expected = Optional.of(new MilestoneDetailResponseDTO());

        when(milestoneAdaptor.selectMilestoneDetail(1L))
                .thenReturn(expected);

        assertEquals(expected, milestoneService.readMilestoneDetail(1L));
    }

    @Test
    void testCreateMilestoneInProject() {
        when(milestoneAdaptor.insertMilestoneInProject(any(MilestoneRequestDTO.class)))
                .thenReturn(true);

        assertTrue(milestoneAdaptor.insertMilestoneInProject(new MilestoneRequestDTO()));
    }

    @Test
    void testUpdateMilestone() {
        when(milestoneAdaptor.updateMilestone(anyLong(), any(MilestoneRequestDTO.class)))
                .thenReturn(true);

        assertTrue(milestoneAdaptor.updateMilestone(1L, new MilestoneRequestDTO()));
    }

    @Test
    void testDeleteMilestone() {
        when(milestoneAdaptor.deleteMilestone(anyLong()))
                .thenReturn(true);

        assertTrue(milestoneAdaptor.deleteMilestone(1L));
    }

}