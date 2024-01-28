package com.nhnacademy.minidooray.gateway.service.task;

import com.nhnacademy.minidooray.gateway.adaptor.task.TaskAdaptor;
import com.nhnacademy.minidooray.gateway.domain.task.request.TaskCreateRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.task.request.TaskDeleteRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.task.request.TaskInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.task.response.TaskDetailResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.task.response.TaskInfoResponseDTO;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{
  private final TaskAdaptor taskAdaptor;

  @Override
  public List<TaskInfoResponseDTO> readTaskListInProject(TaskInfoRequestDTO taskInfoRequestDTO) {
    List<TaskInfoResponseDTO> taskList = taskAdaptor.selectTaskListInProject(taskInfoRequestDTO);
    for(TaskInfoResponseDTO t : taskList) {
      for(String tag: t.getTagNameList()) {
      }
    }
    return taskList;
  }

  @Override
  public boolean createTaskInProject(TaskCreateRequestDTO taskCreateRequestDTO) {
    return taskAdaptor.insertTaskInProject(taskCreateRequestDTO);
  }

  @Override
  public boolean updateTask(Long taskId, TaskCreateRequestDTO taskModifyRequestDTO) {
    return taskAdaptor.updateTask(taskId, taskModifyRequestDTO);
  }

  @Override
  public boolean deleteTesk(TaskDeleteRequestDTO taskDeleteRequestDTO) {
    return taskAdaptor.deleteTask(taskDeleteRequestDTO);
  }

  @Override
  public Optional<TaskDetailResponseDTO> readTaskByTaskId(Long taskId) {
    return taskAdaptor.selectTaskByTaskId(taskId);
  }
}
