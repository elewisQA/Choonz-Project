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
 fetch('http://localhost:8082/genres/read/'+id)
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
    image.src = data['picture'];
    image.id = "image_genre";
    find.appendChild(image);

    let textContainer = document.createElement("div");
    textContainer.className ="container";
    textContainer.id = "text_container";
    find.appendChild(textContainer);

    let genreName = document.createElement("h1");
    genreName.style = "font-size: 45px;";
    genreName.textContent = data["name"];


    let descGenre = document.createElement("div");
    let genre = document.createElement("p");
    genre.style = "font-size: 17px;";
    genre.textContent = data["description"];
    descGenre.appendChild(genre);

    textContainer.appendChild(genreName);
    textContainer.appendChild(descGenre);

    let tableContainer = document.createElement("div");
    tableContainer.id = "table_container";
    let table = document.createElement("table");
    table.className = "table table-hover";
    let tableBody = document.createElement("tbody");
    find.appendChild(tableContainer);
    tableContainer.appendChild(table);
    table.appendChild(tableBody);
    console.log(data["albums"]);
    // Populate the table
    let count = 0;
    for (let a of data['albums']) { 
        for(let key in a['tracks']){
            console.log(a["tracks"][key]["id"]);
            let value = a['tracks'][key]["name"];
            let trackId = a["tracks"][key]["id"];
            console.log(value);
            let row = document.createElement("tr");
            tableBody.appendChild(row);

            let albumCover = document.createElement("th");
            albumCover.scope = "row";
            albumCover.style =  "width: 100px;";
            let albumImage = document.createElement("img");
            albumImage.src = a["cover"];
            albumImage.id = "img_track_album";
            albumImage.style = "width: 70px;";
            albumCover.appendChild(albumImage);
            row.appendChild(albumCover);

            let songName = document.createElement("td");
            let link = document.createElement("a");
            link.href = "viewTrack.html?id=" + a["tracks"][key]["id"];
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
            secondDropdown.id ="second_dropdown" + count;
            secondDropdown.setAttribute("aria-labelledby", "dropdownMenu222");
            dropdown.appendChild(secondDropdown);
            readPlaylists(count, trackId);
            count ++;
        }
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
         console.log(data);
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