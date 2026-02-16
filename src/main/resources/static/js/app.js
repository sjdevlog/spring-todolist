const API_BASE = "/api/todos";

function toggleDesc(el) {
  el.closest(".todo-item").classList.toggle("open");
}

function goEdit(id) {
  location.href = `edit.html?id=${id}`;
}

function toggleTodo(id) {
  fetch(`${API_BASE}/${id}/toggle`, { method: "PATCH" })
    .then(() => loadTodos());
}

function deleteTodo(id) {
  fetch(`${API_BASE}/${id}`, { method: "DELETE" })
    .then(() => loadTodos());
}

function loadTodos() {
  fetch(API_BASE, { headers: { "Accept": "application/json" } })
    .then(res => res.json())
    .then(todos => {
      // ✅ 카운터
      const completedCount = todos.filter(t => t.completed).length;
      const statsEl = document.getElementById("todo-stats");
      if (statsEl) statsEl.textContent = `${completedCount} / ${todos.length} 완료`;

      // ✅ 리스트 렌더
      const list = document.getElementById("todo-list");
      if (!list) return;

      list.innerHTML = "";

      todos.forEach(todo => {
        const li = document.createElement("li");
        li.className = "todo-item";

        li.innerHTML = `
        <div class="todo-main" onclick="toggleDesc(this)">
            <div class="todo-title ${todo.completed ? "done" : ""}">
                ${todo.title}
            </div>
            <div class="todo-desc">
                ${todo.description ? todo.description : "설명 없음"}
            </div>
        </div>

        <div class="actions">
            <button onclick="toggleTodo(${todo.id}); event.stopPropagation();">
                ${todo.completed ? "되돌리기" : "완료"}
            </button>
            <button class="secondary" onclick="goEdit(${todo.id}); event.stopPropagation();">수정</button>
            <button class="secondary" onclick="deleteTodo(${todo.id}); event.stopPropagation();">삭제</button>
        </div>
        `;



        list.appendChild(li);
      });
    })
    .catch(err => {
      console.error(err);
      alert("목록 로딩 중 오류가 발생했습니다. (콘솔 확인)");
    });
}

document.addEventListener("DOMContentLoaded", () => {
  loadTodos();

  // 다크모드 토글(있으면)
  const toggleBtn = document.getElementById("theme-toggle");
  if (toggleBtn) {
    const saved = localStorage.getItem("theme");
    if (saved === "dark") {
      document.body.classList.add("dark");
      toggleBtn.textContent = "라이트모드";
    }

    toggleBtn.addEventListener("click", () => {
      const isDark = document.body.classList.toggle("dark");
      localStorage.setItem("theme", isDark ? "dark" : "light");
      toggleBtn.textContent = isDark ? "라이트모드" : "다크모드";
    });
  }
});
