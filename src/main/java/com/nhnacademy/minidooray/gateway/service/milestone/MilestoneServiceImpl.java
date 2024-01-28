package com.nhnacademy.minidooray.gateway.service.milestone;

import com.nhnacademy.minidooray.gateway.adaptor.milestone.MilestoneAdaptor;
import com.nhnacademy.minidooray.gateway.domain.milestone.request.MilestoneRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.milestone.response.MilestoneDetailResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.milestone.response.MilestoneInfoResponseDTO;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MilestoneServiceImpl implements MilestoneService{
  private final MilestoneAdaptor milestoneAdaptor;

  @Override
  public Optional<MilestoneInfoResponseDTO> readMilestoneInTask(Long taskId) {
    return milestoneAdaptor.selectMilestoneInTask(taskId);
  }

  @Override
  public List<MilestoneInfoResponseDTO> readMilestoneListInProject(Long projectId) {
    return milestoneAdaptor.selectMilestoneListInProject(projectId);
  }

  @Override
  public Optional<MilestoneDetailResponseDTO> readMilestoneDetail(Long milestoneId) {
    return milestoneAdaptor.selectMilestoneDetail(milestoneId);
  }

  @Override
  public boolean createMilestoneInProject(MilestoneRequestDTO milestoneRequestDTO) {
    return milestoneAdaptor.insertMilestoneInProject(milestoneRequestDTO);
  }

  @Override
  public boolean updateMilestone(Long milestoneId, MilestoneRequestDTO milestoneRequestDTO) {
    return milestoneAdaptor.updateMilestone(milestoneId, milestoneRequestDTO);
  }

  @Override
  public boolean deleteMilestone(Long milestoneId) {
    return milestoneAdaptor.deleteMilestone(milestoneId);
  }
}
