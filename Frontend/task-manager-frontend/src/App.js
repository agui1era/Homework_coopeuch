import React from 'react';
import TaskList from './components/TaskList';
import './App.css'; // Importa el archivo de estilos

function App() {
  return (
    <div className="App">
      <h1>Task Manager</h1>
      <TaskList />
    </div>
  );
}

export default App; // Asegúrate de que el componente se exporte correctamente
