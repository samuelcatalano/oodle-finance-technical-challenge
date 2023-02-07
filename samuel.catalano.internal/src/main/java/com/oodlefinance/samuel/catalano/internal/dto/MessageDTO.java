package com.oodlefinance.samuel.catalano.internal.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.oodlefinance.samuel.catalano.internal.dto.base.BaseDTO;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDTO extends BaseDTO {

  private Long id;

  @NotBlank(message = "message must not be blank")
  private String message;
}
