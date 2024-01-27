package com.nhnacademy.minidooray.gateway.service.task;

import com.nhnacademy.minidooray.gateway.domain.task.request.TaskCreateRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.task.request.TaskDeleteRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.task.request.TaskInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.task.response.TaskInfoResponseDTO;
import java.util.List;

public interface TaskService {
  List<TaskInfoResponseDTO> readTaskListInProject(TaskInfoRequestDTO taskInfoRequestDTO);
  boolean createTaskInProject(TaskCreateRequestDTO taskCreateRequestDTO);
  boolean updateTask(Long taskId, TaskCreateRequestDTO taskModifyRequestDTO);
  boolean deleteTesk(TaskDeleteRequestDTO taskDeleteRequestDTO);
}
