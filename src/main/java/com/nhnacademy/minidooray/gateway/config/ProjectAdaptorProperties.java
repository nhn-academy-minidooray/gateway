package com.nhnacademy.minidooray.gateway.config;

import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "com.nhnacademy.minidooray.project")
@Data
public class ProjectAdaptorProperties {
  @NotNull
  private String address;
}
