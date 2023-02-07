package com.oodlefinance.samuel.catalano.internal.service;

import jakarta.persistence.EntityNotFoundException;

import com.oodlefinance.samuel.catalano.internal.dto.base.BaseDTO;
import com.oodlefinance.samuel.catalano.internal.exception.ServiceException;

import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

public interface BaseService <D extends BaseDTO> {

  D create(D dto) throws ServiceException;

  D update(Long id, D dto) throws ServiceException;

  D findById(Long id) throws EntityNotFoundException;

  List<D> findAll() throws ServiceException;

  void deleteById(Long id) throws EntityNotFoundException, DataIntegrityViolationException;
}
