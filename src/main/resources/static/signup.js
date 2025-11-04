const form = document.getElementById('signupForm');
const errorEl = document.getElementById('signupError');

form.addEventListener('submit', async (e) => {
  e.preventDefault();
  errorEl.textContent = '';

  const fd = new FormData(form);
  const username = fd.get('username');
  const email = fd.get('email');
  const password = fd.get('password');
  const confirm = fd.get('confirm');
  if (password !== confirm) {
    errorEl.textContent = 'Passwords do not match';
    return;
  }

  try {
    const res = await fetch('/api/auth/signup', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ username, email, password })
    });
    if (!res.ok) {
      const body = await res.json().catch(() => ({}));
      throw new Error(body.message || 'Signup failed');
    }
    const data = await res.json().catch(() => null);
    if (data && data.token) {
      localStorage.setItem('authToken', data.token);
      localStorage.setItem('username', data.username);
      window.location.href = '/index.html';
    } else {
      window.location.href = '/login.html';
    }
  } catch (err) {
    errorEl.textContent = err.message;
  }
});


