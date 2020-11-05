window.onload = loginout;

function loginout(){
    let lIn = document.getElementById("loginBtn");
    let lOut = document.getElementById("logoutBtn");
    if (sessionStorage.getItem("token") !== "") {
      lIn.style.display = "none";
      lOut.style.display = "block";
    } else {
      lIn.style.display = "block";
      lOut.style.display = "none";
    }
}

//---[ Basic Setup ]---
let searchTerm = sessionStorage.getItem("query");

console.log(searchTerm);

artistSearch(searchTerm);
albumSearch(searchTerm);
trackSearch(searchTerm);
genreSearch(searchTerm);
playlistSearch(searchTerm);

//---[ Search Methods ]---
function artistSearch(query) {
    fetch("http://localhost:8082/search/artists/" + query)
    .then(
        function(response) {
            if (response.status !== 302) {// found
                console.log("Artist(s) Not Found!", response.status);
                return;
            }

            response.json().then(function (data) {
                console.log(data);
                let container = document.getElementById("artists");
                for (let i = 0; i < data.length; i++) {
                    populateArtists(data[i], container);
                }             
            });
        }
    )
    .catch(function (err) {
        console.log("Fetch Error:-S", err);
    });
}

function albumSearch(query) {
    fetch("http://localhost:8082/search/albums/" + query)
    .then(
        function(response) {
            if (response.status !== 302) {// found
                console.log("Album(s) Not Found!", response.status);
                return;
            }

            response.json().then(function (data) {
                console.log(data);
                let container = document.getElementById("albums");
                for (let i = 0; i < data.length; i++) {
                    populateAlbums(data[i], container);
                }
            });
        }
    )
    .catch(function (err) {
        console.log("Fetch Error:-S", err);
    });
}

function trackSearch(query) {
    fetch("http://localhost:8082/search/tracks/" + query)
    .then(
        function(response) {
            if (response.status !== 302) {// found
                console.log("Track(s) Not Found!", response.status);
                return;
            }

            response.json().then(function (data) {
                console.log(data);    
                let container = document.getElementById("tracks");    
                for (let i = 0; i < data.length; i++) {
                    populateTracks(data[i], container);
                }
            });
        }
    )
    .catch(function (err) {
        console.log("Fetch Error:-S", err);
    });
}

function genreSearch(query) {
    fetch("http://localhost:8082/search/genres/" + query)
    .then(
        function(response) {
            if (response.status !== 302) {// found
                console.log("Genre(s) Not Found!", response.status);
                return;
            }

            response.json().then(function (data) {
                console.log(data);    
                let container = document.getElementById("genres");       
                for (let i = 0; i < data.length; i++) {
                    populateGenres(data[i], container);
                }
            });
        }
    )
    .catch(function (err) {
        console.log("Fetch Error:-S", err);
    });
}

function playlistSearch(query) {
    fetch("http://localhost:8082/search/playlists/" + query)
    .then(
        function(response) {
            if (response.status !== 302) {// found
                console.log("Playlist(s) Not Found!", response.status);
                return;
            }

            response.json().then(function (data) {
                console.log(data);        
                let container = document.getElementById("playlists");    
                for (let i = 0; i < data.length; i++) {
                    populatePlaylists(data[i], container);
                }
            });
        }
    )
    .catch(function (err) {
        console.log("Fetch Error:-S", err);
    });
}

//---[ Populate Methods ]---
function populateArtists(artist, container) {
    // Column Organization
    let column = document.createElement("div");
    column.setAttribute("class", "col mb-4");

    // Setup Result Card 
    let card = document.createElement("div");
    card.setAttribute("class", "card text-center");

    // Add Image
    let img = document.createElement("img");
    img.setAttribute("class", "card-img-top");
    img.setAttribute("src", artist['picture']);
    card.appendChild(img);

    // Add Details about
    let cardText = document.createElement("div");
    cardText.setAttribute("class", "card-body");
    let group = document.createElement("h5");
    group.innerHTML = "Artist";
    cardText.appendChild(group);
    let link = document.createElement("a");
    link.setAttribute("href", "viewArtist.html?id=" + artist['id']);
    let title = document.createElement("h5");
    title.setAttribute("class", "card-title");
    title.innerHTML = artist['name'];
    link.appendChild(title);
    cardText.appendChild(link);
    card.appendChild(cardText);

    // Add it to it's own column
    column.appendChild(card);
    container.appendChild(column);
}

function populateAlbums(album, container) {
    // Column Organization
    let column = document.createElement("div");
    column.setAttribute("class", "col mb-4");

    // Setup Result Card 
    let card = document.createElement("div");
    card.setAttribute("class", "card text-center");

    // Add Image
    let img = document.createElement("img");
    img.setAttribute("class", "card-img-top");
    img.setAttribute("src", album['cover']);
    card.appendChild(img);

    // Add Details about
    let cardText = document.createElement("div");
    cardText.setAttribute("class", "card-body");
    let group = document.createElement("h5");
    group.innerHTML = "Album";
    group.setAttribute("class", "card-title");
    cardText.appendChild(group);
    let link = document.createElement("a");
    link.setAttribute("href", "viewArtist.html?id=" + album['id']);
    let title = document.createElement("h5");
    title.setAttribute("class", "card-title");
    title.innerHTML = album['name'];
    link.appendChild(title);
    cardText.appendChild(link);
    card.appendChild(cardText);

    // Add it to it's own column
    column.appendChild(card);
    container.appendChild(column);
}

function populateTracks(track, container) {
    // Column Organization
    let column = document.createElement("div");
    column.setAttribute("class", "col mb-4");

    // Setup Result Card 
    let card = document.createElement("div");
    card.setAttribute("class", "card text-center");

    // Add Image
    let img = document.createElement("img");
    img.setAttribute("class", "card-img-top");
    let album = track['album']
    img.setAttribute("src", album['cover']);
    card.appendChild(img);

    // Add Details about
    let cardText = document.createElement("div");
    let group = document.createElement("h5");
    group.innerHTML = "Song";
    group.setAttribute("class", "card-title");
    cardText.appendChild(group);
    cardText.setAttribute("class", "card-body");
    let link = document.createElement("a");
    link.setAttribute("href", "viewArtist.html?id=" + track['id']);
    let title = document.createElement("h5");
    title.setAttribute("class", "card-title");
    title.innerHTML = track['name'];
    link.appendChild(title);
    cardText.appendChild(link);
    card.appendChild(cardText);

    // Add it to it's own column
    column.appendChild(card);
    container.appendChild(column);
}

function populateGenres(genre, container) {
    // Column Organization
    let column = document.createElement("div");
    column.setAttribute("class", "col mb-4");

    // Setup Result Card 
    let card = document.createElement("div");
    card.setAttribute("class", "card text-center");

    // Add Image
    let img = document.createElement("img");
    img.setAttribute("class", "card-img-top");
    img.setAttribute("src", genre['picture']);
    card.appendChild(img);

    // Add Details about
    let cardText = document.createElement("div");
    let group = document.createElement("h5");
    group.innerHTML = "Genre";
    group.setAttribute("class", "card-title");
    cardText.appendChild(group);
    cardText.setAttribute("class", "card-body");
    let link = document.createElement("a");
    link.setAttribute("href", "viewArtist.html?id=" + genre['id']);
    let title = document.createElement("h5");
    title.setAttribute("class", "card-title");
    title.innerHTML = genre['name'];
    link.appendChild(title);
    cardText.appendChild(link);
    card.appendChild(cardText);

    // Add it to it's own column
    column.appendChild(card);
    container.appendChild(column);
}

function populatePlaylists(playlist, container) {
    // Column Organization
    console.log(playlist['name']);
    let column = document.createElement("div");
    column.setAttribute("class", "col mb-4");

    // Setup Result Card 
    let card = document.createElement("div");
    card.setAttribute("class", "card text-center");

    // Add Image
    let img = document.createElement("img");
    img.setAttribute("class", "card-img-top");
    img.setAttribute("src", playlist['artwork']);
    card.appendChild(img);

    // Add Details about
    let cardText = document.createElement("div");
    let group = document.createElement("h5");
    group.innerHTML = "Playlist";
    group.setAttribute("class", "card-title");
    cardText.appendChild(group);
    cardText.setAttribute("class", "card-body");
    let link = document.createElement("a");
    link.setAttribute("href", "viewArtist.html?id=" + playlist['id']);
    let title = document.createElement("h5");
    title.setAttribute("class", "card-title");
    title.innerHTML = playlist['name'];
    link.appendChild(title);
    cardText.appendChild(link);
    card.appendChild(cardText);

    // Add it to it's own column
    column.appendChild(card);
    container.appendChild(column);
}