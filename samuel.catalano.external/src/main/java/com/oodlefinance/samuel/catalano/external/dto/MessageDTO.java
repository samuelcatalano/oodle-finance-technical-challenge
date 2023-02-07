package com.oodlefinance.samuel.catalano.external.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.oodlefinance.samuel.catalano.external.dto.base.BaseDTO;

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
