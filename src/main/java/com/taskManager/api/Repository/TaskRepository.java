package com.taskManager.api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.taskManager.api.Entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
