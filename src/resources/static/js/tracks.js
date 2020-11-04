fetch('http://localhost:8082/tracks/read')
  .then(
    function(response) {
      if (response.status !== 200) {
        console.log('Looks like there was a problem. Status Code: ' +
          response.status);
        return;
      }

      // Examine the text in the response
      // response.json().then(function(data) {
      //   console.log(data);
      //   populate(data);
      // });
    response.json().then(
        data => {
            data.sort((a, b) => (a.name > b.name) ? 1 : (b.name > a.name)? -1 : 0)
            console.log(data);
            populate(data);
        },
    )
    }
  )
  .catch(function(err) {
    console.log('Fetch Error :-S', err);
  });

function populate(data) {
    let find = document.getElementById("main_info");

    let tableContainer = document.createElement("div");
    tableContainer.id = "table_container_tracks";
    let table = document.createElement("table");
    table.className = "table table-hover";
    let tableBody = document.createElement("tbody");
    find.appendChild(tableContainer);
    tableContainer.appendChild(table);
    table.appendChild(tableBody);
    // Populate the table
    count = 0;
    for (let key in data) {
        // data.sort(function(a,b){
        //     if (a.name < b.name) {return -1;}
        //     else if (a.name > b.name) {return 1;}
        //     else{return 0;}
        // });
        let trackId = data[key]['id'];
        let albumId = data[key]["album"]["id"];
        console.log(albumId);
        //console.log(data[key]['album']['id']);
     let value = data[key]["name"];
     let album = data[key]["album"]["cover"];
     console.log(value);
     console.log(album);
     let row = document.createElement("tr");
     row.id = "row_id";
     tableBody.appendChild(row);

     let albumCover = document.createElement("th");
     albumCover.scope = "row";
     albumCover.style =  "width: 100px;";
     let albumlink = document.createElement("a");
     albumlink.href = "viewAlbum.html?id=" + data[key]["album"]["id"];
     let albumImage = document.createElement("img");
     albumImage.src = album;
     albumImage.id = "img_track_album";
     albumImage.style = "width: 70px;";
     albumlink.appendChild(albumImage);
     albumCover.appendChild(albumlink);
     row.appendChild(albumCover);

     let songName = document.createElement("td");
     songName.id = "trackName" + count;
     let link = document.createElement("a");
     link.href = "viewTrack.html?id=" + data[key]['id'];
     link.textContent = value;
     row.appendChild(songName);
     let br = document.createElement("br");
     songName.appendChild(link);
     songName.appendChild(br);
     getArtist(albumId, count);

        // let br = document.createElement("br");
        // songName.appendChild(br);
        // let artistLink = document.createElement("a");
        // artistLink.id = "artistLinkID";
        // artistLink.href = "#";
        // artistLink.textContent = "artist";
        // songName.appendChild(artistLink);

      
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

      let linkPlaylist = document.createElement("a");
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

function getArtist(albumId, count) {
  
 fetch('http://localhost:8082/albums/read/' + albumId)
   .then(
     function(response) {
       if (response.status !== 200) {
         console.log('Looks like there was a problem. Status Code: ' +
           response.status);
         return;
       }

       // Examine the text in the response
       response.json().then(function(data) {
         console.log(data['artist']['name']);
        //  let find = document.getElementById("trackName");
        //  let br = document.createElement("br");
        //  let artistLink = document.createElement("a");
        //  artistLink.href = "#";
        //  artistLink.textContent = data['artist']['name'];
        //  find.appendChild(br);
        //  find.appendChild(artistLink);
         addArtist(data, count);
       });
     }
   )
   .catch(function(err) {
     console.log('Fetch Error :-S', err);
   });
}

function addArtist(data, count){
  console.log(data['artist']['id'])
//   let find = document.getElementById("row_id");
//   let songArtist = document.createElement("td");
//      let link = document.createElement("a");
//      link.href = "#";
//      link.textContent = data['artist']['name'];
//      songArtist.appendChild(link);
//      find.appendChild(songArtist);

//         artistName = data['artist']['name'];

        let find = document.getElementById("trackName" + count);
        let br = document.createElement("br");
        let artistLink = document.createElement("a");
        artistLink.style = "font-size: 14px; color: #444;";
        artistLink.href = "viewArtist.html?id=" + data['artist']['id'];
        artistLink.textContent = data['artist']['name'];
        find.appendChild(br);
        find.appendChild(artistLink);
        find.id = "done";  
}

function readPlaylists(songCount, trackId) {
  fetch('http://localhost:8082/playlists/read')
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
         addPlaylists(data, songCount, trackId);
       });
     }
   )
   .catch(function(err) {
     console.log('Fetch Error :-S', err);
   });
 }

 function addPlaylists(data, songCount, trackId) {
  let find = document.getElementById("second_dropdown"+songCount);
  for (let key of data) {
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
      "token": sessionStorage.getItem('token')
    },
    body:json = JSON.stringify({
      "id": playlistId,
      "user": {
        "id": sessionStorage.getItem('userId')
      }
     })
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