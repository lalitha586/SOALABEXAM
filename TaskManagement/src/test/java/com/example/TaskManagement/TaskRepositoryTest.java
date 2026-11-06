package com.example.TaskManagement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import jakarta.persistence.EntityManager;

@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void testSaveAndFindTask() {

        Task task = new Task(
            "Complete Java Project",
            "Perform repository integration testing"
        );

        Task savedTask = taskRepository.save(task);

        assertNotNull(savedTask.getId());

        entityManager.flush();
        entityManager.clear();

        Task retrievedTask = taskRepository
                .findById(savedTask.getId())
                .orElse(null);

        assertNotNull(retrievedTask);

        assertEquals("Complete Java Project",
                retrievedTask.getTitle());

        assertEquals("Perform repository integration testing",
                retrievedTask.getDescription());

        System.out.println("Task ID: " + retrievedTask.getId());
        System.out.println("Task saved successfully!");
    }
}