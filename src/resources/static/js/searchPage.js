//---[ Basic Setup ]---
let searchTerm = sessionStorage.getItem("query");
let container = document.getElementById("search_results");
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
                let title = document.createElement("h2");
                title.innerHTML = "Artists";
                let artistList = document.createElement("ul");         
                for (let i = 0; i < data.length; i++) {
                    populateArtists(data[i], artistList);
                }
                container.appendChild(title);
                container.appendChild(artistList);
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
                let title = document.createElement("h2");
                title.innerHTML = "Albums";
                let albumList = document.createElement("ul");         
                for (let i = 0; i < data.length; i++) {
                    populateAlbums(data[i], albumList);
                }
                container.appendChild(title);
                container.appendChild(albumList);
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
                let title = document.createElement("h2");
                title.innerHTML = "Tracks";
                let trackList = document.createElement("ul");         
                for (let i = 0; i < data.length; i++) {
                    populateTracks(data[i], trackList);
                }
                container.appendChild(title);
                container.appendChild(trackList);
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
                let title = document.createElement("h2");
                title.innerHTML = "Genres";
                let genreList = document.createElement("ul");         
                for (let i = 0; i < data.length; i++) {
                    populateGenres(data[i], genreList);
                }
                container.appendChild(title);
                container.appendChild(genreList);
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
                let title = document.createElement("h2");
                title.innerHTML = "Playlists";
                let playlistList = document.createElement("ul");         
                for (let i = 0; i < data.length; i++) {
                    populatePlaylists(data[i], playlistList);
                }
                container.appendChild(title);
                container.appendChild(playlistList);
            });
        }
    )
    .catch(function (err) {
        console.log("Fetch Error:-S", err);
    });
}

//---[ Populate Methods ]---
function populateArtists(artist, list) {
    console.log(artist['name']);
    let newEntry = document.createElement("li");
    newEntry.innerHTML = artist['name'];
    list.appendChild(newEntry);
}

function populateAlbums(album, list) {
    console.log(album['name']);
    let newEntry = document.createElement("li");
    newEntry.innerHTML = album['name'];
    list.appendChild(newEntry);
}

function populateTracks(track, list) {
    console.log(track['name']);
    let newEntry = document.createElement("li");
    newEntry.innerHTML = track['name'];
    list.appendChild(newEntry);
}

function populateGenres(genre, list) {
    console.log(genre['name']);
    let newEntry = document.createElement("li");
    newEntry.innerHTML = genre['name'];
    list.appendChild(newEntry);
}

function populatePlaylists(playlist, list) {
    console.log(playlist['name']);
    let newEntry = document.createElement("li");
    newEntry.innerHTML = playlist['name'];
    list.appendChild(newEntry);
}