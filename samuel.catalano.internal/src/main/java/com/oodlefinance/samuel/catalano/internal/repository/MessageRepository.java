package com.oodlefinance.samuel.catalano.internal.repository;

import com.oodlefinance.samuel.catalano.internal.entity.Message;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
}