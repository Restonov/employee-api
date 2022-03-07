package com.restonov.task.service;

import java.util.List;

public interface CrudService<T, I> {

  T create(T entity);
  List<T> findAll();
  T findById(I id);
  T update(I id, T entity);
  void delete(I id);
}
