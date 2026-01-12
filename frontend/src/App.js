import { useEffect, useState } from "react";
import "./App.css";

function App() {
  const [tasks, setTasks] = useState([]);
  const [title, setTitle] = useState("");

  // 一覧取得
  useEffect(() => {
    fetch("http://localhost:8080/api/tasks")
      .then(res => res.json())
      .then(data => setTasks(data))
      .catch(err => console.error(err));
  }, []);

  // 追加
  const addTask = () => {
    fetch("http://localhost:8080/api/tasks", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ title }),
    })
      .then(res => res.json())
      .then(newTask => {
        setTasks([...tasks, newTask]);
        setTitle("");
      });
  };

  return (
    <div style={{ padding: "20px" }}>
      <h1>Task Management</h1>

      <input
        value={title}
        onChange={e => setTitle(e.target.value)}
        placeholder="タスク名"
      />
      <button onClick={addTask}>追加</button>

      <ul>
        {tasks.map(task => (
          <li key={task.id}>
            {task.title}（{task.status}）
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;

