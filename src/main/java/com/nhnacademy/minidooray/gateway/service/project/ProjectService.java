package com.nhnacademy.minidooray.gateway.service.project;

import com.nhnacademy.minidooray.gateway.domain.project.request.ProjectInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.project.request.ProjectCreateRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.project.response.ProjectInfoResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.project.response.ProjectListInfoResponseDTO;
import java.util.List;
import java.util.Optional;

public interface ProjectService {
  boolean createProject(ProjectCreateRequestDTO projectCreateRequestDTO);
  List<ProjectListInfoResponseDTO> readProjectList(
      String accountId);
  Optional<ProjectInfoResponseDTO> readProject(ProjectInfoRequestDTO projectInfoRequestDTO);
}
