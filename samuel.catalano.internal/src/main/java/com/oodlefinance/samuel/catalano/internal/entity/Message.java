package com.oodlefinance.samuel.catalano.internal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import com.oodlefinance.samuel.catalano.internal.entity.base.BaseEntity;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "message")
public class Message extends BaseEntity {

  @Column(name = "message")
  private String message;
}
