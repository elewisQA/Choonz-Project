
function validateForm(){

    var name = document.forms["createNewAlbum"]["name"];
    var genre = document.forms["createNewAlbum"]["albumGenre"];
    var artist = document.forms["createNewAlbum"]["albumArtist"];
    var cover = document.forms["createNewAlbum"]["cover"];
    if (name.value == "") { 
        window.alert("Please enter the name of the album."); 
        name.focus(); 
        return true; 
    }
    if (genre.value == 0) { 
        window.alert("Please select the genre."); 
        genre.focus();
        return false; 
    }
    if (artist.value == 0) { 
        window.alert("Please select the artist."); 
        artist.focus(); 
        return false; 
    }
    if (cover.value == "") { 
        window.alert("Please paste the link of the image for this album."); 
        cover.focus(); 
        return false; 
    }

    return false;
}

const findAlbumId = new URLSearchParams(window.location.search);

    document.querySelector("form.albums").addEventListener("submit", function(stop){
        stop.preventDefault();
        let albumModal = document.querySelector("form.albums").elements;
        console.log(albumModal);
        let albumName = albumModal['albumName'].value;
        let albumGenreID = albumModal['albumGenre'].value;
        let albumGenreName = albumModal['albumGenre'].genreText;
        let albumCover = albumModal['albumCover'].value;
        let albumArtistID = albumModal['albumArtist'].value;
        let albumArtistName = albumModal['albumArtist'].artistText;

        
        for (let found of findAlbumId) {
            console.log(found);
            let id = found[1];
            console.log(id); 
        } 
        
        //updateAlbum(albumName, albumGenreID, albumGenreName, albumCover, albumArtistID, albumArtistName, id);
        
    })
    
    function updateAlbum(albumName, albumGenreID, albumGenreName, albumCover, albumArtistID, albumArtistName, id) {
    
        fetch('http://localhost:8082/albums/update/' + id, {
            method: 'put',
            headers: {
                  "Content-type": "application/json"
            },
            body:json = JSON.stringify({
                "name": albumName,
                "id": id,
                "artist": {
                    "id": albumArtistID,
                    "name": albumArtistName
                },
                "genre": {
                    "id": albumGenreID,
                    "name": albumGenreName
                },
                "cover": albumCover,
            })
            })
            .then(json)
            .then(function (data) {
                console.log('Request succeeded with JSON response', data);
                // window.location.href = "albums.html";
            })
            .catch(function (error) {
                console.log('Request failed', error);
            });
    }

fetch('http://localhost:8082/artists/read')
      .then(
        function(response) {
          if (response.status !== 200) {
            console.log('Looks like there was a problem. Status Code: ' +
              response.status);
            return;
          }
    
          // Examine the text in the response
          response.json().then(function(data) {
            console.log(data);
    
            feedDropdownWithArtists(data);
          });
        }
      )
      .catch(function(err) {
        console.log('Fetch Error :-S', err);
      });
    
      fetch('http://localhost:8082/genres/read')
      .then(
        function(response) {
          if (response.status !== 200) {
            console.log('Looks like there was a problem. Status Code: ' +
              response.status);
            return;
          }
    
          // Examine the text in the response
          response.json().then(function(data) {
            console.log(data);
    
            feedDropdownWithGenres(data);
          });
        }
      )
      .catch(function(err) {
        console.log('Fetch Error :-S', err);
      });
    
      function feedDropdownWithArtists(alldata) {
    
        for (let key of alldata) {
          console.log(key);
          console.log(key['name']);
    
          let dropdown = document.getElementById("artistDD");
          let option = document.createElement("option");
          option.value = key['id'];
          let artistText = document.createTextNode(key['name']);
          option.appendChild(artistText);
          dropdown.appendChild(option);
          
        }
    
      }
    
      function feedDropdownWithGenres(alldata) {
    
        for (let key of alldata) {
          console.log(key);
          console.log(key['genreName']);
    
          let dropdown = document.getElementById("genreDD");
          let option = document.createElement("option");
          option.value = key['id'];
          let genreText = document.createTextNode(key['name']);
          option.appendChild(genreText);
          dropdown.appendChild(option);
          
        }
    
      }
