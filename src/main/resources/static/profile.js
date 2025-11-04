const token = localStorage.getItem('authToken');
if (!token) window.location.href = '/login.html';

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

const form = document.getElementById('profileForm');
const errorEl = document.getElementById('profileError');
const f = (id) => document.getElementById(id);

async function loadProfile() {
  const res = await authFetch('/api/user/me');
  if (!res.ok) return;
  const u = await res.json();
  f('firstname').value = u.firstname || '';
  f('lastname').value = u.lastname || '';
  f('age').value = u.age || '';
  f('gender').value = (u.gender || 'M');
  f('email').value = u.email || '';
}

form.addEventListener('submit', async (e) => {
  e.preventDefault();
  errorEl.textContent = '';
  const body = {
    firstname: f('firstname').value,
    lastname: f('lastname').value,
    age: Number(f('age').value),
    gender: f('gender').value,
    email: f('email').value,
  };
  try {
    const res = await authFetch('/api/user/profile', { method: 'PUT', body: JSON.stringify(body) });
    if (!res.ok) {
      const data = await res.json().catch(() => ({}));
      throw new Error(data.message || 'Failed to save profile');
    }
  } catch (err) {
    errorEl.textContent = err.message;
  }
});

loadProfile();


