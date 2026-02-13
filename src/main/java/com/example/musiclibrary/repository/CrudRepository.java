package com.example.musiclibrary.repository;

import java.util.List;

public interface CrudRepository<T, ID> {
    ID create(T entity);
    T findById(ID id);
    List<T> findAll();
    void update(ID id, T entity);
    void delete(ID id);

    default boolean existsById(ID id) {
        return findById(id) != null;
    }
}
