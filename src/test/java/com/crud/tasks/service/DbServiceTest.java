package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DbServiceTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private DbService dbService = new DbService(taskRepository);

    @Test
    void getAllTasks() {
        //Given
        taskRepository.deleteAll();
        Task task = new Task();
        task.setTitle("title");
        task.setContent("content");
        taskRepository.save(task);
        //When
        List<Task> tasks = dbService.getAllTasks();
        //Then
        assertEquals(1, tasks.size());
        //CleanUp
        taskRepository.deleteAll();
    }

    @Test
    void getTask() throws TaskNotFoundException {
        //Given
        Task task = new Task();
        task.setTitle("title");
        task.setContent("content");
        taskRepository.save(task);
        //When
        Task foundedTask = dbService.getTask(task.getId());
        //Then
        assertEquals(task.getId(), foundedTask.getId());
        assertEquals("title", foundedTask.getTitle());
        assertEquals("content", foundedTask.getContent());
        //CleanUp
        taskRepository.deleteAll();
    }

    @Test
    void deleteTask() throws TaskNotFoundException {
        //Given
        Task task = new Task();
        task.setTitle("title");
        task.setContent("content");
        taskRepository.save(task);
        //When
        dbService.deleteTask(task.getId());
        //Then
        assertFalse(taskRepository.existsById(task.getId()));
    }
}
