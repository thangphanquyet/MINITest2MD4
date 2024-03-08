package com.minitest2.service.task;

import com.minitest2.model.Task;
import com.minitest2.repository.ITaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TaskService implements ITaskService{
    @Autowired
    private ITaskRepo taskRepo;
    @Override
    public Iterable<Task> findAll() {
        return taskRepo.findAll();
    }

    @Override
    public void save(Task task) {
        taskRepo.save(task);
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskRepo.findById(id);
    }

    @Override
    public void remove(Long id) {
        taskRepo.deleteById(id);
    }
}