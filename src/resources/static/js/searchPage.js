let searchTerm = sessionStorage.getItem("query");
console.log(searchTerm);

artistSearch(searchTerm);
albumSearch(searchTerm);
trackSearch(searchTerm);
genreSearch(searchTerm);
playlistSearch(searchTerm);

// Populate Information on page

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
            });
        }
    )
    .catch(function (err) {
        console.log("Fetch Error:-S", err);
    });
}
