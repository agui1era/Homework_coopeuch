import React, { useState, useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { updateTask } from '../features/tasks/taskSlice';

const EditTaskForm = ({ task, onCancel }) => {
  const [description, setDescription] = useState(task ? task.description : '');
  const [error, setError] = useState('');

  useEffect(() => {
    if (task) {
      setDescription(task.description);
    }
  }, [task]);

  const dispatch = useDispatch();

  const handleSubmit = (e) => {
    e.preventDefault();
    if (description.trim() === '') {
      setError('Description cannot be empty');
      return;
    }
    if (task) {
      dispatch(updateTask({ id: task.id, task: { description, active: task.active } }));
      onCancel();
    }
    setError('');
  };

  if (!task) {
    return null;
  }

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        value={description}
        onChange={(e) => setDescription(e.target.value)}
        placeholder="Enter task description"
      />
      {error && <span className="error">{error}</span>}
      <button type="submit">Update Task</button>
      <button type="button" onClick={onCancel}>Cancel</button>
    </form>
  );
};

export default EditTaskForm;
