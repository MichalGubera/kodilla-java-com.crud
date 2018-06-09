package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@Transactional
@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTestSuite {

    @InjectMocks
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto((long)1, "testTask1", "testContent1");
        //When
        Task task1 = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(1l, task1.getId().longValue());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task2 = new Task((long)2, "testTask2", "testContent2");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task2);
        //Then
        assertEquals("testTask2", taskDto.getTitle());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        List<Task> tasks = new ArrayList<>();
        Task task3 = new Task(3l,"testTask3", "testContent3");
        tasks.add(task3);
        //When
        List<TaskDto> listOfTasksDto = taskMapper.mapToTaskDtoList(tasks);
        //Then
        assertEquals(1,listOfTasksDto.size());
        assertEquals("testContent3", listOfTasksDto.get(0).getContent());
    }
}
