import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';

// Asynchronous thunks
export const fetchTasks = createAsyncThunk('tasks/fetchTasks', async () => {
  const response = await fetch('http://localhost:8080/api/tasks');
  const data = await response.json();
  return data;
});

export const createTask = createAsyncThunk('tasks/createTask', async (task) => {
  const response = await fetch('http://localhost:8080/api/tasks', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(task),
  });
  const data = await response.json();
  return data;
});

export const updateTask = createAsyncThunk('tasks/updateTask', async ({ id, task }) => {
  const response = await fetch(`http://localhost:8080/api/tasks/${id}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(task),
  });
  const data = await response.json();
  return data;
});

export const deleteTask = createAsyncThunk('tasks/deleteTask', async (id) => {
  await fetch(`http://localhost:8080/api/tasks/${id}`, {
    method: 'DELETE',
  });
  return id;
});

const taskSlice = createSlice({
  name: 'tasks',
  initialState: {
    items: [],
    status: 'idle',
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchTasks.fulfilled, (state, action) => {
        state.items = action.payload;
      })
      .addCase(createTask.fulfilled, (state, action) => {
        state.items.push(action.payload.data);
      })
      .addCase(updateTask.fulfilled, (state, action) => {
        const index = state.items.findIndex(task => task.id === action.payload.data.id);
        if (index !== -1) {
          state.items[index] = action.payload.data;
        }
      })
      .addCase(deleteTask.fulfilled, (state, action) => {
        state.items = state.items.filter(task => task.id !== action.payload);
      });
  },
});

export default taskSlice.reducer;
