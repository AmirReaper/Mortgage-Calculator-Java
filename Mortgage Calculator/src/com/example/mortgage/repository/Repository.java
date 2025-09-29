package com.example.repository;

import java.util.List;

/**
 * Generic repository interface.
 */
public interface Repository<T, ID> {
    void add(T entity);
    T findById(ID id);
    List<T> getAll();
    void remove(T entity);
}