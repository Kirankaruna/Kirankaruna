package com.example.todo.service;

import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public String addTask(Task task) {
        taskRepository.save(task);
        return "Task added successfully";
    }

    public List<Task> viewAllTasks() {
        return taskRepository.findAll();
    }

    public String deleteTask(String taskId){
        if(taskRepository.existsById(taskId)){
            taskRepository.deleteById(taskId);
            return "Task deleted successfully";
        }
        return "Task with  " + taskId + "  doesn't exist";
    }
}
