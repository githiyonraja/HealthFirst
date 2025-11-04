const token = localStorage.getItem('authToken');
if (!token) window.location.href = '/login.html';

const helloUser = document.getElementById('helloUser');
helloUser.textContent = `Hello, ${localStorage.getItem('username') || ''}`;

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

// Helpers
const todayISO = () => new Date().toISOString().slice(0,10);

// Steps
const stepsDate = document.getElementById('stepsDate');
const stepsValue = document.getElementById('stepsValue');
const stepsTable = document.getElementById('stepsTable');
stepsDate.value = todayISO();
document.getElementById('saveSteps').addEventListener('click', async () => {
  await authFetch('/api/steps', {
    method: 'POST',
    body: JSON.stringify({ entryDate: stepsDate.value, steps: Number(stepsValue.value || 0) })
  });
  loadRange();
  loadToday();
});

// Calories
const calDate = document.getElementById('calDate');
const calIn = document.getElementById('calIn');
const calOut = document.getElementById('calOut');
const calTable = document.getElementById('calTable');
calDate.value = todayISO();
document.getElementById('saveCalories').addEventListener('click', async () => {
  await authFetch('/api/calories', {
    method: 'POST',
    body: JSON.stringify({ entryDate: calDate.value, caloriesIn: Number(calIn.value||0), caloriesOut: Number(calOut.value||0) })
  });
  loadRange();
  loadToday();
});

// Weight (upsert)
const wDate = document.getElementById('wDate');
const wValue = document.getElementById('wValue');
const wTable = document.getElementById('wTable');
wDate.value = todayISO();
document.getElementById('saveWeight').addEventListener('click', async () => {
  await authFetch('/api/weight', {
    method: 'POST',
    body: JSON.stringify({ entryDate: wDate.value, weightKg: Number(wValue.value) })
  });
  loadRange();
  loadToday();
});

async function loadToday(){
  const res = await authFetch('/api/summary/today');
  const data = await res.json();
  const el = document.getElementById('todaySummary');
  el.textContent = `Steps: ${data.steps ?? '-'} | In: ${data.caloriesIn ?? '-'} | Out: ${data.caloriesOut ?? '-'} | Weight: ${data.weightKg ?? '-'}`;
}

async function loadRange(){
  const start = new Date(); start.setDate(start.getDate()-6);
  const startISO = start.toISOString().slice(0,10);
  const endISO = todayISO();
  const res = await authFetch(`/api/summary/range?start=${startISO}&end=${endISO}`);
  const data = await res.json();
  // Render steps
  stepsTable.innerHTML = '';
  (data.steps||[]).forEach(e=>{
    const tr = document.createElement('tr');
    tr.innerHTML = `<td>${e.entryDate}</td><td>${e.steps}</td>`;
    stepsTable.appendChild(tr);
  });
  // Render calories
  calTable.innerHTML = '';
  (data.calories||[]).forEach(e=>{
    const tr = document.createElement('tr');
    tr.innerHTML = `<td>${e.entryDate}</td><td>${e.caloriesIn}</td><td>${e.caloriesOut}</td>`;
    calTable.appendChild(tr);
  });
  // Render weights
  wTable.innerHTML = '';
  (data.weights||[]).forEach(e=>{
    const tr = document.createElement('tr');
    tr.innerHTML = `<td>${e.entryDate}</td><td>${e.weightKg}</td>`;
    wTable.appendChild(tr);
  });
}

loadToday();
loadRange();


