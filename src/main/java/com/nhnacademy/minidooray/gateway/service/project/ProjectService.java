package com.nhnacademy.minidooray.gateway.service.project;

import com.nhnacademy.minidooray.gateway.domain.project.request.ProjectInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.project.request.ProjectListInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.project.request.ProjectRegisterRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.project.response.ProjectInfoResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.project.response.ProjectListInfoResponseDTO;
import java.util.List;
import java.util.Optional;

public interface ProjectService {
  boolean createService(ProjectRegisterRequestDTO projectRegisterRequestDTO);
  List<ProjectListInfoResponseDTO> readProjectList(
      String accountId);
  Optional<ProjectInfoResponseDTO> readProject(ProjectInfoRequestDTO projectInfoRequestDTO);
}
