function validateForm(){

    var name = document.forms["createNewPlaylist"]["name"];
    var pic = document.forms["createNewPlaylist"]["pic"];
    var desc = document.forms["createNewPlaylist"]["desc"];
    if (name.value == "") { 
        window.alert("Please enter the name of the playlist."); 
        name.focus(); 
        return true; 
    }
    if (pic.value == "") { 
        window.alert("Please add a link for the cover image."); 
        pic.focus();
        return false; 
    }
    if (desc.value == "") { 
        window.alert("Please enter a brief description"); 
        desc.focus(); 
        return false; 
    }

    return false;
}

 const findPlaylistID = new URLSearchParams(window.location.search);
 for (let found of findPlaylistID) {
    let id = found[1];
    console.log(id);
    getPlaylistTracks(id);  
 } 

function validation (tracksArray){
 document.querySelector("form.playlist").addEventListener("submit", function(stop){
    stop.preventDefault();
    let playlistModal = document.querySelector("form.playlist").elements;
    console.log(playlistModal);
    let playlistName = playlistModal['playlistName'].value;
    let playlistPic = playlistModal['playlistPic'].value;
    let playlistDesc = playlistModal['playlistDesc'].value;
    for (let found of findPlaylistID) {
        console.log(found);
        let id = found[1];
        console.log(id); 
        updatePlaylist(playlistName, playlistPic, playlistDesc, id, tracksArray);
       
    } 
     
})
}

    
  function updatePlaylist(playlistName,  playlistPic, playlistDesc, id, tracksArray) {

       fetch('http://localhost:8082/playlists/update/' + id, {
           method: 'post',
           headers: {
                "Content-type": "application/json",
                "token": "oH8bnUVErJ"
           },
           body:json = JSON.stringify({
            "name": playlistName,
            "artwork": playlistPic,
            "description": playlistDesc,
            "tracks": tracksArray
           })
           })
           .then(json)
           .then(function (data) {
               console.log('Request succeeded with JSON response', data);
            //    window.location.href = "viewPlaylist.html?id=" + id;
           })
           .catch(function (error) {
               console.log('Request failed', error);
           });
   }


function getPlaylistTracks(playlistId) {
   fetch('http://localhost:8082/playlists/read/' + playlistId)
  .then(
    function(response) {
      if (response.status !== 200) {
        console.log('Looks like there was a problem. Status Code: ' +
          response.status);
        return;
      }

      // Examine the text in the response
      response.json().then(function(data) {
        console.log(data['tracks']);
        let tracksArray = data['tracks'];
        validation(tracksArray);
      });
    }
  )
  .catch(function(err) {
    console.log('Fetch Error :-S', err);
  });

}