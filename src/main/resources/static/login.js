const form = document.getElementById('loginForm');
const errorEl = document.getElementById('loginError');

form.addEventListener('submit', async (e) => {
  e.preventDefault();
  errorEl.textContent = '';
  const formData = new FormData(form);
  const username = formData.get('username');
  const password = formData.get('password');
  try {
    const res = await fetch('/login', {
      method: 'POST',
      headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
      body: new URLSearchParams({ username, password }),
    });
    if (!res.ok) {
      const body = await res.json().catch(() => ({}));
      throw new Error(body.message || 'Login failed');
    }
    const data = await res.json();
    localStorage.setItem('authToken', data.token);
    localStorage.setItem('username', data.username);
    window.location.href = '/index.html';
  } catch (err) {
    errorEl.textContent = err.message;
  }
});


