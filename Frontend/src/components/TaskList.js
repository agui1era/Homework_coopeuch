import React, { useEffect, useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { fetchTasks, deleteTask } from '../features/tasks/taskSlice';
import TaskForm from './TaskForm';
import EditTaskForm from './EditTaskForm';

const TaskList = () => {
  const dispatch = useDispatch();
  const tasks = useSelector((state) => state.tasks.items);
  const [editingTask, setEditingTask] = useState(null);

  useEffect(() => {
    dispatch(fetchTasks());
  }, [dispatch]);

  const handleDelete = (id) => {
    dispatch(deleteTask(id));
  };

  const handleEdit = (task) => {
    setEditingTask(task);
  };

  const cancelEdit = () => {
    setEditingTask(null);
  };

  return (
    <div>
      <h2>Task List</h2>
      <TaskForm />
      <ul>
        {tasks.map(task => (
          <li key={task.id}>
            {editingTask && editingTask.id === task.id ? (
              <EditTaskForm task={editingTask} onCancel={cancelEdit} />
            ) : (
              <>
                {task.description}
                <div>
                  <button type="button" onClick={() => handleEdit(task)}>Edit</button>
                  <button type="button" className="delete" onClick={() => handleDelete(task.id)}>Delete</button>
                </div>
              </>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default TaskList;
