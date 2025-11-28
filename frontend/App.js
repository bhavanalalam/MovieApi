async function api(path, opts) {
  const res = await fetch('http://localhost:8080' + path, opts);
  return res.json();
}

async function load() {
  const root = document.getElementById('root');
  root.innerHTML = `
    <h1>🎬 Movies</h1>
    <div class="movies-list" id="movies"></div>
    <h2>Add New Movie</h2>
    <div class="form-container" id="form"></div>
  `;

  const movies = await api('/api/movies');
  const moviesDiv = document.getElementById('movies');

  moviesDiv.innerHTML = movies.map(m => `
    <div class="movie-card">
      <h3>${m.title}</h3>
      <p><strong>Duration:</strong> ${m.durationMinutes} mins</p>
      <button onclick="showFor(${m.id})">View Shows</button>
    </div>
  `).join('');

  document.getElementById('form').innerHTML = `
    <input id="title" placeholder="Movie Title">
    <input id="desc" placeholder="Description">
    <input id="dur" type="number" placeholder="Duration (mins)">
    <button id="create">Create Movie</button>
  `;

  document.getElementById('create').onclick = async () => {
    const payload = {
      title: document.getElementById('title').value,
      description: document.getElementById('desc').value,
      durationMinutes: parseInt(document.getElementById('dur').value || 0)
    };

    await fetch('http://localhost:8080/api/movies', {
      method: 'POST',
      headers: {'Content-Type':'application/json'},
      body: JSON.stringify(payload)
    });

    load();
  };
}

async function showFor(id) {
  const shows = await api('/api/shows/movie/' + id);
  const root = document.getElementById('root');

  root.innerHTML = `
    <button class="back-button" onclick="load()">⬅ Back</button>
    <h1>🎟 Shows for Movie ${id}</h1>
    <div class="shows-list">
      ${shows.map(s => `
        <div class="show-card">
          <p><strong>Screen:</strong> ${s.screen}</p>
          <p><strong>Time:</strong> ${s.startTime}</p>
        </div>
      `).join('')}
    </div>

    <h3>Add Show</h3>
    <div class="form-container">
      <input id="stime" placeholder="YYYY-MM-DDTHH:MM">
      <input id="screen" placeholder="Screen name">
      <button id="addShow">Add Show</button>
    </div>
  `;

  document.getElementById('addShow').onclick = async () => {
    const payload = {
      movie: { id: id },
      startTime: document.getElementById('stime').value,
      screen: document.getElementById('screen').value
    };

    await fetch('http://localhost:8080/api/shows', {
      method: 'POST',
      headers: {'Content-Type':'application/json'},
      body: JSON.stringify(payload)
    });

    showFor(id);
  };
}

window.onload = load;
