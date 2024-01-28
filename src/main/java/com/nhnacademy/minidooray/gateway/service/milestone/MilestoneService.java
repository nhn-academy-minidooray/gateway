package com.nhnacademy.minidooray.gateway.service.milestone;

import com.nhnacademy.minidooray.gateway.domain.milestone.request.MilestoneRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.milestone.response.MilestoneDetailResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.milestone.response.MilestoneInfoResponseDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface MilestoneService {
  Optional<MilestoneInfoResponseDTO> readMilestoneInTask(Long taskId);
  List<MilestoneInfoResponseDTO> readMilestoneListInProject(
      Long projectId);
  Optional<MilestoneDetailResponseDTO> readMilestoneDetail(Long milestoneId);
  boolean createMilestoneInProject(MilestoneRequestDTO milestoneRequestDTO);
  boolean updateMilestone(Long milestoneId, MilestoneRequestDTO milestoneRequestDTO);
  boolean deleteMilestone(Long milestoneId);
}
