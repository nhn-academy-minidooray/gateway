package com.nhnacademy.minidooray.gateway.service.project.impl;

import com.nhnacademy.minidooray.gateway.adaptor.project.ProjectAdaptor;
import com.nhnacademy.minidooray.gateway.domain.project.request.ProjectInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.project.request.ProjectListInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.project.request.ProjectRegisterRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.project.response.ProjectInfoResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.project.response.ProjectListInfoResponseDTO;
import com.nhnacademy.minidooray.gateway.service.project.ProjectService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
  private final ProjectAdaptor projectAdaptor;

  @Override
  public boolean createService(ProjectRegisterRequestDTO projectRegisterRequestDTO) {
    return projectAdaptor.insertProject(projectRegisterRequestDTO);
  }

  @Override
  public List<ProjectListInfoResponseDTO> readProjectList(String accountId) {
    return projectAdaptor.selectProjectListByAccountId(new ProjectListInfoRequestDTO(accountId));
  }

  @Override
  public Optional<ProjectInfoResponseDTO> readProject(ProjectInfoRequestDTO projectInfoRequestDTO) {
    return projectAdaptor.selectProjectDetailByProjectId(projectInfoRequestDTO);
  }
}
