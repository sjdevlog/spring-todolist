// /static/js/app.js
const API_BASE = "/api/todos";

/** ====== 공통 유틸 ====== */
function escapeHtml(str) {
  // XSS 방지용(제목에 <script> 같은 거 넣어도 안전)
  return String(str ?? "")
    .replace(/&/g, "&amp;")
    .replace(/</g, "&lt;")
    .replace(/>/g, "&gt;")
    .replace(/"/g, "&quot;")
    .replace(/'/g, "&#039;");
}

/** ====== 목록 렌더링 ====== */
async function loadTodos() {
  const list = document.getElementById("todo-list");
  if (!list) return; // index.html에서만 동작

  try {
    const res = await fetch(API_BASE, {
      headers: { Accept: "application/json" },
    });

    if (!res.ok) {
      const text = await res.text();
      list.innerHTML = `<li>목록 조회 실패: ${escapeHtml(text)}</li>`;
      return;
    }

    const todos = await res.json();
    list.innerHTML = "";

    if (!Array.isArray(todos) || todos.length === 0) {
      list.innerHTML = `
        <li class="empty">
            할 일이 없습니다.<br>
            <span class="hint">+ 새 할일을 눌러 추가해보세요.</span>
        </li>
        `;

      return;
    }

    todos.forEach((todo) => {
      const li = document.createElement("li");

      const title = escapeHtml(todo.title);
      const doneClass = todo.completed ? "done" : "";
      const toggleLabel = todo.completed ? "되돌리기" : "완료";

      li.innerHTML = `
        <span class="todo-title ${doneClass}">${title}</span>
        <div class="actions">
          <button onclick="toggleTodo(${todo.id})">${toggleLabel}</button>
          <button class="secondary" onclick="goEdit(${todo.id})">수정</button>
          <button class="secondary" onclick="deleteTodo(${todo.id})">삭제</button>
        </div>
      `;

      list.appendChild(li);
    });
  } catch (e) {
    list.innerHTML = `<li>네트워크 오류로 목록을 불러오지 못했습니다.</li>`;
    console.error(e);
  }
}

/** ====== 완료/미완료 토글 ====== */
async function toggleTodo(id) {
  try {
    const res = await fetch(`${API_BASE}/${id}/toggle`, {
      method: "PATCH",
      headers: { Accept: "application/json" },
    });

    if (!res.ok) {
      const text = await res.text();
      alert("상태 변경 실패:\n" + text);
      return;
    }

    await loadTodos();
  } catch (e) {
    alert("네트워크 오류로 상태 변경에 실패했습니다.");
    console.error(e);
  }
}

/** ====== 삭제 ====== */
async function deleteTodo(id) {
  if (!confirm("정말 삭제할까요?")) return;

  try {
    const res = await fetch(`${API_BASE}/${id}`, {
      method: "DELETE",
      headers: { Accept: "application/json" },
    });

    if (!res.ok) {
      const text = await res.text();
      alert("삭제 실패:\n" + text);
      return;
    }

    await loadTodos();
  } catch (e) {
    alert("네트워크 오류로 삭제에 실패했습니다.");
    console.error(e);
  }
}

/** ====== 수정 페이지로 이동 ====== */
function goEdit(id) {
  window.location.href = `edit.html?id=${id}`;
}

/** ====== index.html에서만 자동 실행 ====== */
document.addEventListener("DOMContentLoaded", loadTodos);
