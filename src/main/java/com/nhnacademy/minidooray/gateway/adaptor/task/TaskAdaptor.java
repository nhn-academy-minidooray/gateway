package com.nhnacademy.minidooray.gateway.adaptor.task;

import com.nhnacademy.minidooray.gateway.domain.task.request.TaskCreateRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.task.request.TaskDeleteRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.task.request.TaskInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.task.response.TaskDetailResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.task.response.TaskInfoResponseDTO;
import java.util.List;
import java.util.Optional;

public interface TaskAdaptor {
  List<TaskInfoResponseDTO> selectTaskListInProject(TaskInfoRequestDTO taskInfoRequestDTO);
  Optional<TaskDetailResponseDTO> selectTaskByTaskId(Long taskId);

  boolean insertTaskInProject(TaskCreateRequestDTO taskCreateRequestDTO);
  boolean updateTask(Long taskId, TaskCreateRequestDTO taskModifyRequestDTO);
  boolean deleteTask(TaskDeleteRequestDTO taskDeleteRequestDTO);
}
