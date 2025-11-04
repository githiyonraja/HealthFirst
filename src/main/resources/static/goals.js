const token = localStorage.getItem('authToken');
if (!token) window.location.href = '/login.html';

document.getElementById('logoutBtn').addEventListener('click', () => {
  localStorage.removeItem('authToken');
  localStorage.removeItem('username');
  window.location.href = '/login.html';
});

function authFetch(url, options = {}) {
  return fetch(url, {
    ...options,
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json',
      ...(options.headers || {})
    }
  });
}

const primaryGoal = document.getElementById('primaryGoal');
const goalWeight = document.getElementById('goalWeight');
const saveGoal = document.getElementById('saveGoal');
const goalsTable = document.getElementById('goalsTable');
const goalError = document.getElementById('goalError');

saveGoal.addEventListener('click', async () => {
  goalError.textContent = '';
  try {
    const body = {
      primaryGoal: primaryGoal.value,
      goalWeight: Number(goalWeight.value)
    };
    const res = await authFetch('/goals', { method: 'POST', body: JSON.stringify(body) });
    if (!res.ok) {
      const data = await res.json().catch(() => ({}));
      throw new Error(data.message || 'Failed to save goal');
    }
    await loadGoals();
    primaryGoal.value = '';
    goalWeight.value = '';
  } catch (e) {
    goalError.textContent = e.message;
  }
});

async function loadGoals() {
  const res = await authFetch('/goals/mine');
  const data = await res.json();
  goalsTable.innerHTML = '';
  (data || []).forEach(g => {
    const tr = document.createElement('tr');
    tr.innerHTML = `<td>${g.goalId}</td><td>${g.primaryGoal}</td><td>${g.goalWeight}</td>`;
    goalsTable.appendChild(tr);
  });
}

loadGoals();


