document.querySelector("form.albums").addEventListener("submit", function(stop){
    stop.preventDefault();
    let albumModal = document.querySelector("form.albums").elements;
    console.log(albumModal);
    let albumName = albumModal['albumName'].value;
    let albumGenre = albumModal['albumGenre'].value;
    let albumCover = albumModal['albumCover'].value;
    let albumArtist = albumModal['albumArtist'].value;
    addAlbum(albumName, albumGenre, albumCover, albumArtist);
    
})


function addAlbum(albumName, albumGenre, albumCover, albumArtist) {

    fetch('http://localhost:8082/albums/create', {
        method: 'post',
        headers: {
              "Content-type": "application/json"
        },
        body:json = JSON.stringify({
            "name": albumName,
            "artist": {
                "id": 1,
                "name": albumArtist
            },
            "genre": {
                "id": 1,
                "name": albumGenre
            },
            "cover": albumCover,
        })
        })
        .then(json)
        .then(function (data) {
            console.log('Request succeeded with JSON response', data);
            window.location.href = "albums.html";
        })
        .catch(function (error) {
            console.log('Request failed', error);
        });
}
