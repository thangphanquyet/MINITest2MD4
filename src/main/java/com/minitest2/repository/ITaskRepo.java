package com.minitest2.repository;

import com.minitest2.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface ITaskRepo extends CrudRepository<Task, Long> {
}