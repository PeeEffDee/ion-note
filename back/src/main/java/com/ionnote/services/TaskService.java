package com.ionnote.services;

import com.ionnote.dtos.task.CreateTaskDTO;
import com.ionnote.dtos.task.DeleteTaskDTO;
import com.ionnote.dtos.task.ReadTaskDTO;
import com.ionnote.dtos.task.UpdateTaskDTO;
import com.ionnote.entities.Task;
import com.ionnote.exceptions.TaskNotFoundException;
import com.ionnote.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;

    //Create
    public void createTask(CreateTaskDTO dto) {
        var tempTask = Task.builder()
                .uuid(UUID.randomUUID())
                .name(dto.getName())
                .deadline(dto.getDeadline())
                .completed(Boolean.FALSE)
                .build();

        taskRepository.save(tempTask);
    }

    //Read
    public Task readTask(ReadTaskDTO dto) throws TaskNotFoundException {
        return taskRepository.findById(dto.getUuid()).orElseThrow(TaskNotFoundException::new);
    }

    public List<Task> readAllTasks() {
        return taskRepository.findAll();
    }

    //Update
    public void updateTask(UpdateTaskDTO dto) throws TaskNotFoundException {
        var task = taskRepository.findById(dto.getUuid()).orElseThrow(TaskNotFoundException::new);
        BeanUtils.copyProperties(dto, task);
        taskRepository.save(task);
    }

    //Delete
    public void deleteTask(DeleteTaskDTO dto) throws TaskNotFoundException {
        var task = taskRepository.findById(dto.getUuid()).orElseThrow(TaskNotFoundException::new);
        taskRepository.delete(task);
    }

}
