package com.nhnacademy.minidooray.gateway.adaptor.milestone;

import com.nhnacademy.minidooray.gateway.domain.milestone.request.MilestoneRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.milestone.response.MilestoneDetailResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.milestone.response.MilestoneInfoResponseDTO;
import java.util.List;
import java.util.Optional;

public interface MilestoneAdaptor {
  Optional<MilestoneInfoResponseDTO> selectMilestoneInTask(Long taskId);
  List<MilestoneInfoResponseDTO> selectMilestoneListInProject(
      Long projectId);
  Optional<MilestoneDetailResponseDTO> selectMilestoneDetail(Long milestoneId);
  boolean insertMilestoneInProject(MilestoneRequestDTO milestoneRequestDTO);
  boolean updateMilestone(Long milestoneId, MilestoneRequestDTO milestoneRequestDTO);
  boolean deleteMilestone(Long milestoneId);
}
