package com.nhnacademy.minidooray.gateway.domain.task.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDetailResponseDTO {
    private Long id;
    private String name;
    private String detail;
}
