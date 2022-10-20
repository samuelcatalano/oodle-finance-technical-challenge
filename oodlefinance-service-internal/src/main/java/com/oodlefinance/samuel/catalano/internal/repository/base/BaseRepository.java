package com.oodlefinance.samuel.catalano.internal.repository.base;

import com.oodlefinance.samuel.catalano.internal.model.base.BaseEntity;

import java.util.List;

public interface BaseRepository<E extends BaseEntity> {

    /**
     * List all entities.
     * @return list of all entities
     * @throws Exception the exception to be launched
     */
    List<E> findAll() throws Exception;

    /**
     * Find an entity by id.
     * @param id the entity id
     * @return entity by id
     * @throws Exception the exception to be launched
     */
    E findById(final Long id) throws Exception;

    /**
     * Create a new entity.
     * @param entity the entity
     * @return E the entity created
     * @throws Exception the exception to be launched
     */
    E create(final E entity) throws Exception;

    /**
     * Delete an entity.
     * @param id the id to be deleted
     * @return E the entity deleted
     * @throws Exception the exception to be launched
     */
    void delete(final Long id) throws Exception;
}