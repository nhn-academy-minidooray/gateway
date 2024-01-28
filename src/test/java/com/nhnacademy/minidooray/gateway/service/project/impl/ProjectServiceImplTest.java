package com.nhnacademy.minidooray.gateway.service.project.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.nhnacademy.minidooray.gateway.adaptor.project.ProjectAdaptor;
import com.nhnacademy.minidooray.gateway.domain.project.request.ProjectCreateRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.project.request.ProjectInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.project.request.ProjectListInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.project.response.ProjectInfoResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.project.response.ProjectListInfoResponseDTO;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {

    @Mock
    ProjectAdaptor projectAdaptor;

    @InjectMocks
    ProjectServiceImpl projectService;

    @Test
    void testCreateProject() {
        ProjectCreateRequestDTO requestDTO = new ProjectCreateRequestDTO("test", "detail", "tester");

        when(projectAdaptor.insertProject(requestDTO))
                .thenReturn(true);

        assertTrue(projectService.createProject(requestDTO));
    }

    @Test
    void testReadProjectList() {
        List<ProjectListInfoResponseDTO> expected = List.of(new ProjectListInfoResponseDTO());

        when(projectAdaptor.selectProjectListByAccountId(new ProjectListInfoRequestDTO("tester")))
                .thenReturn(expected);

        assertEquals(expected, projectService.readProjectList("tester"));
    }

    @Test
    void testReadProject() {
        ProjectInfoRequestDTO requestDTO = new ProjectInfoRequestDTO(1L);
        Optional<ProjectInfoResponseDTO> expected = Optional.of(new ProjectInfoResponseDTO());

        when(projectAdaptor.selectProjectDetailByProjectId(requestDTO))
                .thenReturn(expected);

        assertEquals(expected, projectService.readProject(requestDTO));
    }

}