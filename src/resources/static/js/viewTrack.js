const findId = new URLSearchParams(window.location.search);
for (let found of findId) {
    let id = found[1];
    console.log(id);
    getID(id);  
 } 

function getID(id) {
 fetch('http://localhost:8082/tracks/read/'+id)
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
        viewTrack(data);
      });
    }
  )
  .catch(function(err) {
    console.log('Fetch Error :-S', err);
  });
}

function viewTrack(data) {
    console.log(data['name']);
    let albumId = data['album']['id'];

    let find = document.getElementById("main_info_track");
    getDetails(albumId)

    let trackDuration = document.createElement("p");
    trackDuration.id = "trackDuration";
    trackDuration.textContent = "Duration: " + data["duration"];
    find.appendChild(trackDuration);

    let trackContainer = document.getElementById("track_info_container");
    let trackName = document.createElement("h2");
    trackName.style = "font-size: 45px;";
    trackName.textContent = data["name"];
    trackContainer.appendChild(trackName);
    find.appendChild(trackContainer);
}

function getDetails(albumId) {
  
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
            console.log("album: ",data['name']);
            console.log("artist: ",data['artist']['name']);
            addDetails(data);
          });
        }
      )
      .catch(function(err) {
        console.log('Fetch Error :-S', err);
      });
   }
   
   function addDetails(data){
        let find = document.getElementById("main_info_track");
        let image = document.createElement("img");
        image.src = data['cover'];
        image.id = "image_album";
        find.appendChild(image);

        let br = document.createElement("br");
        let artistLink = document.createElement("a");
        artistLink.id = "trackdetails";
        artistLink.href = "viewArtist.html?id=" + data['artist']['id'];
        artistLink.textContent = "Artist: " + data['artist']['name'];
        find.appendChild(br);
        find.appendChild(artistLink);

        let albumLink = document.createElement("a");
        albumLink.id = "trackdetailsAlbum";
        albumLink.href = "viewAlbum.html?id=" + data["id"];
        albumLink.textContent = "Album: " + data['name'];
        find.appendChild(br);
        find.appendChild(albumLink);
   }