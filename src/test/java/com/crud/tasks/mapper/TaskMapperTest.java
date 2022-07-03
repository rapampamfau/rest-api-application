package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskMapperTest {

    private TaskMapper taskMapper = new TaskMapper();

    @Test
    void mapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "testTitle", "testContent");
        //When
        Task testTask = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(taskDto.getId(), testTask.getId());
        assertEquals(taskDto.getTitle(), testTask.getTitle());
        assertEquals(taskDto.getContent(), testTask.getContent());
    }

    @Test
    void mapToTaskDto() {
        //Given
        Task task = new Task(1L, "testTitle", "testContent");
        //When
        TaskDto testTaskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(task.getId(), testTaskDto.getId());
        assertEquals(task.getTitle(), testTaskDto.getTitle());
        assertEquals(task.getContent(), testTaskDto.getContent());
    }

    @Test
    void mapToTaskDtoList() {
        //Given
        List<Task> taskList = new ArrayList<>();
        Task task = new Task(1L, "testTitle", "testContent");
        taskList.add(task);
        //When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(taskList);
        //Then
        assertEquals(taskList.get(0).getId(), taskDtos.get(0).getId());
        assertEquals(taskList.get(0).getTitle(), taskDtos.get(0).getTitle());
        assertEquals(taskList.get(0).getContent(), taskDtos.get(0).getContent());
    }
}
