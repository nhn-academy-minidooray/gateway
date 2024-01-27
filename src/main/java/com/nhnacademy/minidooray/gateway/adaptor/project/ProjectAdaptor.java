package com.nhnacademy.minidooray.gateway.adaptor.project;

import com.nhnacademy.minidooray.gateway.domain.project.request.ProjectInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.project.request.ProjectListInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.project.request.ProjectCreateRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.project.response.ProjectInfoResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.project.response.ProjectListInfoResponseDTO;
import java.util.List;
import java.util.Optional;

public interface ProjectAdaptor {
  boolean insertProject(ProjectCreateRequestDTO projectCreateRequestDTO);
  List<ProjectListInfoResponseDTO> selectProjectListByAccountId(
      ProjectListInfoRequestDTO projectListInfoRequestDTO);

  Optional<ProjectInfoResponseDTO> selectProjectDetailByProjectId(
      ProjectInfoRequestDTO projectInfoRequestDTO);
}
