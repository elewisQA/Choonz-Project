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
        updatePlaylist(playlistName, playlistPic, playlistDesc, id);
       
    } 
     
})


    
  function updatePlaylist(playlistName,  playlistPic, playlistDesc, id) {

       fetch('http://localhost:8082/playlists/update/' + id, {
           method: 'post',
           headers: {
                "Content-type": "application/json",
                "token": "IKnnHQY5av"
           },
           body:json = JSON.stringify({
            "name": playlistName,
            "artwork": playlistPic,
            "description": playlistDesc,
           })
           })
           .then(json)
           .then(function (data) {
               console.log('Request succeeded with JSON response', data);
                window.location.href = "viewPlaylist.html?id=" + id;
           })
           .catch(function (error) {
               console.log('Request failed', error);
           });
   }


