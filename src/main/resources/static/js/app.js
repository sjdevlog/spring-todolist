const API_BASE = "/api/todos";

/* ========== 목록 조회 ========== */
function loadTodos() {
    fetch(API_BASE)
        .then(res => res.json())
        .then(todos => {
            const list = document.getElementById("todo-list");
            if (!list) return;

            list.innerHTML = "";

            todos.forEach(todo => {
                const li = document.createElement("li");

                li.innerHTML = `
                    <span class = "${todo.completed ? 'done' : ''}">
                        ${todo.title}
                    </span>
                    <button onclick="toggleTodo(${todo.id})">
                        ${todo.completed ? '되돌리기' : '완료'}
                    </button>
                    <button onclick="goEdit(${todo.id})">수정</button>
                    <button onclick="deleteTodo(${todo.id})">삭제</button>
                `;

                list.appendChild(li);
            });
        });
}

/* ========== 완료 토글 ========== */
function toggleTodo(id) {
    fetch(`${API_BASE}/${id}/toggle`, { method: "PATCH" })
        .then(() => loadTodos());
}

/* ========== 삭제 ========== */
function deleteTodo(id) {
  fetch(`${API_BASE}/${id}`, { method: "DELETE" })
    .then(() => loadTodos());
}

/* ========== 수정 페이지 이동 ========== */
function goEdit(id) {
  location.href = `edit.html?id=${id}`;
}

/* index.html에서만 실행 */
document.addEventListener("DOMContentLoaded", loadTodos);