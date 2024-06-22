import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { createTask } from '../features/tasks/taskSlice';

const TaskForm = () => {
  const [description, setDescription] = useState('');
  const [error, setError] = useState('');
  const dispatch = useDispatch();

  const handleSubmit = (e) => {
    e.preventDefault();
    if (description.trim() === '') {
      setError('Description cannot be empty');
      return;
    }
    dispatch(createTask({ description, active: true }));
    setDescription('');
    setError('');
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        value={description}
        onChange={(e) => setDescription(e.target.value)}
        placeholder="Enter task description"
      />
      {error && <span className="error">{error}</span>}
      <button type="submit">Add Task</button>
    </form>
  );
};

export default TaskForm;
