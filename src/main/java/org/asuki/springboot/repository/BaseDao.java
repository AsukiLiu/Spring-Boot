package org.asuki.springboot.repository;

import org.asuki.springboot.model.BaseEntity;

public interface BaseDao<T extends BaseEntity> {
    T getById(Long id);

    T save(T t);

    void delete(Long id);

    Long count();
}
