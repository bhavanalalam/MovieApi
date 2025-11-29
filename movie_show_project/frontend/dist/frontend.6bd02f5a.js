async function api(path, opts) {
    const res = await fetch('http://localhost:8080' + path, opts);
    return res.json();
}
async function load() {
    const root = document.getElementById('root');
    root.innerHTML = `
    <h1 class="title">\u{1F3AC} Movie List</h1>

    <div id="movies" class="movie-list"></div>

    <h2 class="subtitle">\u{2795} Add New Movie</h2>

    <div class="form">
      <input class="input" id="title" placeholder="Movie Title"/>
      <input class="input" id="desc" placeholder="Description"/>
      <input class="input" id="dur" placeholder="Duration (mins)"/>
      <button id="create" class="btn create-btn">Create Movie</button>
    </div>
  `;
    const movies = await api('/api/movies');
    const moviesDiv = document.getElementById('movies');
    moviesDiv.innerHTML = movies.map((m)=>`
      <div class="movie-card">
        <div>
          <strong class="movie-title">${m.title}</strong>
          <span class="movie-duration">(${m.durationMinutes} mins)</span>
        </div>
        <button class="btn show-btn" onclick="showFor(${m.id})">View Shows</button>
      </div>
    `).join('');
    document.getElementById('create').onclick = async ()=>{
        const payload = {
            title: document.getElementById('title').value,
            description: document.getElementById('desc').value,
            durationMinutes: parseInt(document.getElementById('dur').value || 0)
        };
        await fetch('http://localhost:8080/api/movies', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        });
        load();
    };
}
async function showFor(id) {
    const shows = await api('/api/shows/movie/' + id);
    const root = document.getElementById('root');
    root.innerHTML = `
    <h1 class="title">\u{1F39F} Shows for Movie ID: ${id}</h1>

    <div class="show-list">
      ${shows.map((s)=>`<div class="show-card">${s.screen} \u{2014} <span class="show-time">${s.startTime}</span></div>`).join('')}
    </div>

    <button onclick="load()" class="btn back-btn">\u{2B05} Back to Movies</button>

    <h2 class="subtitle">\u{2795} Add New Show</h2>

    <div class="form">
      <input class="input" id="stime" placeholder="YYYY-MM-DDTHH:MM"/>
      <input class="input" id="screen" placeholder="Screen Name"/>
      <button id="addShow" class="btn create-btn">Add Show</button>
    </div>
  `;
    document.getElementById('addShow').onclick = async ()=>{
        const payload = {
            movie: {
                id: id
            },
            startTime: document.getElementById('stime').value,
            screen: document.getElementById('screen').value
        };
        await fetch('http://localhost:8080/api/shows', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        });
        showFor(id);
    };
}
window.onload = load;

//# sourceMappingURL=frontend.6bd02f5a.js.map
