const findId = new URLSearchParams(window.location.search);
for (let found of findId) {
    let id = found[1];
    console.log(id);
    getID(id);
      
 }

 window.onload = loginout;

function loginout(){
    let lIn = document.getElementById("loginBtn");
    let lOut = document.getElementById("logoutBtn");
    if ((sessionStorage.getItem("token") === null) || (sessionStorage.getItem("token") === "")) {
      lIn.style.display = "block";
      lOut.style.display = "none";
    } else {
      lIn.style.display = "none";
      lOut.style.display = "block";
    }
}

function getID(id) {
 fetch('http://localhost:8082/albums/read/'+id)
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
        populate(data);
      });
    }
  )
  .catch(function(err) {
    console.log('Fetch Error :-S', err);
  });
}

function populate(data) {
    console.log(data['name']);
    console.log(data['id']);
    let find = document.getElementById("main_info");
    let image = document.createElement("img");
    image.src = data['cover'];
    image.id = "image_album";
    find.appendChild(image);

    let textContainer = document.createElement("div");
    textContainer.className ="container";
    textContainer.id = "text_container";
    find.appendChild(textContainer);

    let albumName = document.createElement("h1");
    albumName.style = "font-size: 45px;";
    albumName.textContent = data['name'];

    let linkArtist = document.createElement("a");
    linkArtist.href="viewArtist.html?id=" + data['artist']['id'];
    let artist = document.createElement("h2");
    artist.style = "font-size: 25px;";
    artist.textContent = data['artist']['name'];
    linkArtist.appendChild(artist);

    let linkGenre = document.createElement("a");
    linkGenre.href="viewGenre.html?id=" + data['genre']['id'];
    let genre = document.createElement("p");
    genre.style = "font-size: 17px;";
    genre.textContent = data['genre']['name'];
    linkGenre.appendChild(genre);

    textContainer.appendChild(albumName);
    textContainer.appendChild(linkArtist);
    textContainer.appendChild(linkGenre);

    let tableContainer = document.createElement("div");
    tableContainer.id = "table_container";
    let table = document.createElement("table");
    table.className = "table table-hover";
    let tableBody = document.createElement("tbody");
    find.appendChild(tableContainer);
    tableContainer.appendChild(table);
    table.appendChild(tableBody);
    console.log(data["tracks"]);
    let songCount = 1;
    // Populate the table
    for (let key in data['tracks']) { 
    console.log(data["tracks"][key]["id"]);
     let value = data['tracks'][key]["name"];
     console.log(value);
     let row = document.createElement("tr");
     tableBody.appendChild(row);

     let songId = document.createElement("th");
     songId.scope = "row";
     songId.textContent = songCount;
     row.appendChild(songId);

     let songName = document.createElement("td");
     let link = document.createElement("a");
     link.href = "viewTrack.html?id=" + data["tracks"][key]["id"];
     link.textContent = value;
     row.appendChild(songName);
     songName.appendChild(link);
      
     let songIcon = document.createElement("td");
     let dropdownMenu = document.createElement("div");
     dropdownMenu.className="dropdown";
     songIcon.appendChild(dropdownMenu);
      
    let linkSong = document.createElement("a");
      linkSong.href ="#";
      linkSong.id ="dropdownMenu2";
      linkSong.setAttribute("data-toggle", "dropdown");
      linkSong.setAttribute("aria-haspopup", "true");
      linkSong.setAttribute("aria-expanded", "false");
      let infoSong = document.createElement("i");
      infoSong.className ="fas fa-ellipsis-h";
      infoSong.id = "info_tracks"
      row.appendChild(songIcon);
      linkSong.appendChild(infoSong);
      dropdownMenu.appendChild(linkSong);

      let dropdown = document.createElement("div");
      dropdown.className = "dropdown-menu";
      dropdown.setAttribute("aria-labelledby", "dropdownMenu22");
      dropdownMenu.appendChild(dropdown);

      let linkPlaylist = document.createElement("button");
      linkPlaylist.href="#";
      linkPlaylist.className = "dropdown-item";
      linkPlaylist.id = "dropdownMenu222";
      linkPlaylist.type = "button";
      linkPlaylist.setAttribute("data-toggle", "dropdown");
      linkPlaylist.setAttribute("aria-haspopup", "true");
      linkPlaylist.setAttribute("aria-expanded", "false");
      linkPlaylist.textContent = "Add to Playlist";
      dropdown.appendChild(linkPlaylist);

      let spanPlaylist = document.createElement("span");
      spanPlaylist.id ="album-menu-playlist";
      linkPlaylist.appendChild(spanPlaylist);

      let iconPlaylist = document.createElement("i");
      iconPlaylist.className = "fas fa-music";
      spanPlaylist.appendChild(iconPlaylist);

      let secondDropdown = document.createElement("div");
      secondDropdown.className = "dropdown-menu";
      secondDropdown.id ="second_dropdown" + songCount;
      secondDropdown.setAttribute("aria-labelledby", "dropdownMenu222");
      dropdown.appendChild(secondDropdown);
      let trackId = data['tracks'][key]['id'];
      readPlaylists(songCount, trackId);
     songCount++;
    }
    
}

function readPlaylists(songCount, trackId) {
  fetch('http://localhost:8082/users/read')
   .then(
     function(response) {
       if (response.status !== 200) {
         console.log('Looks like there was a problem. Status Code: ' +
           response.status);
         return;
       }
 
       // Examine the text in the response
       response.json().then(function(data) {
         //console.log(data);
         let userId = sessionStorage.getItem('userId'); 
         for (let key of data) {
          if (key['id'] == userId) {
            console.log(key['id'])
            addPlaylists(key, songCount, trackId)
          }
        }
       });
     }
   )
   .catch(function(err) {
     console.log('Fetch Error :-S', err);
   });
 }

 function addPlaylists(data, songCount, trackId) {
  let find = document.getElementById("second_dropdown"+songCount);
  for (let key of data['playlists']) {
    console.log(key);
    let selectPlaylist = document.createElement("a");
    selectPlaylist.className = "dropdown-item";
    selectPlaylist.href="#";
    selectPlaylist.textContent = key['name'];
    selectPlaylist.addEventListener("click", function(stop){
      stop.preventDefault();  
      addTrack(trackId, key['id']);    
    })
    find.appendChild(selectPlaylist);
  }
 }

 function addTrack(trackId, playlistId){
  fetch('http://localhost:8082/playlists/add/' + playlistId+'/'+trackId,{
    method: 'post',
    headers: {
      "Content-type": "application/json",
      "token": sessionStorage.getItem('token'),
      "uid": sessionStorage.getItem('userId')
    },
  })
  .then(
    function(response) {
      if (response.status !== 202) {
        console.log('Looks like there was a problem. Status Code: ' +
          response.status);
        return;
      }

      // Examine the text in the response
      response.json().then(function(data) {
        console.log(data);
      });
    }
  )
  .catch(function(err) {
    console.log('Fetch Error :-S', err);
  });
}