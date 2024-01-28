package com.nhnacademy.minidooray.gateway.service.task;

import com.nhnacademy.minidooray.gateway.domain.task.request.TaskCreateRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.task.request.TaskDeleteRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.task.request.TaskInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.task.response.TaskDetailResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.task.response.TaskInfoResponseDTO;
import java.util.List;
import java.util.Optional;

public interface TaskService {
  List<TaskInfoResponseDTO> readTaskListInProject(TaskInfoRequestDTO taskInfoRequestDTO);
  boolean createTaskInProject(TaskCreateRequestDTO taskCreateRequestDTO);
  boolean updateTask(Long taskId, TaskCreateRequestDTO taskModifyRequestDTO);
  boolean deleteTesk(TaskDeleteRequestDTO taskDeleteRequestDTO);
  Optional<TaskDetailResponseDTO> readTaskByTaskId(Long taskId);
}
